package wannabit.io.cosmostaion.ui.viewmodel.tx

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cosmos.bank.v1beta1.TxProto.MsgSend
import com.cosmos.base.abci.v1beta1.AbciProto
import com.cosmos.base.tendermint.v1beta1.QueryProto.GetLatestBlockRequest
import com.cosmos.base.tendermint.v1beta1.ServiceGrpc
import com.cosmos.base.v1beta1.CoinProto.Coin
import com.cosmos.distribution.v1beta1.DistributionProto.DelegationDelegatorReward
import com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddress
import com.cosmos.gov.v1beta1.TxProto
import com.cosmos.staking.v1beta1.TxProto.MsgBeginRedelegate
import com.cosmos.staking.v1beta1.TxProto.MsgCancelUnbondingDelegation
import com.cosmos.staking.v1beta1.TxProto.MsgDelegate
import com.cosmos.staking.v1beta1.TxProto.MsgUndelegate
import com.cosmos.tx.v1beta1.ServiceProto.SimulateResponse
import com.cosmos.tx.v1beta1.TxProto.Fee
import com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract
import com.ibc.applications.transfer.v1.TxProto.MsgTransfer
import com.ibc.core.channel.v1.QueryGrpc
import com.ibc.core.channel.v1.QueryProto
import com.ibc.core.client.v1.ClientProto
import com.ibc.lightclients.tendermint.v1.TendermintProto
import com.kava.cdp.v1beta1.TxProto.MsgCreateCDP
import com.kava.cdp.v1beta1.TxProto.MsgDeposit
import com.kava.cdp.v1beta1.TxProto.MsgDrawDebt
import com.kava.cdp.v1beta1.TxProto.MsgRepayDebt
import com.kava.cdp.v1beta1.TxProto.MsgWithdraw
import com.kava.hard.v1beta1.TxProto.MsgBorrow
import com.kava.hard.v1beta1.TxProto.MsgRepay
import com.kava.incentive.v1beta1.QueryProto.QueryRewardsResponse
import com.kava.router.v1beta1.TxProto.MsgDelegateMintDeposit
import com.kava.router.v1beta1.TxProto.MsgWithdrawBurn
import io.grpc.ManagedChannel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.web3j.protocol.Web3j
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.data.model.req.LFee
import wannabit.io.cosmostaion.data.model.req.Msg
import wannabit.io.cosmostaion.data.model.res.AssetPath
import wannabit.io.cosmostaion.data.model.res.LegacyRes
import wannabit.io.cosmostaion.data.model.res.NameService
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.data.repository.tx.TxRepository
import wannabit.io.cosmostaion.ui.tx.step.SendAssetType
import wannabit.io.cosmostaion.ui.viewmodel.event.SingleLiveEvent
import java.util.concurrent.TimeUnit

class TxViewModel(private val txRepository: TxRepository) : ViewModel() {

    var nameServices = SingleLiveEvent<MutableList<NameService>>()

    fun icnsAddress(recipientChain: CosmosLine, userInput: String, prefix: String) =
        viewModelScope.launch(Dispatchers.IO) {
            val nameServiceList = mutableListOf<NameService>()
            when (recipientChain) {
//                is ChainStargaze -> {
//                    val osIcnsDeferred = async {
//                        txRepository.osIcnsAddress(
//                            getChannel(ChainOsmosis()), userInput, prefix
//                        )
//                    }
//                    val starIcnsDeferred = async {
//                        txRepository.sgIcnsAddress(
//                            getChannel(ChainStargaze()), userInput
//                        )
//                    }
//                    val responses = awaitAll(osIcnsDeferred, starIcnsDeferred)
//                    if (responses[0]?.isNotEmpty() == true) {
//                        nameServiceList.add(
//                            NameService(
//                                NameService.NameServiceType.ICNS, userInput, responses[0].toString()
//                            )
//                        )
//                    }
//                    if (responses[1]?.isNotEmpty() == true) {
//                        nameServiceList.add(
//                            NameService(
//                                NameService.NameServiceType.STARGAZE,
//                                userInput,
//                                responses[1].toString()
//                            )
//                        )
//                    }
//                    nameServices.postValue(nameServiceList)
//                }

//                is ChainArchway -> {
//                    val osIcnsDeferred = async {
//                        txRepository.osIcnsAddress(
//                            getChannel(ChainOsmosis()), userInput, prefix
//                        )
//                    }
//                    val archIcnsDeferred = async {
//                        txRepository.archIcnsAddress(
//                            getChannel(ChainArchway()), userInput
//                        )
//                    }
//
//                    val responses = awaitAll(osIcnsDeferred, archIcnsDeferred)
//                    if (responses[0]?.isNotEmpty() == true) {
//                        nameServiceList.add(
//                            NameService(
//                                NameService.NameServiceType.ICNS, userInput, responses[0].toString()
//                            )
//                        )
//                    }
//                    if (responses[1]?.isNotEmpty() == true) {
//                        nameServiceList.add(
//                            NameService(
//                                NameService.NameServiceType.ARCHWAY,
//                                userInput,
//                                responses[1].toString()
//                            )
//                        )
//                    }
//                    nameServices.postValue(nameServiceList)
//                }

                else -> {
//                    val osIcnsDeferred = async {
//                        txRepository.osIcnsAddress(
//                            getChannel(ChainOsmosis()), userInput, prefix
//                        )
//                    }
//                    val response = osIcnsDeferred.await()
//                    if (response?.isNotEmpty() == true) {
//                        nameServiceList.add(
//                            NameService(
//                                NameService.NameServiceType.ICNS, userInput, response.toString()
//                            )
//                        )
//                    }
                    nameServices.postValue(nameServiceList)
                }
            }
        }

