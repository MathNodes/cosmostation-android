package wannabit.io.cosmostaion.ui.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.data.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.databinding.ItemTokenEditBinding
import java.math.RoundingMode

class TokenEditViewHolder(
    val binding: ItemTokenEditBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        walletViewModel: WalletViewModel,
        chain: BaseChain,
        token: Token
    ) {
        binding.apply {
            tokenImg.setTokenImg(token.image)
            tokenImg.clipToOutline = true
            tokenName.text = token.symbol
            tokenAddress.text = token.contract

            BaseData.getToken(chain, token.chain, token.contract)?.let { token ->
                token.amount?.toBigDecimal()?.movePointLeft(token.decimals)
                    ?.setScale(6, RoundingMode.DOWN)?.let { amount ->
                        if (token.fetched) {
                            skeletonTokenAmount.visibility = View.GONE
                            skeletonTokenValue.visibility = View.GONE

                            tokenAmount.text = formatAmount(amount.toPlainString(), 6)
                            if (chain.supportEvm) {
                                chain.evmRpcFetcher?.let {
                                    tokenValue.text =
                                        formatAssetValue(it.tokenValue(token.contract))
                                }

                            } else {
                                chain.cosmosFetcher?.let {
                                    tokenValue.text =
                                        formatAssetValue(it.tokenValue(token.contract))
                                }
                            }

                        } else {
                            CoroutineScope(Dispatchers.IO).launch {
                                if (chain.supportEvm) {
                                    walletViewModel.erc20Balance(chain, token)
                                } else {
                                    val channel = chain.cosmosFetcher?.getChannel()
                                    walletViewModel.cw20Balance(channel, chain, token)
                                }
                            }
                        }
                    }
            }
        }
    }
}