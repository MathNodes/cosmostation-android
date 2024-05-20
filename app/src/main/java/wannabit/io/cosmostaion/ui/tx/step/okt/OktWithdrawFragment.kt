package wannabit.io.cosmostaion.ui.tx.step.okt

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.cosmosClass.OKT_BASE_FEE
import wannabit.io.cosmostaion.chain.cosmosClass.OKT_GECKO_ID
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.cosmos.Signer
import wannabit.io.cosmostaion.data.model.req.LCoin
import wannabit.io.cosmostaion.data.model.req.LFee
import wannabit.io.cosmostaion.data.model.res.OktToken
import wannabit.io.cosmostaion.databinding.FragmentOktWithdrawBinding
import wannabit.io.cosmostaion.ui.option.tx.general.AmountSelectListener
import wannabit.io.cosmostaion.ui.option.tx.general.LegacyInsertAmountFragment
import wannabit.io.cosmostaion.ui.option.tx.general.MemoFragment
import wannabit.io.cosmostaion.ui.option.tx.general.MemoListener
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import wannabit.io.cosmostaion.ui.tx.step.BaseTxFragment
import java.math.BigDecimal
import java.math.RoundingMode

class OktWithdrawFragment : BaseTxFragment() {

    private var _binding: FragmentOktWithdrawBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: ChainOkt996Keccak

    private var oktTokenInfo: OktToken? = null

    private var toWithdrawAmount = ""
    private var txMemo = ""

