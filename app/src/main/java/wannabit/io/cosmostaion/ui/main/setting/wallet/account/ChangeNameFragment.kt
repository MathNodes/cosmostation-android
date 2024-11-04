package wannabit.io.cosmostaion.ui.main.setting.wallet.account

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.hideKeyboard
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.FragmentChangeNameBinding
import wannabit.io.cosmostaion.data.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.data.viewmodel.account.AccountViewModel

class ChangeNameFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentChangeNameBinding? = null
    private val binding get() = _binding!!

    private lateinit var baseAccount: BaseAccount

    private val accountViewModel: AccountViewModel by activityViewModels()

    companion object {
        @JvmStatic
        fun newInstance(baseAccount: BaseAccount): ChangeNameFragment {
            val args = Bundle().apply {
                putParcelable("baseAccount", baseAccount)
            }
            val fragment = ChangeNameFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangeNameBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setUpClickAction()
    }

    private fun initView() {
        binding.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("baseAccount", BaseAccount::class.java)
                    ?.let { baseAccount = it }

            } else {
                (arguments?.getParcelable("baseAccount") as? BaseAccount)?.let {
                    baseAccount = it
                }
            }

            val inputText = baseAccount.name
            if (inputText.isNotEmpty()) {
                accountName.setText(baseAccount.name)
            } else {
                accountName.setText(getString(R.string.title_wallet))
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnConfirm.setOnClickListener {
                val inputText = accountName.text.toString().trim()
                if (inputText.equals(baseAccount.name, true)) {
                    dismiss()
                    requireActivity().hideKeyboard(btnConfirm)
                    return@setOnClickListener
                } else if (inputText.isBlank()) {
                    Toast.makeText(
                        context, getString(R.string.str_empty_account_name), Toast.LENGTH_LONG
                    ).show()
                } else {
                    baseAccount.name = inputText
                    accountViewModel.insertAccount(baseAccount)
                    ApplicationViewModel.shared.changeName(baseAccount)
                    requireActivity().hideKeyboard(btnConfirm)
                    dismiss()
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}