    val broadcastEvmSendTx = SingleLiveEvent<String?>()
    fun broadcastEvmSend(
        web3j: Web3j, hexValue: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        val response = txRepository.broadcastEvmSendTx(web3j, hexValue)
        broadcastEvmSendTx.postValue(response)
    }

    val simulateEvmSend = SingleLiveEvent<Pair<String?, String?>>()
    val erc20ErrorMessage = SingleLiveEvent<Pair<String?, String?>>()

    fun simulateEvmSend(
        toEthAddress: String?,
        toSendAmount: String?,
        selectedToken: Token?,
        sendAssetType: SendAssetType,
        selectedChain: CosmosLine,
        selectedFeeInfo: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        val response = txRepository.simulateEvmSendTx(
            toEthAddress, toSendAmount, selectedToken, sendAssetType, selectedChain, selectedFeeInfo
        )
        if (response.second?.isNotEmpty() == true) {
            simulateEvmSend.postValue(response)
        } else {
            erc20ErrorMessage.postValue(response)
        }
    }

    val broadcastEvmDelegateTx = SingleLiveEvent<String?>()
    fun broadcastEvmDelegate(
        web3j: Web3j, hexValue: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        val response = txRepository.broadcastEvmDelegateTx(web3j, hexValue)
        broadcastEvmDelegateTx.postValue(response)
    }

    val simulateEvmDelegate = SingleLiveEvent<Pair<String?, String?>>()

    fun simulateEvmDelegate(
        toValidatorEthAddress: String?,
        toDelegateAmount: String?,
        selectedChain: EthereumLine,
        selectedFeeInfo: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        val response = txRepository.simulateEvmDelegateTx(
            toValidatorEthAddress, toDelegateAmount, selectedChain, selectedFeeInfo
        )
        if (response.second?.isNotEmpty() == true) {
            simulateEvmDelegate.postValue(response)
        } else {
            erc20ErrorMessage.postValue(response)
        }
    }

    val broadcastEvmUnDelegateTx = SingleLiveEvent<String?>()
    fun broadcastEvmUnDelegate(
        web3j: Web3j, hexValue: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        val response = txRepository.broadcastEvmUnDelegateTx(web3j, hexValue)
        broadcastEvmUnDelegateTx.postValue(response)
    }

    val simulateEvmUnDelegate = SingleLiveEvent<Pair<String?, String?>>()

    fun simulateEvmUnDelegate(
        validatorEthAddress: String?,
        toUnDelegateAmount: String?,
        selectedChain: EthereumLine,
        selectedFeeInfo: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        val response = txRepository.simulateEvmUnDelegateTx(
            validatorEthAddress, toUnDelegateAmount, selectedChain, selectedFeeInfo
        )
        if (response.second?.isNotEmpty() == true) {
            simulateEvmUnDelegate.postValue(response)
        } else {
            erc20ErrorMessage.postValue(response)
        }
    }

    val broadcastEvmReDelegateTx = SingleLiveEvent<String?>()
    fun broadcastEvmReDelegate(
        web3j: Web3j, hexValue: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        val response = txRepository.broadcastEvmReDelegateTx(web3j, hexValue)
        broadcastEvmReDelegateTx.postValue(response)
    }

    val simulateEvmReDelegate = SingleLiveEvent<Pair<String?, String?>>()

