package wannabit.io.cosmostaion.ui.dialog.tx

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.handlerRight
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.model.res.BnbToken
import wannabit.io.cosmostaion.databinding.FragmentInsertAmountBinding
import java.math.BigDecimal
import java.math.RoundingMode

class LegacyInsertAmountFragment(
    private val selectChain: CosmosLine?,
    private val bnbTokenInfo: BnbToken?,
    private val availAmount: BigDecimal?,
    private val toAmount: String?,
    val listener: AmountSelectListener
) : BottomSheetDialogFragment() {

    private var _binding: FragmentInsertAmountBinding? = null
    private val binding get() = _binding!!

    private var assetDecimal: Int = 6

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInsertAmountBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        textChange()
        updateView()
        clickAction()
    }

    private fun initView() {
        binding.apply {
            if (selectChain is ChainBinanceBeacon) {
                assetDecimal = 8
                availAmount?.setScale(assetDecimal)?.let { amount ->
                    available.text = formatAmount(amount.toPlainString(), assetDecimal)
                    availableDenom.text = bnbTokenInfo?.originalSymbol?.uppercase()
                }

                toAmount?.let {
                    if (it.isNotEmpty()) {
                        val dpToSendAmount = it.toBigDecimal().setScale(assetDecimal).stripTrailingZeros().toPlainString()
                        amountTxt.text = Editable.Factory.getInstance().newEditable(dpToSendAmount)
                    } else {
                        amountTxt.text = Editable.Factory.getInstance().newEditable(it)
                    }
                }

            } else {

            }
        }
    }

    private fun textChange() {
        binding.apply {
            amountTxt.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    val userInput = s.toString()
                    if (userInput.startsWith(".")) {
                        amountTxt.setText("")
                        return

                    } else if (userInput.contains(".")) {
                        val decimalPlaces: Int = userInput.length - userInput.indexOf(".") - 1
                        if (decimalPlaces == assetDecimal) {
                            if (userInput.toBigDecimal().handlerRight(assetDecimal, 0) == BigDecimal.ZERO) {
                                s?.delete(s.length - 1, s.length)
                            }
                        } else if (decimalPlaces > assetDecimal) {
                            s?.delete(s.length - 1, s.length)
                        }
                    }
                    updateView()
                }
            })
        }
    }

    private fun updateView() {
        binding.apply {
            amountTxt.text.toString().trim().let { text ->
                if (text.isEmpty()) {
                    editLayout.error = null
                    invalidMsg.visibility = View.GONE
                    btnConfirm.updateButtonView(false)
                    return
                }

                if (text.toBigDecimal().handlerRight(assetDecimal, 0) == BigDecimal.ZERO) {
                    editLayout.error = getString(R.string.error_invalid_amount)
                    invalidMsg.visibility = View.VISIBLE
                    btnConfirm.updateButtonView(false)
                    return
                }

                BigDecimal(text).apply {
                    availAmount?.setScale(assetDecimal, RoundingMode.DOWN)?.let { amount ->
                        if (this != BigDecimal.ZERO && amount >= this) {
                            editLayout.error = null
                            invalidMsg.visibility = View.GONE
                            btnConfirm.isEnabled = true
                            btnConfirm.updateButtonView(true)

                        } else {
                            editLayout.error = getString(R.string.error_invalid_amount)
                            invalidMsg.visibility = View.VISIBLE
                            btnConfirm.isEnabled = false
                            btnConfirm.updateButtonView(false)
                        }
                        amountTxt.setSelection(text.length)
                    }
                }
            }
        }
    }

    private fun clickAction() {
        binding.apply {
            btnQuarter.setOnClickListener {
                availAmount?.let { amount ->
                    val quarterAmount = amount.multiply(BigDecimal(0.25)).setScale(assetDecimal, RoundingMode.DOWN)
                    amountTxt.text = Editable.Factory.getInstance().newEditable(quarterAmount.toPlainString())
                    updateView()
                }
            }

            btnHalf.setOnClickListener {
                availAmount?.let { amount ->
                    val halfAmount = amount.multiply(BigDecimal(0.5)).setScale(assetDecimal, RoundingMode.DOWN)
                    amountTxt.text = Editable.Factory.getInstance().newEditable(halfAmount.toPlainString())
                    updateView()
                }
            }

            btnMax.setOnClickListener {
                availAmount?.let { amount ->
                    val maxAmount = amount.setScale(assetDecimal, RoundingMode.DOWN)
                    amountTxt.text = Editable.Factory.getInstance().newEditable(maxAmount.toPlainString())
                    updateView()
                }
            }

            btnConfirm.setOnClickListener {
                val originalAmount = BigDecimal(amountTxt.text.toString().trim()).setScale(assetDecimal, RoundingMode.DOWN)
                listener.select(originalAmount.toPlainString())
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}