package wannabit.io.cosmostaion.chain

import android.content.Context
import com.cosmos.auth.v1beta1.QueryGrpc
import com.cosmos.auth.v1beta1.QueryProto
import com.cosmos.bank.v1beta1.QueryGrpc.newBlockingStub
import com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesRequest
import com.cosmos.base.query.v1beta1.PaginationProto
import com.cosmos.base.v1beta1.CoinProto.Coin
import com.cosmos.distribution.v1beta1.DistributionProto.DelegationDelegatorReward
import com.cosmos.staking.v1beta1.QueryProto.QueryDelegatorDelegationsRequest
import com.cosmos.staking.v1beta1.QueryProto.QueryDelegatorUnbondingDelegationsRequest
import com.cosmos.staking.v1beta1.StakingProto
import com.cosmos.staking.v1beta1.StakingProto.DelegationResponse
import com.cosmos.staking.v1beta1.StakingProto.UnbondingDelegation
import com.cosmos.tx.v1beta1.TxProto
import com.google.gson.Gson
import com.google.protobuf.ByteString
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONObject
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.cosmosClass.ChainAkash
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainCosmos
import wannabit.io.cosmostaion.chain.cosmosClass.ChainEvmos
import wannabit.io.cosmostaion.chain.cosmosClass.ChainInjective
import wannabit.io.cosmostaion.chain.cosmosClass.ChainIris
import wannabit.io.cosmostaion.chain.cosmosClass.ChainJuno
import wannabit.io.cosmostaion.chain.cosmosClass.ChainKava118
import wannabit.io.cosmostaion.chain.cosmosClass.ChainKava459
import wannabit.io.cosmostaion.chain.cosmosClass.ChainLum118
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOsmosis
import wannabit.io.cosmostaion.chain.cosmosClass.ChainStride
import wannabit.io.cosmostaion.common.BaseConstant.BASE_GAS_AMOUNT
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.common.CosmostationConstants.CHAIN_BASE_URL
import wannabit.io.cosmostaion.common.safeApiCall
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.model.res.AccountResponse
import wannabit.io.cosmostaion.data.model.res.BnbToken
import wannabit.io.cosmostaion.data.model.res.Cw20Balance
import wannabit.io.cosmostaion.data.model.res.FeeInfo
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.Param
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.database.model.RefAddress
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.concurrent.TimeUnit

open class CosmosLine : BaseChain() {

    open var stakeDenom: String? = ""
    open var supportCw20 = false
    open var supportErc20 = false
    open var supportStaking = true

    open var evmCompatible = false

    open var grpcPort = 443
    private var duration = 20L

    var rewardAddress: String? = ""
    var cosmosAuth: com.google.protobuf.Any? = null
    var cosmosValidators = mutableListOf<StakingProto.Validator>()
    var cosmosBalances = mutableListOf<Coin>()
    var cosmosVestings = mutableListOf<Coin>()
    var cosmosDelegations = mutableListOf<DelegationResponse>()
    var cosmosUnbondings = mutableListOf<UnbondingDelegation>()
    var cosmosRewards = mutableListOf<DelegationDelegatorReward>()

    var tokens = mutableListOf<Token>()
    var param: Param? = null

    var lcdAccountInfo: AccountResponse? = null
    var lcdBeaconTokens = mutableListOf<BnbToken>()

    interface LoadDataCallback {
        fun onDataLoaded(isLoaded: Boolean)
    }

    open var loadDataCallback: LoadDataCallback? = null

    fun setLoadDataCallBack(callback: LoadDataCallback) {
        loadDataCallback = callback
    }

    private fun getChannel(): ManagedChannel {
        return ManagedChannelBuilder.forAddress(grpcHost, grpcPort).useTransportSecurity().build()
    }

    open fun allAssetValue(): BigDecimal {
        val allValue: BigDecimal = if (this is ChainBinanceBeacon) {
            lcdBalanceValue(stakeDenom)
        } else {
            balanceValueSum().add(vestingValueSum()).add(delegationValueSum())
                .add(unbondingValueSum()).add(rewardValueSum())
        }
        return allValue
    }

