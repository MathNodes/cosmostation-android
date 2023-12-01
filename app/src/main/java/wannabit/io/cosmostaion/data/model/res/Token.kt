package wannabit.io.cosmostaion.data.model.res

import com.squareup.moshi.JsonClass
import wannabit.io.cosmostaion.common.CosmostationConstants


@JsonClass(generateAdapter = true)
data class TokenResponse(val assets: MutableList<Token>)

data class Token(
    val id: Int,
    val chainName: String,
    val address: String,
    val symbol: String,
    val decimals: Int,
    val description: String,
    val display: Int,
    val image: String,
    val default: Boolean,
    val totalSupply: String,
    val coinGeckoId: String
) {

    var amount: String? = null
        get() = field ?: "0"

    fun assetImg(): String {
        return CosmostationConstants.CHAIN_BASE_URL + image
    }
}