    fun simulateEvmReDelegate(
        fromValidatorEthAddress: String?,
        toValidatorEthAddress: String?,
        toReDelegateAmount: String?,
        selectedChain: EthereumLine,
        selectedFeeInfo: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        val response = txRepository.simulateEvmReDelegateTx(
            fromValidatorEthAddress,
            toValidatorEthAddress,
            toReDelegateAmount,
            selectedChain,
            selectedFeeInfo
        )
        if (response.second?.isNotEmpty() == true) {
            simulateEvmReDelegate.postValue(response)
        } else {
            erc20ErrorMessage.postValue(response)
        }
    }

    val broadcastEvmCancelUnStakingTx = SingleLiveEvent<String?>()
    fun broadcastEvmCancelUnStaking(
        web3j: Web3j, hexValue: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        val response = txRepository.broadcastEvmCancelUnStakingTx(web3j, hexValue)
        broadcastEvmCancelUnStakingTx.postValue(response)
    }

    val simulateEvmCancelUnStaking = SingleLiveEvent<Pair<String?, String?>>()

    fun simulateEvmCancelUnStaking(
        validatorEthAddress: String?,
        unDelegateAmount: String?,
        height: Long,
        selectedChain: EthereumLine,
        selectedFeeInfo: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        val response = txRepository.simulateEvmCancelUnStakingTx(
            validatorEthAddress, unDelegateAmount, height, selectedChain, selectedFeeInfo
        )
        if (response.second?.isNotEmpty() == true) {
            simulateEvmCancelUnStaking.postValue(response)
        } else {
            erc20ErrorMessage.postValue(response)
        }
    }

    val broadcastEvmVoteTx = SingleLiveEvent<String?>()
    fun broadcastEvmVote(
        web3j: Web3j, hexValue: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        val response = txRepository.broadcastEvmRVoteTx(web3j, hexValue)
        broadcastEvmVoteTx.postValue(response)
    }

    val simulateEvmVote = SingleLiveEvent<Pair<String?, String?>>()

    fun simulateEvmVote(
        proposalId: Long, proposalOption: Long, selectedChain: EthereumLine, selectedFeeInfo: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        val response = txRepository.simulateEvmVoteTx(
            proposalId, proposalOption, selectedChain, selectedFeeInfo
        )
        if (response.second?.isNotEmpty() == true) {
            simulateEvmVote.postValue(response)
        } else {
            erc20ErrorMessage.postValue(response)
        }
    }

    val errorMessage = SingleLiveEvent<String>()

    val broadcastTx = SingleLiveEvent<AbciProto.TxResponse>()

    val simulate = SingleLiveEvent<AbciProto.GasInfo>()

