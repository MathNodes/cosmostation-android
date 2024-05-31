package wannabit.io.cosmostaion.chain

import android.net.Uri
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import java.io.Serializable
import java.math.BigDecimal

open class BaseChain : Serializable {

    open var name: String = ""
    open var tag: String = ""
    open var chainIdCosmos: String = ""
    open var logo: Int = R.drawable.token_default
    open var swipeLogo: Int = -1
    open var isDefault: Boolean = true
    open var apiName: String = ""
    open var accountPrefix: String? = ""

    open lateinit var accountKeyType: AccountKeyType
    open var publicKey: ByteArray? = null
    open var privateKey: ByteArray? = null
    open var setParentPath: List<ChildNumber> = mutableListOf()
    open var address: String? = ""

    open var fetched = false

    fun getHDPath(lastPath: String): String {
        return accountKeyType.hdPath.replace("X", lastPath)
    }

    open fun setInfoWithSeed(seed: ByteArray?, parentPath: List<ChildNumber>, lastPath: String) {}

    open fun setInfoWithPrivateKey(privateKey: ByteArray?) {}

    open fun tokenValue(address: String, isUsd: Boolean? = false): BigDecimal { return BigDecimal.ZERO }

    open fun allTokenValue(isUsd: Boolean? = false): BigDecimal { return BigDecimal.ZERO }

    open fun allAssetValue(isUsd: Boolean?): BigDecimal { return BigDecimal.ZERO }

    open fun allValue(isUsd: Boolean?): BigDecimal { return BigDecimal.ZERO }

    open fun explorerAccount(): Uri? { return null }

    open fun explorerTx(hash: String?): Uri? { return null }

    open fun explorerProposal(id: String?): Uri? { return null }
}

data class AccountKeyType(
    var pubkeyType: PubKeyType,
    var hdPath: String
)

enum class PubKeyType { ETH_KECCAK256, COSMOS_SECP256K1, BERA_SECP256K1, SUI_ED25519, NONE }

fun allIbcChains(): MutableList<CosmosLine> {
    val lines = mutableListOf<CosmosLine>()
    lines.addAll(allCosmosLines())
    lines.addAll(allEvmLines().filter { it.supportCosmos })
    return lines
}