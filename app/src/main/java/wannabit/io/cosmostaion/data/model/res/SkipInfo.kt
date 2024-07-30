package wannabit.io.cosmostaion.data.model.res

data class SkipChainResponse(
    val chains: MutableList<SkipChain>?
)

data class SkipChain(
    val chain_name: String?,
    val chain_id: String?,
)

data class SkipRouteResponse(
    val source_asset_denom: String?,
    val source_asset_chain_id: String?,
    val dest_asset_denom: String?,
    val dest_asset_chain_id: String?,
    val amount_in: String?,
    val amount_out: String?,
    val operations: MutableList<Operation>?,
    val required_chain_addresses: MutableList<String>?,
    val swap_venue: SwapVenue?,
    val usd_amount_in: String?,
    val usd_amount_out: String?,
    val swap_price_impact_percent: String?
)

data class Operation(
    val transfer: Transfer?,
    val swap: Swap?
) {
    data class Transfer(
        val port: String?,
        val channel: String?,
        val chain_id: String?,
        val pfm_enabled: Boolean?,
        val dest_denom: String?,
        val supports_memo: Boolean?
    )

    data class Swap(
        val swap_in: SwapIn?,
        val estimated_affiliate_fee: String?
    ) {
        data class SwapIn(
            val swap_venue: SwapVenue?,
            val swap_operations: MutableList<SwapOperation>,
            val swap_amount_in: String
        )
    }
}

data class SwapOperation(
    val pool: String?,
    val denom_in: String?,
    val denom_out: String?
)

data class SwapVenue(
    val name: String?,
    val chain_id: String
)

data class SkipMsgResponse(
    val msgs: MutableList<Msg>,
    val txs:MutableList<Txs>
) {
    data class Msg(
        val multi_chain_msg: MultiChainMsg
    ) {
        data class MultiChainMsg(
            val path: MutableList<String>?,
            val chain_id: String?,
            val msg_type_url: String?,
            val msg: String
        )
    }

    data class Txs(
        val cosmos_tx: CosmosTx
    ) {
        data class CosmosTx(
            val chain_id: String?,
            val path: MutableList<String>?,
            val msgs: MutableList<TxMsg>,
            val signer_address: String?
        ) {
            data class TxMsg(
                val msg: String?,
                val msg_type_url: String?
            )
        }
    }
}

data class SkipErrorResponse(
    val code: Int?,
    val message: String?
)