    fun broadcastSend(
        managedChannel: ManagedChannel?,
        address: String?,
        msgSend: MsgSend?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastSendTx(
                managedChannel, it, msgSend, fee, memo, selectedChain
            )
            broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateSend(
        managedChannel: ManagedChannel?,
        address: String?,
        msgSend: MsgSend?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateSendTx(
                    managedChannel, it, msgSend, fee, memo, selectedChain
                ) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateSendTx(
                    managedChannel, it, msgSend, fee, memo, selectedChain
                ) as String
                errorMessage.postValue(errorResponse)
            }
        } ?: run {
            errorMessage.postValue("No key account")
        }
    }

    fun broadcastIbcSend(
        managedChannel: ManagedChannel?,
        recipientChannel: ManagedChannel?,
        toAddress: String?,
        assetPath: AssetPath?,
        toSendDenom: String?,
        toSendAmount: String?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, selectedChain?.address)?.let {
            try {
                val blockStub = ServiceGrpc.newBlockingStub(recipientChannel)
                    .withDeadlineAfter(8L, TimeUnit.SECONDS)
                val blockRequest = GetLatestBlockRequest.newBuilder().build()
                val lastBlock = blockStub.getLatestBlock(blockRequest)

                val ibcClientStub = QueryGrpc.newBlockingStub(managedChannel)
                    .withDeadlineAfter(8L, TimeUnit.SECONDS)
                val ibcClientRequest = QueryProto.QueryChannelClientStateRequest.newBuilder()
                    .setChannelId(assetPath?.channel).setPortId(assetPath?.port).build()
                val ibcClientResponse = ibcClientStub.channelClientState(ibcClientRequest)
                val lastHeight =
                    TendermintProto.ClientState.parseFrom(ibcClientResponse.identifiedClientState.clientState.value).latestHeight
                val height =
                    ClientProto.Height.newBuilder().setRevisionNumber(lastHeight.revisionNumber)
                        .setRevisionHeight(lastBlock.block.header.height + 200)

                val sendCoin =
                    Coin.newBuilder().setDenom(toSendDenom).setAmount(toSendAmount).build()

                val msgTransfer = MsgTransfer.newBuilder().setSender(selectedChain?.address)
                    .setReceiver(toAddress).setSourceChannel(assetPath?.channel)
                    .setSourcePort(assetPath?.port).setTimeoutHeight(height).setTimeoutTimestamp(0)
                    .setToken(sendCoin).build()

                val response = txRepository.broadcastIbcSendTx(
                    managedChannel, it, msgTransfer, fee, memo, selectedChain
                )
                broadcastTx.postValue(response?.txResponse)
            } catch (e: Exception) {
                errorMessage.postValue("Unknown Error")
            }
        }
    }

    fun simulateIbcSend(
        managedChannel: ManagedChannel?,
        recipientChannel: ManagedChannel?,
        fromAddress: String?,
        toAddress: String?,
        assetPath: AssetPath?,
        toSendDenom: String?,
        toSendAmount: String?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, fromAddress)?.let {
            try {
                val blockStub = ServiceGrpc.newBlockingStub(recipientChannel)
                    .withDeadlineAfter(8L, TimeUnit.SECONDS)
                val blockRequest = GetLatestBlockRequest.newBuilder().build()
                val lastBlock = blockStub.getLatestBlock(blockRequest)

                val ibcClientStub = QueryGrpc.newBlockingStub(managedChannel)
                    .withDeadlineAfter(8L, TimeUnit.SECONDS)
                val ibcClientRequest = QueryProto.QueryChannelClientStateRequest.newBuilder()
                    .setChannelId(assetPath?.channel).setPortId(assetPath?.port).build()
                val ibcClientResponse = ibcClientStub.channelClientState(ibcClientRequest)
                val lastHeight =
                    TendermintProto.ClientState.parseFrom(ibcClientResponse.identifiedClientState.clientState.value).latestHeight
                val height =
                    ClientProto.Height.newBuilder().setRevisionNumber(lastHeight.revisionNumber)
                        .setRevisionHeight(lastBlock.block.header.height + 200)

                val sendCoin =
                    Coin.newBuilder().setDenom(toSendDenom).setAmount(toSendAmount).build()

                val msgTransfer =
                    MsgTransfer.newBuilder().setSender(fromAddress).setReceiver(toAddress)
                        .setSourceChannel(assetPath?.channel).setSourcePort(assetPath?.port)
                        .setTimeoutHeight(height).setTimeoutTimestamp(0).setToken(sendCoin).build()

                val response = txRepository.simulateIbcSendTx(
                    managedChannel, it, msgTransfer, fee, memo, selectedChain
                )
                when (response) {
                    is SimulateResponse -> simulate.postValue(response.gasInfo)
                    is String -> errorMessage.postValue(response as? String)
                    else -> errorMessage.postValue("Unknown Error")
                }
            } catch (e: Exception) {
                errorMessage.postValue("Unknown Error")
            }
        } ?: run {
            errorMessage.postValue("No key account")
        }
    }

    fun broadDelegate(
        managedChannel: ManagedChannel?,
        address: String?,
        msgDelegate: MsgDelegate?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastDelegateTx(
                managedChannel, it, msgDelegate, fee, memo, selectedChain
            )
            broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateDelegate(
        managedChannel: ManagedChannel?,
        address: String?,
        msgDelegate: MsgDelegate,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateDelegateTx(
                    managedChannel, it, msgDelegate, fee, memo, selectedChain
                ) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateDelegateTx(
                    managedChannel, it, msgDelegate, fee, memo, selectedChain
                ) as String
                errorMessage.postValue(errorResponse)
            }
        } ?: run {
            errorMessage.postValue("No key account")
        }
    }

    fun broadUnDelegate(
        managedChannel: ManagedChannel?,
        address: String?,
        msgUnDelegate: MsgUndelegate?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastUnDelegateTx(
                managedChannel, it, msgUnDelegate, fee, memo, selectedChain
            )
            broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateUnDelegate(
        managedChannel: ManagedChannel?,
        address: String?,
        msgUnDelegate: MsgUndelegate?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateUnDelegateTx(
                    managedChannel, it, msgUnDelegate, fee, memo, selectedChain
                ) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateUnDelegateTx(
                    managedChannel, it, msgUnDelegate, fee, memo, selectedChain
                ) as String
                errorMessage.postValue(errorResponse)
            }
        } ?: run {
            errorMessage.postValue("No key account")
        }
    }

    fun broadReDelegate(
        managedChannel: ManagedChannel?,
        address: String?,
        msgReDelegate: MsgBeginRedelegate?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastReDelegateTx(
                managedChannel, it, msgReDelegate, fee, memo, selectedChain
            )
            broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateReDelegate(
        managedChannel: ManagedChannel?,
        address: String?,
        msgReDelegate: MsgBeginRedelegate?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateReDelegateTx(
                    managedChannel, it, msgReDelegate, fee, memo, selectedChain
                ) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateReDelegateTx(
                    managedChannel, it, msgReDelegate, fee, memo, selectedChain
                ) as String
                errorMessage.postValue(errorResponse)
            }
        } ?: run {
            errorMessage.postValue("No key account")
        }
    }

    fun broadCancelUnbonding(
        managedChannel: ManagedChannel?,
        address: String?,
        msgCancelUnbondingDelegation: MsgCancelUnbondingDelegation?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastCancelUnbondingTx(
                managedChannel, it, msgCancelUnbondingDelegation, fee, memo, selectedChain
            )
            broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateCancelUnbonding(
        managedChannel: ManagedChannel?,
        address: String?,
        msgCancelUnbondingDelegation: MsgCancelUnbondingDelegation?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateCancelUnbondingTx(
                    managedChannel, it, msgCancelUnbondingDelegation, fee, memo, selectedChain
                ) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateCancelUnbondingTx(
                    managedChannel, it, msgCancelUnbondingDelegation, fee, memo, selectedChain
                ) as String
                errorMessage.postValue(errorResponse)
            }
        } ?: run {
            errorMessage.postValue("No key account")
        }
    }

    fun broadGetRewards(
        managedChannel: ManagedChannel?,
        address: String?,
        rewards: MutableList<DelegationDelegatorReward?>,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastGetRewardsTx(
                managedChannel, it, rewards, fee, memo, selectedChain
            )
            broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateGetRewards(
        managedChannel: ManagedChannel?,
        address: String?,
        rewards: MutableList<DelegationDelegatorReward?>,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateGetRewardsTx(
                    managedChannel, it, rewards, fee, memo, selectedChain
                ) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateGetRewardsTx(
                    managedChannel, it, rewards, fee, memo, selectedChain
                ) as String
                errorMessage.postValue(errorResponse)
            }
        } ?: run {
            errorMessage.postValue("No key account")
        }
    }

    fun broadCompounding(
        managedChannel: ManagedChannel?,
        address: String?,
        rewards: MutableList<DelegationDelegatorReward?>,
        stakingDenom: String?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastCompoundingTx(
                managedChannel, it, rewards, stakingDenom, fee, memo, selectedChain
            )
            broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateCompounding(
        managedChannel: ManagedChannel?,
        address: String?,
        rewards: MutableList<DelegationDelegatorReward?>,
        stakingDenom: String?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateCompoundingTx(
                    managedChannel, it, rewards, stakingDenom, fee, memo, selectedChain
                ) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateCompoundingTx(
                    managedChannel, it, rewards, stakingDenom, fee, memo, selectedChain
                ) as String
                errorMessage.postValue(errorResponse)
            }
        } ?: run {
            errorMessage.postValue("No key account")
        }
    }

    fun broadChangeRewardAddress(
        managedChannel: ManagedChannel?,
        address: String?,
        msgSetWithdrawAddress: MsgSetWithdrawAddress?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastChangeRewardAddressTx(
                managedChannel, it, msgSetWithdrawAddress, fee, memo, selectedChain
            )
            broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateChangeRewardAddress(
        managedChannel: ManagedChannel?,
        address: String?,
        msgSetWithdrawAddress: MsgSetWithdrawAddress?,
        fee: Fee?,
        memo: String,
        selectedChain: BaseChain?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateChangeRewardAddressTx(
                    managedChannel, it, msgSetWithdrawAddress, fee, memo, selectedChain
                ) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateChangeRewardAddressTx(
                    managedChannel, it, msgSetWithdrawAddress, fee, memo, selectedChain
                ) as String
                errorMessage.postValue(errorResponse)
            }
        } ?: run {
            errorMessage.postValue("No key account")
        }
    }

    fun broadVote(
        managedChannel: ManagedChannel?,
        address: String?,
        msgVotes: MutableList<TxProto.MsgVote?>?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastVoteTx(
                managedChannel, it, msgVotes, fee, memo, selectedChain
            )
            broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateVote(
        managedChannel: ManagedChannel?,
        address: String?,
        msgVotes: MutableList<TxProto.MsgVote?>?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateVoteTx(
                    managedChannel, it, msgVotes, fee, memo, selectedChain
                ) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateVoteTx(
                    managedChannel, it, msgVotes, fee, memo, selectedChain
                ) as String
                errorMessage.postValue(errorResponse)
            }
        } ?: run {
            errorMessage.postValue("No key account")
        }
    }

    fun broadcastWasm(
        managedChannel: ManagedChannel?,
        msgWasms: MutableList<MsgExecuteContract?>?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, selectedChain?.address)?.let {
            val response = txRepository.broadcastWasmTx(
                managedChannel, it, msgWasms, fee, memo, selectedChain
            )
            broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateWasm(
        managedChannel: ManagedChannel?,
        address: String?,
        msgWasms: MutableList<MsgExecuteContract?>?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateWasmTx(
                    managedChannel, it, msgWasms, fee, memo, selectedChain
                ) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateWasmTx(
                    managedChannel, it, msgWasms, fee, memo, selectedChain
                ) as String
                errorMessage.postValue(errorResponse)
            }
        } ?: run {
            errorMessage.postValue("No key account")
        }
    }

    fun broadClaimIncentive(
        managedChannel: ManagedChannel?,
        address: String?,
        incentive: QueryRewardsResponse,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastClaimIncentiveTx(
                managedChannel, it, incentive, fee, memo, selectedChain
            )
            broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateClaimIncentive(
        managedChannel: ManagedChannel?,
        address: String?,
        incentive: QueryRewardsResponse,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateClaimIncentiveTx(
                    managedChannel, it, incentive, fee, memo, selectedChain
                ) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateClaimIncentiveTx(
                    managedChannel, it, incentive, fee, memo, selectedChain
                ) as String
                errorMessage.postValue(errorResponse)
            }
        } ?: run {
            errorMessage.postValue("No key account")
        }
    }

    fun broadMintCreate(
        managedChannel: ManagedChannel?,
        address: String?,
        msgCreateCDP: MsgCreateCDP?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastMintCreateTx(
                managedChannel, it, msgCreateCDP, fee, memo, selectedChain
            )
            broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateMintCreate(
        managedChannel: ManagedChannel?,
        address: String?,
        msgCreateCDP: MsgCreateCDP?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateMintCreateTx(
                    managedChannel, it, msgCreateCDP, fee, memo, selectedChain
                ) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateMintCreateTx(
                    managedChannel, it, msgCreateCDP, fee, memo, selectedChain
                ) as String
                errorMessage.postValue(errorResponse)
            }
        } ?: run {
            errorMessage.postValue("No key account")
        }
    }

    fun broadMintDeposit(
        managedChannel: ManagedChannel?,
        address: String?,
        msgDeposit: MsgDeposit?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastMintDepositTx(
                managedChannel, it, msgDeposit, fee, memo, selectedChain
            )
            broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateMintDeposit(
        managedChannel: ManagedChannel?,
        address: String?,
        msgDeposit: MsgDeposit?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateMintDepositTx(
                    managedChannel, it, msgDeposit, fee, memo, selectedChain
                ) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateMintDepositTx(
                    managedChannel, it, msgDeposit, fee, memo, selectedChain
                ) as String
                errorMessage.postValue(errorResponse)
            }
        } ?: run {
            errorMessage.postValue("No key account")
        }
    }

    fun broadMintWithdraw(
        managedChannel: ManagedChannel?,
        address: String?,
        msgWithdraw: MsgWithdraw?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastMintWithdrawTx(
                managedChannel, it, msgWithdraw, fee, memo, selectedChain
            )
            broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateMintWithdraw(
        managedChannel: ManagedChannel?,
        address: String?,
        msgWithdraw: MsgWithdraw?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateMintWithdrawTx(
                    managedChannel, it, msgWithdraw, fee, memo, selectedChain
                ) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateMintWithdrawTx(
                    managedChannel, it, msgWithdraw, fee, memo, selectedChain
                ) as String
                errorMessage.postValue(errorResponse)
            }
        } ?: run {
            errorMessage.postValue("No key account")
        }
    }

    fun broadMintBorrow(
        managedChannel: ManagedChannel?,
        address: String?,
        msgDrawDebt: MsgDrawDebt?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastMintBorrowTx(
                managedChannel, it, msgDrawDebt, fee, memo, selectedChain
            )
            broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateMintBorrow(
        managedChannel: ManagedChannel?,
        address: String?,
        msgDrawDebt: MsgDrawDebt?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateMintBorrowTx(
                    managedChannel, it, msgDrawDebt, fee, memo, selectedChain
                ) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateMintBorrowTx(
                    managedChannel, it, msgDrawDebt, fee, memo, selectedChain
                ) as String
                errorMessage.postValue(errorResponse)
            }
        } ?: run {
            errorMessage.postValue("No key account")
        }
    }

    fun broadMintRepay(
        managedChannel: ManagedChannel?,
        address: String?,
        msgRepayDebt: MsgRepayDebt?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastMintRepayTx(
                managedChannel, it, msgRepayDebt, fee, memo, selectedChain
            )
            broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateMintRepay(
        managedChannel: ManagedChannel?,
        address: String?,
        msgRepayDebt: MsgRepayDebt?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateMintRepayTx(
                    managedChannel, it, msgRepayDebt, fee, memo, selectedChain
                ) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateMintRepayTx(
                    managedChannel, it, msgRepayDebt, fee, memo, selectedChain
                ) as String
                errorMessage.postValue(errorResponse)
            }
        } ?: run {
            errorMessage.postValue("No key account")
        }
    }

    fun broadLendDeposit(
        managedChannel: ManagedChannel?,
        address: String?,
        msgDeposit: com.kava.hard.v1beta1.TxProto.MsgDeposit?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastLendDepositTx(
                managedChannel, it, msgDeposit, fee, memo, selectedChain
            )
            broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateLendDeposit(
        managedChannel: ManagedChannel?,
        address: String?,
        msgDeposit: com.kava.hard.v1beta1.TxProto.MsgDeposit?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateLendDepositTx(
                    managedChannel, it, msgDeposit, fee, memo, selectedChain
                ) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateLendDepositTx(
                    managedChannel, it, msgDeposit, fee, memo, selectedChain
                ) as String
                errorMessage.postValue(errorResponse)
            }
        } ?: run {
            errorMessage.postValue("No key account")
        }
    }

    fun broadLendWithdraw(
        managedChannel: ManagedChannel?,
        address: String?,
        msgWithdraw: com.kava.hard.v1beta1.TxProto.MsgWithdraw?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastLendWithdrawTx(
                managedChannel, it, msgWithdraw, fee, memo, selectedChain
            )
            broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateLendWithdraw(
        managedChannel: ManagedChannel?,
        address: String?,
        msgWithdraw: com.kava.hard.v1beta1.TxProto.MsgWithdraw?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateLendWithdrawTx(
                    managedChannel, it, msgWithdraw, fee, memo, selectedChain
                ) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateLendWithdrawTx(
                    managedChannel, it, msgWithdraw, fee, memo, selectedChain
                ) as String
                errorMessage.postValue(errorResponse)
            }
        } ?: run {
            errorMessage.postValue("No key account")
        }
    }

    fun broadLendBorrow(
        managedChannel: ManagedChannel?,
        address: String?,
        msgBorrow: MsgBorrow?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastLendBorrowTx(
                managedChannel, it, msgBorrow, fee, memo, selectedChain
            )
            broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateLendBorrow(
        managedChannel: ManagedChannel?,
        address: String?,
        msgBorrow: MsgBorrow?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateLendBorrowTx(
                    managedChannel, it, msgBorrow, fee, memo, selectedChain
                ) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateLendBorrowTx(
                    managedChannel, it, msgBorrow, fee, memo, selectedChain
                ) as String
                errorMessage.postValue(errorResponse)
            }
        } ?: run {
            errorMessage.postValue("No key account")
        }
    }

    fun broadLendRepay(
        managedChannel: ManagedChannel?,
        address: String?,
        msgRepay: MsgRepay?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastLendRepayTx(
                managedChannel, it, msgRepay, fee, memo, selectedChain
            )
            broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateLendRepay(
        managedChannel: ManagedChannel?,
        address: String?,
        msgRepay: MsgRepay?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateLendRepayTx(
                    managedChannel, it, msgRepay, fee, memo, selectedChain
                ) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateLendRepayTx(
                    managedChannel, it, msgRepay, fee, memo, selectedChain
                ) as String
                errorMessage.postValue(errorResponse)
            }
        } ?: run {
            errorMessage.postValue("No key account")
        }
    }

    fun broadPoolDeposit(
        managedChannel: ManagedChannel?,
        address: String?,
        msgDeposit: com.kava.swap.v1beta1.TxProto.MsgDeposit?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastPoolDepositTx(
                managedChannel, it, msgDeposit, fee, memo, selectedChain
            )
            broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulatePoolDeposit(
        managedChannel: ManagedChannel?,
        address: String?,
        msgDeposit: com.kava.swap.v1beta1.TxProto.MsgDeposit?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulatePoolDepositTx(
                    managedChannel, it, msgDeposit, fee, memo, selectedChain
                ) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulatePoolDepositTx(
                    managedChannel, it, msgDeposit, fee, memo, selectedChain
                ) as String
                errorMessage.postValue(errorResponse)
            }
        } ?: run {
            errorMessage.postValue("No key account")
        }
    }

    fun broadPoolWithdraw(
        managedChannel: ManagedChannel?,
        address: String?,
        msgWithdraw: com.kava.swap.v1beta1.TxProto.MsgWithdraw?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastPoolWithdrawTx(
                managedChannel, it, msgWithdraw, fee, memo, selectedChain
            )
            broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulatePoolWithdraw(
        managedChannel: ManagedChannel?,
        address: String?,
        msgWithdraw: com.kava.swap.v1beta1.TxProto.MsgWithdraw?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulatePoolWithdrawTx(
                    managedChannel, it, msgWithdraw, fee, memo, selectedChain
                ) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulatePoolWithdrawTx(
                    managedChannel, it, msgWithdraw, fee, memo, selectedChain
                ) as String
                errorMessage.postValue(errorResponse)
            }
        } ?: run {
            errorMessage.postValue("No key account")
        }
    }

    fun broadEarnDeposit(
        managedChannel: ManagedChannel?,
        address: String?,
        msgDeposit: MsgDelegateMintDeposit?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastEarnDepositTx(
                managedChannel, it, msgDeposit, fee, memo, selectedChain
            )
            broadcastTx.postValue(response?.txResponse)
        }
    }

    fun broadEarnWithdraw(
        managedChannel: ManagedChannel?,
        address: String?,
        msgWithdraw: MsgWithdrawBurn?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            val response = txRepository.broadcastEarnWithdrawTx(
                managedChannel, it, msgWithdraw, fee, memo, selectedChain
            )
            broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateEarnDeposit(
        managedChannel: ManagedChannel?,
        address: String?,
        msgDeposit: MsgDelegateMintDeposit?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateEarnDepositTx(
                    managedChannel, it, msgDeposit, fee, memo, selectedChain
                ) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateEarnDepositTx(
                    managedChannel, it, msgDeposit, fee, memo, selectedChain
                ) as String
                errorMessage.postValue(errorResponse)
            }
        } ?: run {
            errorMessage.postValue("No key account")
        }
    }

    fun simulateEarnWithdraw(
        managedChannel: ManagedChannel?,
        address: String?,
        msgWithdraw: MsgWithdrawBurn?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, address)?.let {
            try {
                val response = txRepository.simulateEarnWithdrawTx(
                    managedChannel, it, msgWithdraw, fee, memo, selectedChain
                ) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateEarnWithdrawTx(
                    managedChannel, it, msgWithdraw, fee, memo, selectedChain
                ) as String
                errorMessage.postValue(errorResponse)
            }
        } ?: run {
            errorMessage.postValue("No key account")
        }
    }

    val broadcastOktTx = SingleLiveEvent<LegacyRes?>()
    fun broadcastOktTx(msgs: MutableList<Msg>, fee: LFee, memo: String, selectedChain: CosmosLine) =
        viewModelScope.launch(Dispatchers.IO) {
            val response = txRepository.broadcastOktTx(msgs, fee, memo, selectedChain)
            broadcastOktTx.postValue(response)
        }

    fun broadcastSkipIbcSend(
        managedChannel: ManagedChannel?,
        msgTransfer: MsgTransfer?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, selectedChain?.address)?.let {
            val response = txRepository.broadcastIbcSendTx(
                managedChannel, it, msgTransfer, fee, memo, selectedChain
            )
            broadcastTx.postValue(response?.txResponse)
        }
    }

    fun simulateSkipIbcSend(
        managedChannel: ManagedChannel?,
        fromAddress: String?,
        msgTransfer: MsgTransfer?,
        fee: Fee?,
        memo: String,
        selectedChain: CosmosLine?
    ) = viewModelScope.launch(Dispatchers.IO) {
        txRepository.auth(managedChannel, fromAddress)?.let {
            try {
                val response = txRepository.simulateIbcSendTx(
                    managedChannel, it, msgTransfer, fee, memo, selectedChain
                ) as SimulateResponse
                simulate.postValue(response.gasInfo)
            } catch (e: Exception) {
                val errorResponse = txRepository.simulateIbcSendTx(
                    managedChannel, it, msgTransfer, fee, memo, selectedChain
                ) as String
                errorMessage.postValue(errorResponse)
            }
        } ?: run {
            errorMessage.postValue("No key account")
        }
    }
}