    fun loadData(id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            val paramData = try {
                loadParam()
            } catch (e: Exception) {
                null
            }
            param = paramData

            if (supportCw20) {
                val cw20s = try {
                    loadCw20Token()
                } catch (e: Exception) {
                    null
                }
                if (cw20s != null) {
                    tokens = cw20s
                }
            }
        }

        if (this is ChainBinanceBeacon) {
            loadLcdData()
        } else {
            loadGrpcData(id)
        }
    }

    private fun loadGrpcData(id: Long) = CoroutineScope(Dispatchers.IO).launch {
        val channel = getChannel()
        val stub = QueryGrpc.newBlockingStub(channel).withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request = QueryProto.QueryAccountRequest.newBuilder().setAddress(address).build()

        try {
            stub.account(request)?.let { response ->
                cosmosAuth = response.account
                loadGrpcMoreData(channel, id)
            }
        } catch (e: Exception) {
            loadDataCallback?.onDataLoaded(true)
            fetched = true
            val refAddress = RefAddress(id, tag, address, "0", "0", "0",  0)
            BaseData.updateRefAddressesMain(refAddress)
        } finally {
            channel.shutdown()
            try {
                if (!channel.awaitTermination(5, TimeUnit.SECONDS)) {
                    channel.shutdownNow()
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    open fun loadGrpcMoreData(channel: ManagedChannel, id: Long) = runBlocking {
        CoroutineScope(Dispatchers.Default).let {
            loadBalance(channel)
            if (supportStaking) {
                loadDelegation(channel)
                loadUnbonding(channel)
                loadReward(channel)
            }

            BaseUtils.onParseVestingAccount(this@CosmosLine)
            loadDataCallback?.onDataLoaded(true)
            fetched = true

            if (fetched) {
                val refAddress = RefAddress(id, tag, address, allAssetValue().toPlainString(), allStakingDenomAmount().toString(), "0", cosmosBalances.size.toLong())
                BaseData.updateRefAddressesMain(refAddress)
            }
            it.cancel()
        }
    }

    fun getInitFee(c: Context): TxProto.Fee? {
        var feeCoin: Coin? = null
        for (i in 0 until getDefaultFeeCoins(c).size) {
            val fee = getDefaultFeeCoins(c)[i]
            if (balanceAmount(fee.denom) >= BigDecimal(fee.amount)) {
                feeCoin = Coin.newBuilder().setDenom(fee.denom).setAmount(fee.amount).build()
                break
            }
        }

        if (feeCoin != null) {
            return TxProto.Fee.newBuilder().setGasLimit(BASE_GAS_AMOUNT.toLong()).addAmount(feeCoin).build()
        }
        return null
    }

    fun getBaseFee(c: Context, position: Int, denom: String?): TxProto.Fee {
        val gasAmount = BigDecimal(BASE_GAS_AMOUNT)
        val feeDatas = getFeeInfos(c)[position].feeDatas
        val rate = feeDatas.firstOrNull { it.denom == denom }?.gasRate ?: BigDecimal.ZERO
        val coinAmount = rate?.multiply(gasAmount)?.setScale(0, RoundingMode.DOWN)
        return TxProto.Fee.newBuilder().setGasLimit(BASE_GAS_AMOUNT.toLong()).addAmount(
            Coin.newBuilder().setDenom(denom).setAmount(coinAmount.toString()).build()
        ).build()
    }

    fun getFeeBasePosition(): Int {
        return param?.params?.chainlistParams?.fee?.base?.toInt() ?: 0
    }

    fun isTxFeePayable(c: Context): Boolean {
        getDefaultFeeCoins(c).forEach { fee ->
            if (balanceAmount(fee.denom) >= BigDecimal(fee.amount)) {
                return true
            }
        }
        return false
    }

    fun getDefaultFeeCoins(c: Context): MutableList<Coin> {
        val result: MutableList<Coin> = mutableListOf()
        val gasAmount = BigDecimal(BASE_GAS_AMOUNT)
        val feeDatas = getFeeInfos(c)[getFeeBasePosition()].feeDatas
        feeDatas.forEach { feeData ->
            val amount = feeData.gasRate?.multiply(gasAmount)?.setScale(0, RoundingMode.DOWN)
            result.add(
                Coin.newBuilder().setDenom(feeData.denom).setAmount(amount.toString()).build()
            )
        }
        return result
    }

    fun getFeeInfos(c: Context): MutableList<FeeInfo> {
        val result: MutableList<FeeInfo> = mutableListOf()
        param?.params?.chainlistParams?.fee?.rate?.forEach { rate ->
            result.add(FeeInfo(rate))
        }

        if (result.size == 1) {
            result[0].title = c.getString(R.string.str_fixed)
            result[0].msg = c.getString(R.string.str_fee_speed_title_fixed)
        } else if (result.size == 2) {
            result[1].title = c.getString(R.string.str_average)
            result[1].msg = c.getString(R.string.str_fee_speed_title_average)
            if (result[0].feeDatas[0].gasRate == BigDecimal.ZERO) {
                result[0].title = c.getString(R.string.str_free)
                result[0].msg = c.getString(R.string.str_fee_speed_title_zero)
            } else {
                result[0].title = c.getString(R.string.str_tiny)
                result[0].msg = c.getString(R.string.str_fee_speed_title_tiny)
            }
        } else if (result.size == 3) {
            result[2].title = c.getString(R.string.str_average)
            result[2].msg = c.getString(R.string.str_fee_speed_title_average)
            result[1].title = c.getString(R.string.str_low)
            result[1].msg = c.getString(R.string.str_fee_speed_title_low)
            if (result[0].feeDatas[0].gasRate == BigDecimal.ZERO) {
                result[0].title = c.getString(R.string.str_free)
                result[0].msg = c.getString(R.string.str_fee_speed_title_zero)
            } else {
                result[0].title = c.getString(R.string.str_tiny)
                result[0].msg = c.getString(R.string.str_fee_speed_title_tiny)
            }
        }
        return result
    }

    fun gasMultiply(): Double {
        param?.params?.chainlistParams?.fee?.simulGasMultiply?.let { multiply ->
            return multiply
        } ?: run {
            return 1.2
        }
    }

    private fun loadLcdData() = runBlocking {
        CoroutineScope(Dispatchers.Default).let {
            if (this@CosmosLine is ChainBinanceBeacon) {
//                loadNodeInfo()
                loadAccountInfo()
                loadBeaconTokens()
            }

            loadDataCallback?.onDataLoaded(true)
            fetched = true
            it.cancel()
        }
    }

    fun loadStakeData() {
        if (cosmosValidators.size > 0) { return }

        CoroutineScope(Dispatchers.IO).launch {
            val channel = getChannel()

            try {
                val rewardAddr = async { loadRewardAddress(channel) }
                val bonded = async { loadBondedValidator(channel) }
                val unbonding = async { loadUnbondingValidator(channel) }
                val unbonded = async { loadUnbondedValidator(channel) }

                val response = awaitAll(rewardAddr, bonded, unbonding, unbonded)

                rewardAddress = response[0].toString()
                if (response[1] != null) cosmosValidators.addAll(response[1] as Collection<StakingProto.Validator>)
                if (response[2] != null) cosmosValidators.addAll(response[2] as Collection<StakingProto.Validator>)
                if (response[3] != null) cosmosValidators.addAll(response[3] as Collection<StakingProto.Validator>)

                val tempValidators = cosmosValidators.toMutableList()
                tempValidators.sortWith { o1, o2 ->
                    when {
                        o1.description.moniker == "Cosmostation" -> -1
                        o2.description.moniker == "Cosmostation" -> 1
                        o1.jailed && !o2.jailed -> 1
                        !o1.jailed && o2.jailed -> -1
                        o1.tokens.toDouble() > o2.tokens.toDouble() -> -1
                        else -> 1
                    }
                }
                cosmosValidators = tempValidators

            } finally {
                channel.shutdown()
                try {
                    if (!channel.awaitTermination(5, TimeUnit.SECONDS)) {
                        channel.shutdownNow()
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun loadBalance(channel: ManagedChannel) {
        val pageRequest = PaginationProto.PageRequest.newBuilder().setLimit(2000).build()
        val stub = newBlockingStub(channel).withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request =
            QueryAllBalancesRequest.newBuilder().setPagination(pageRequest).setAddress(address)
                .build()

        stub.allBalances(request)?.let { response ->
            cosmosBalances = response.balancesList
        }
    }

    private fun loadDelegation(channel: ManagedChannel) {
        val stub = com.cosmos.staking.v1beta1.QueryGrpc.newBlockingStub(channel)
            .withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request =
            QueryDelegatorDelegationsRequest.newBuilder().setDelegatorAddr(address).build()

        cosmosDelegations.clear()
        stub.delegatorDelegations(request)?.let { response ->
            response.delegationResponsesList.forEach { delegation ->
                if (delegation.balance.amount != "0") {
                    cosmosDelegations.add(delegation)
                }
            }
        }
    }

    private fun loadUnbonding(channel: ManagedChannel) {
        val stub = com.cosmos.staking.v1beta1.QueryGrpc.newBlockingStub(channel)
            .withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request =
            QueryDelegatorUnbondingDelegationsRequest.newBuilder().setDelegatorAddr(address).build()

        stub.delegatorUnbondingDelegations(request)?.let { response ->
            cosmosUnbondings = response.unbondingResponsesList
        }
    }

    private fun loadReward(channel: ManagedChannel) {
        val stub = com.cosmos.distribution.v1beta1.QueryGrpc.newBlockingStub(channel)
            .withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request =
            com.cosmos.distribution.v1beta1.QueryProto.QueryDelegationTotalRewardsRequest.newBuilder()
                .setDelegatorAddress(address).build()

        stub.delegationTotalRewards(request)?.let { response ->
            cosmosRewards = response.rewardsList
        }
    }

    private suspend fun loadParam(): Param? {
        return when (val response = safeApiCall { RetrofitInstance.mintscanApi.param(this.apiName) }) {
            is NetworkResult.Success -> {
                response.data.body()
            }

            is NetworkResult.Error -> {
                null
            }
        }
    }

    private fun loadBondedValidator(channel: ManagedChannel): MutableList<StakingProto.Validator>? {
        val pageRequest = PaginationProto.PageRequest.newBuilder().setLimit(500).build()
        val stub = com.cosmos.staking.v1beta1.QueryGrpc.newBlockingStub(channel)
            .withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request = com.cosmos.staking.v1beta1.QueryProto.QueryValidatorsRequest.newBuilder().
        setPagination(pageRequest).setStatus("BOND_STATUS_BONDED").build()
        return try {
            stub.validators(request).validatorsList
        } catch (_: Exception) {
            null
        }
    }

    private fun loadUnbondedValidator(channel: ManagedChannel): MutableList<StakingProto.Validator>? {
        val pageRequest = PaginationProto.PageRequest.newBuilder().setLimit(500).build()
        val stub = com.cosmos.staking.v1beta1.QueryGrpc.newBlockingStub(channel)
            .withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request = com.cosmos.staking.v1beta1.QueryProto.QueryValidatorsRequest.newBuilder().
        setPagination(pageRequest).setStatus("BOND_STATUS_UNBONDED").build()
        return try {
            stub.validators(request).validatorsList
        } catch (_: Exception) {
            null
        }
    }

    private fun loadUnbondingValidator(channel: ManagedChannel): MutableList<StakingProto.Validator>? {
        val pageRequest = PaginationProto.PageRequest.newBuilder().setLimit(500).build()
        val stub = com.cosmos.staking.v1beta1.QueryGrpc.newBlockingStub(channel)
            .withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request = com.cosmos.staking.v1beta1.QueryProto.QueryValidatorsRequest.newBuilder()
            .setPagination(pageRequest).setStatus("BOND_STATUS_UNBONDING").build()
        return try {
            return stub.validators(request).validatorsList
        } catch (_: Exception) {
            null
        }
    }

    private fun loadRewardAddress(channel: ManagedChannel): String? {
        val stub = com.cosmos.distribution.v1beta1.QueryGrpc.newBlockingStub(channel)
            .withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request = com.cosmos.distribution.v1beta1.QueryProto.QueryDelegatorWithdrawAddressRequest.newBuilder()
            .setDelegatorAddress(address).build()

        return stub.delegatorWithdrawAddress(request).withdrawAddress
    }

    private suspend fun loadCw20Token(): MutableList<Token> {
        return when (val response = safeApiCall { RetrofitInstance.mintscanApi.cw20token(this.apiName) }) {
            is NetworkResult.Success -> {
                response.data.assets
            }

            is NetworkResult.Error -> {
                mutableListOf()
            }
        }
    }

    private fun loadCw20Balance(channel: ManagedChannel, token: Token) {
        val stub = com.cosmwasm.wasm.v1.QueryGrpc.newBlockingStub(channel)
            .withDeadlineAfter(duration, TimeUnit.SECONDS)
        val req = Cw20Balance(address)
        val jsonData = Gson().toJson(req)
        val queryData = ByteString.copyFromUtf8(jsonData)

        val request = com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateRequest.newBuilder()
            .setAddress(token.address).setQueryData(queryData).build()

        stub.smartContractState(request)?.let { response ->
            val json = JSONObject(response.data.toStringUtf8())
            token.amount = json.get("balance").toString()
        }
    }

    private fun loadAllCw20Balance(id: Long) {
        val channel = getChannel()
        val scope = CoroutineScope(Dispatchers.Default)
        val deferredList = mutableListOf<Deferred<Unit>>()

        tokens.forEach { token ->
            val deferred = scope.async {
                loadCw20Balance(channel, token)
            }
            deferredList.add(deferred)
        }

        runBlocking {
            deferredList.awaitAll()

            val refAddress = RefAddress(id, tag, address, "0", "0", allAssetValue().toPlainString(), 0)
            BaseData.updateRefAddressesToken(refAddress)
        }

        try {
            channel.shutdown()
        } catch (_: Exception) {
        } finally {
            channel.shutdown()
            try {
                if (!channel.awaitTermination(5, TimeUnit.SECONDS)) {
                    channel.shutdownNow()
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    fun balanceAmount(denom: String): BigDecimal {
        if (cosmosBalances.isNotEmpty()) {
            return cosmosBalances.firstOrNull { it.denom == denom }?.amount?.toBigDecimal() ?: BigDecimal.ZERO
        }
        return BigDecimal.ZERO
    }

    fun balanceValue(denom: String): BigDecimal {
        BaseData.getAsset(apiName, denom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId)
            val amount = balanceAmount(denom)
            asset.decimals?.let { decimal ->
                return price.multiply(amount).movePointLeft(decimal)
                    .setScale(6, RoundingMode.DOWN)
            } ?: run {
                return BigDecimal.ZERO
            }
        }
        return BigDecimal.ZERO
    }

    fun balanceValueSum(): BigDecimal {
        var sum = BigDecimal.ZERO
        if (cosmosBalances.isNotEmpty()) {
            cosmosBalances.forEach { balance ->
                sum = sum.add(balanceValue(balance.denom))
            }
        }
        return sum
    }

    fun vestingAmount(denom: String): BigDecimal {
        if (cosmosVestings.isNotEmpty()) {
            return cosmosVestings.firstOrNull { it.denom == denom }?.amount?.toBigDecimal() ?: BigDecimal.ZERO
        }
        return BigDecimal.ZERO
    }

    private fun vestingValue(denom: String): BigDecimal {
        BaseData.getAsset(apiName, denom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId)
            val amount = vestingAmount(denom)
            asset.decimals?.let { decimal ->
                return price.multiply(amount).movePointLeft(decimal)
                    .setScale(6, RoundingMode.DOWN)
            }
        }
        return BigDecimal.ZERO
    }

    private fun vestingValueSum(): BigDecimal {
        var sum = BigDecimal.ZERO
        cosmosVestings.forEach { vesting ->
            sum = sum.add(vestingValue(vesting.denom))
        }
        return sum
    }

    fun delegationAmountSum(): BigDecimal {
        var sum = BigDecimal.ZERO
        cosmosDelegations.forEach { delegation ->
            sum = sum.add(delegation.balance.amount.toBigDecimal())
        }
        return sum
    }

    private fun delegationValueSum(): BigDecimal {
        stakeDenom?.let {
            BaseData.getAsset(apiName, it)?.let { asset ->
                val price = BaseData.getPrice(asset.coinGeckoId)
                val amount = delegationAmountSum()
                asset.decimals?.let { decimal ->
                    return price.multiply(amount).movePointLeft(decimal)
                        .setScale(6, RoundingMode.DOWN)
                }
            }
            return BigDecimal.ZERO
        }
        return BigDecimal.ZERO
    }

    fun unbondingAmountSum(): BigDecimal {
        var sum = BigDecimal.ZERO
        cosmosUnbondings.forEach { unbonding ->
            unbonding.entriesList.forEach { entry ->
                sum = sum.add(entry.balance.toBigDecimal())
            }
        }
        return sum
    }

   private fun unbondingValueSum(): BigDecimal {
        stakeDenom?.let {
            BaseData.getAsset(apiName, it)?.let { asset ->
                val price = BaseData.getPrice(asset.coinGeckoId)
                val amount = unbondingAmountSum()
                asset.decimals?.let { decimal ->
                    return price.multiply(amount).movePointLeft(decimal)
                        .setScale(6, RoundingMode.DOWN)
                }
            }
            return BigDecimal.ZERO
        }
        return BigDecimal.ZERO
    }

    fun rewardAmountSum(denom: String): BigDecimal {
        var sum = BigDecimal.ZERO
        cosmosRewards.forEach { reward ->
            val matchReward = reward.rewardList.firstOrNull { it.denom == denom }
            val rewardAmount = matchReward?.amount?.toBigDecimalOrNull() ?: BigDecimal.ZERO
            sum = sum.add(rewardAmount)
        }
        return sum.movePointLeft(18).setScale(0, RoundingMode.DOWN)
    }

    private fun rewardValue(denom: String): BigDecimal {
        BaseData.getAsset(apiName, denom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId)
            val amount = rewardAmountSum(denom)
            asset.decimals?.let { decimal ->
                return price.multiply(amount).movePointLeft(decimal)
                    .setScale(6, RoundingMode.DOWN)
            }
        }
        return BigDecimal.ZERO
    }

    fun rewardAllCoins(): List<Coin> {
        val allRewards = mutableListOf<Coin>()
        cosmosRewards.forEach { reward ->
            reward.rewardList.forEach { coin ->
                val calReward = Coin.newBuilder().setDenom(coin.denom).setAmount(
                    coin.amount.toBigDecimal().movePointLeft(18).setScale(0, RoundingMode.DOWN)
                        .toString()
                ).build()
                allRewards.add(calReward)
            }
        }
        return allRewards
    }

    fun rewardOtherDenoms(): Int {
        val otherDenoms = mutableListOf<String>()
        rewardAllCoins().forEach { coin ->
            if (!otherDenoms.contains(coin.denom)) {
                otherDenoms.add(coin.denom)
            }
        }
        otherDenoms.removeAll { it == stakeDenom }
        return otherDenoms.size
    }

    private fun rewardValueSum(): BigDecimal {
        var sum = BigDecimal.ZERO
        rewardAllCoins().forEach { rewardCoin ->
            BaseData.getAsset(apiName, rewardCoin.denom)?.let { asset ->
                val price = BaseData.getPrice(asset.coinGeckoId)
                val amount = rewardCoin.amount.toBigDecimal()
                val value = asset.decimals?.let {
                    price.multiply(amount)?.movePointLeft(it)?.setScale(6, RoundingMode.DOWN)
                }
                sum = sum.add(value)
            }
        }
        return sum
    }

    fun claimableRewards(): MutableList<DelegationDelegatorReward?> {
        val result = mutableListOf<DelegationDelegatorReward?>()
        cosmosRewards.forEach { reward ->
            for (i in 0 until reward.rewardCount) {
                val rewardAmount = reward.getReward(i).amount.toBigDecimal().movePointLeft(18).setScale(0, RoundingMode.DOWN)
                if (rewardAmount > BigDecimal.ONE) {
                    result.add(reward)
                    break
                }
            }
        }
        return result
    }

    open fun allStakingDenomAmount(): BigDecimal? {
        stakeDenom?.let {
            return balanceAmount(it).add(vestingAmount(it))?.add(delegationAmountSum())
                ?.add(unbondingAmountSum())?.add(rewardAmountSum(it))
        }
        return BigDecimal.ZERO
    }

    fun cw20Value(address: String): BigDecimal {
        val token = tokens.firstOrNull { it.address == address }
        if (token != null) {
            val price = BaseData.getPrice(token.coinGeckoId)
            return price.multiply(token.amount?.toBigDecimal()).movePointLeft(token.decimals)
                .setScale(6, RoundingMode.DOWN)
        }
        return BigDecimal.ZERO
    }

    private fun allCw20ValueSum(): BigDecimal {
        var sumValue = BigDecimal.ZERO
        tokens.forEach { token ->
            val price = BaseData.getPrice(token.coinGeckoId)
            val value = price.multiply(token.amount?.toBigDecimal()).movePointLeft(token.decimals)
                .setScale(6, RoundingMode.DOWN)
            sumValue = sumValue.add(value)
        }
        return sumValue
    }

    open fun denomValue(denom: String): BigDecimal {
        return if (denom == stakeDenom) {
            balanceValue(denom).add(vestingValue(denom)).add(rewardValue(denom))
                .add(delegationValueSum()).add(unbondingValueSum())

        } else {
            balanceValue(denom).add(vestingValue(denom)).add(rewardValue(denom))
        }
    }

    private suspend fun loadNodeInfo() {
        when (val response = safeApiCall { RetrofitInstance.beaconApi.nodeInfo() }) {
            is NetworkResult.Success -> {

            }

            is NetworkResult.Error -> {
                return
            }
        }
    }

    private suspend fun loadAccountInfo() {
        when (val response = safeApiCall { RetrofitInstance.beaconApi.accountInfo(address) }) {
            is NetworkResult.Success -> {
                lcdAccountInfo = response.data
            }

            is NetworkResult.Error -> {
                return
            }
        }
    }

    private suspend fun loadBeaconTokens() {
        when (val response = safeApiCall { RetrofitInstance.beaconApi.beaconTokens("1000") }) {
            is NetworkResult.Success -> {
                lcdBeaconTokens = response.data
            }

            is NetworkResult.Error -> {
                return
            }
        }
    }

    fun lcdBalanceAmount(denom: String): BigDecimal {
        val balance = lcdAccountInfo?.balances?.firstOrNull { it.symbol == denom }
        if (balance != null) {
            return balance.free.toBigDecimal()
        }
        return BigDecimal.ZERO
    }

    fun lcdBnbFrozenAmount(denom: String): BigDecimal {
        val balance = lcdAccountInfo?.balances?.firstOrNull { it.symbol == denom }
        if (balance != null) {
            return balance.frozen.toBigDecimal()
        }
        return BigDecimal.ZERO
    }

    fun lcdBnbLockedAmount(denom: String): BigDecimal {
        val balance = lcdAccountInfo?.balances?.firstOrNull { it.symbol == denom }
        if (balance != null) {
            return balance.locked.toBigDecimal()
        }
        return BigDecimal.ZERO
    }

    fun lcdBalanceValue(denom: String?): BigDecimal {
        denom?.let {
            if (it == stakeDenom) {
                val amount = lcdBalanceAmount(denom)
                val price = BaseData.getPrice(ChainBinanceBeacon().BNB_GECKO_ID)
                return price.multiply(amount).setScale(6, RoundingMode.DOWN)
            }
        }
        return BigDecimal.ZERO
    }

    fun lcdBalanceValueSum(): BigDecimal {
        var sumValue = BigDecimal.ZERO
        lcdAccountInfo?.balances?.forEach { balance ->
            sumValue = sumValue.add(lcdBalanceValue(balance.symbol))
        }
        return sumValue
    }

    fun monikerImg(opAddress: String?): String {
        return "$CHAIN_BASE_URL$apiName/moniker/$opAddress.png"
    }
}


fun allCosmosLines(): List<CosmosLine> {
    val lines = mutableListOf<CosmosLine>()
    lines.add(ChainCosmos())
    lines.add(ChainAkash())
    lines.add(ChainBinanceBeacon())
    lines.add(ChainEvmos())
    lines.add(ChainInjective())
    lines.add(ChainIris())
    lines.add(ChainJuno())
    lines.add(ChainKava459())
    lines.add(ChainKava118())
    lines.add(ChainLum118())
    lines.add(ChainNeutron())
    lines.add(ChainOsmosis())
    lines.add(ChainStride())

    lines.forEach { line ->
        line.chainId = BaseData.chains?.firstOrNull { it.chain == line.apiName }?.chainId.toString()
    }
    return lines
}

val DEFAULT_DISPLAY_COSMOS = mutableListOf("cosmos118", "osmosis118", "binance714", "evmos60")