    private var availableAmount = BigDecimal.ZERO
    private var gasAmount = BigDecimal(BaseConstant.BASE_GAS_AMOUNT)
    private var gasFee = BigDecimal(OKT_BASE_FEE)

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: CosmosLine): OktWithdrawFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = OktWithdrawFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOktWithdrawBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initFee()
        setUpClickAction()
        setUpBroadcast()
    }

    private fun initView() {
        binding.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("selectedChain", ChainOkt996Keccak::class.java)
                    ?.let { selectedChain = it }

            } else {
                (arguments?.getParcelable("selectedChain") as? ChainOkt996Keccak)?.let {
                    selectedChain = it
                }
            }

            listOf(oktWithdrawView, memoView, feeView).forEach {
                it.setBackgroundResource(
                    R.drawable.cell_bg
                )
            }

            selectedChain.oktTokenInfo?.data?.firstOrNull { it.symbol == selectedChain.stakeDenom }
                ?.let { tokenInfo ->
                    oktTokenInfo = tokenInfo
                    val originalSymbol = tokenInfo.original_symbol
                    tokenImg.setTokenImg(selectedChain.assetImg(originalSymbol))
                    tokenName.text = originalSymbol.uppercase()
                    availableAmount = selectedChain.lcdOktDepositAmount()
                }
        }
    }

    private fun initFee() {
        binding.apply {
            selectedChain.stakeDenom?.let { stakeDenom ->
                feeTokenImg.setTokenImg(selectedChain.assetImg(stakeDenom))
                feeToken.text = stakeDenom.uppercase()

                selectedChain.oktDepositedInfo?.validatorAddress?.size?.let { existCnt ->
                    gasAmount = BigDecimal(BaseConstant.BASE_GAS_AMOUNT)
                    gasFee = BigDecimal(OKT_BASE_FEE)
                    if (existCnt > 10) {
                        val multiplier = if (existCnt > 20) 4 else 3
                        gasAmount = gasAmount.multiply(BigDecimal(multiplier.toString()))
                        gasFee = gasFee.multiply(BigDecimal(multiplier.toString()))
                    }
                }

                val price = BaseData.getPrice(OKT_GECKO_ID)
                val value = price.multiply(gasFee).setScale(6, RoundingMode.DOWN)
                feeAmount.text = formatAmount(gasFee.toPlainString(), 18)
                feeDenom.text = stakeDenom.uppercase()
                feeValue.text = formatAssetValue(value)
            }
        }
    }

    private fun updateAmountView(toAmount: String) {
        binding.apply {
            tabMsgTxt.visibility = View.GONE
            amountLayout.visibility = View.VISIBLE
            toWithdrawAmount = toAmount

            val dpAmount = BigDecimal(toAmount).setScale(18, RoundingMode.DOWN)
            withdrawAmount.text = formatAmount(dpAmount.toPlainString(), 18)

            val price = BaseData.getPrice(OKT_GECKO_ID)
            val toSendValue = price.multiply(dpAmount).setScale(6, RoundingMode.DOWN)
            withdrawValue.text = formatAssetValue(toSendValue)
        }
        txValidate()
    }

    private fun updateMemoView(memo: String) {
        binding.apply {
            txMemo = memo
            if (txMemo.isEmpty()) {
                tabMemoMsg.text = getString(R.string.str_tap_for_add_memo_msg)
                tabMemoMsg.setTextColor(
                    ContextCompat.getColorStateList(
                        requireContext(), R.color.color_base03
                    )
                )
            } else {
                tabMemoMsg.text = txMemo
                tabMemoMsg.setTextColor(
                    ContextCompat.getColorStateList(
                        requireContext(), R.color.color_base01
                    )
                )
            }
        }
        txValidate()
    }

    private fun setUpClickAction() {
        binding.apply {
            oktWithdrawView.setOnClickListener {
                handleOneClickWithDelay(
                    LegacyInsertAmountFragment.newInstance(selectedChain,
                        oktTokenInfo,
                        availableAmount.toString(),
                        toWithdrawAmount,
                        object : AmountSelectListener {
                            override fun select(toAmount: String) {
                                updateAmountView(toAmount)
                            }
                        })
                )
            }

            memoView.setOnClickListener {
                handleOneClickWithDelay(
                    MemoFragment.newInstance(txMemo, object : MemoListener {
                        override fun memo(memo: String) {
                            updateMemoView(memo)
                        }
                    })
                )
            }

            btnWithdraw.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    withdrawResultLauncher.launch(this)
                    requireActivity().overridePendingTransition(
                        R.anim.anim_slide_in_bottom, R.anim.anim_fade_out
                    )
                }
            }
        }
    }

    private fun handleOneClickWithDelay(bottomSheetDialogFragment: BottomSheetDialogFragment) {
        if (isClickable) {
            isClickable = false

            bottomSheetDialogFragment.show(
                requireActivity().supportFragmentManager, bottomSheetDialogFragment::class.java.name
            )

            Handler(Looper.getMainLooper()).postDelayed({
                isClickable = true
            }, 300)
        }
    }

    private fun txValidate() {
        binding.apply {
            if (toWithdrawAmount.isEmpty()) {
                return
            }
            if (BigDecimal(toWithdrawAmount) == BigDecimal.ZERO) {
                return
            }
            binding.btnWithdraw.updateButtonView(true)
        }
    }

    private val withdrawResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE

                selectedChain.stakeDenom?.let { stakeDenom ->
                    val withdrawCoin = LCoin(stakeDenom, toWithdrawAmount)
                    val gasCoin = LCoin(stakeDenom, gasFee.toString())
                    val fee = LFee(gasAmount.toString(), mutableListOf(gasCoin))

                    selectedChain.address?.let { address ->
                        val oktWithdrawMsg = Signer.oktWithdrawMsg(address, withdrawCoin)
                        txViewModel.broadcastOktTx(oktWithdrawMsg, fee, txMemo, selectedChain)
                    }
                }
            }
        }

    private fun setUpBroadcast() {
        txViewModel.broadcastOktTx.observe(viewLifecycleOwner) { txResponse ->
            Intent(requireContext(), TxResultActivity::class.java).apply {
                if (txResponse != null) {
                    if (txResponse.txhash != null) {
                        putExtra("txHash", txResponse.txhash)
                        putExtra("isSuccess", true)
                    }
                    if (txResponse.code != null) {
                        putExtra("errorMsg", txResponse.rawLog)
                        putExtra("isSuccess", false)
                    }
                } else {
                    putExtra("isSuccess", false)
                }
                putExtra("selectedChain", selectedChain.tag)
                startActivity(this)
            }
            dismiss()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}