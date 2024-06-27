package wannabit.io.cosmostaion.ui.wallet

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.i2p.crypto.eddsa.Utils
import wannabit.io.cosmostaion.chain.allCosmosLines
import wannabit.io.cosmostaion.chain.allEvmLines
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseKey
import wannabit.io.cosmostaion.common.concurrentForEach
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.FragmentWalletSelectBinding
import wannabit.io.cosmostaion.ui.main.MainActivity
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.ui.viewmodel.account.AccountViewModel
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModel

class WalletSelectFragment : Fragment() {

    private var _binding: FragmentWalletSelectBinding? = null
    private val binding get() = _binding!!

    private lateinit var mnemonic: String
    private lateinit var pKey: String
    private var initType: Int? = 0

    private lateinit var walletSelectAdapter: WalletSelectAdapter

    private val walletViewModel: WalletViewModel by activityViewModels()
    private val accountViewModel: AccountViewModel by activityViewModels()

    private var toAddAccount: BaseAccount? = null
    private var selectHdPath = 0

    private var selectedEvmTags: MutableList<String> = mutableListOf()
    private var selectedCosmosTags: MutableList<String> = mutableListOf()

    companion object {
        @JvmStatic
        fun newInstance(mnemonic: String?, pKey: String?, initType: Int): WalletSelectFragment {
            val args = Bundle().apply {
                putString("mnemonic", mnemonic)
                putString("pKey", pKey)
                putInt("initType", initType)
            }
            val fragment = WalletSelectFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWalletSelectBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setupLoadedData()
        setUpClickAction()
        checkRestore()
    }

    private fun initView() {
        arguments?.apply {
            mnemonic = getString("mnemonic").toString()
            pKey = getString("pKey").toString()
            initType = getInt("initType", -1)
        }

        binding.btnRestoreWallet.updateButtonView(false)
        selectedCosmosTags.add("cosmos118")
        lifecycleScope.launch(Dispatchers.IO) {
            if (mnemonic.isNotEmpty()) {
                toAddAccount =
                    BaseAccount("", "", "", "", BaseAccountType.MNEMONIC, selectHdPath.toString())

                initMnemonicView()

            } else {
                binding.pathLayout.visibility = View.GONE
                toAddAccount = BaseAccount("", "", "", "", BaseAccountType.PRIVATE_KEY, "0")

                initPKeyAllData()
            }
        }
    }

    private fun initMnemonicView() {
        lifecycleScope.launch(Dispatchers.IO) {
//            toAddAccount?.let { account ->
//                account.apply {
//                    allEvmLineChains = allEvmLines()
//                    allCosmosLineChains = allCosmosLines()
//                }
//                withContext(Dispatchers.Main) {
//                    updateView()
//                }
//            }
        }
    }

    private fun initPKeyAllData() {
        lifecycleScope.launch(Dispatchers.IO) {
            toAddAccount?.let { account ->
//                account.apply {
//                    allEvmLineChains = allEvmLines()
//                    allCosmosLineChains =
//                        allCosmosLines().filter { it.isDefault || it.tag == "okt996_Secp" }
//                            .toMutableList()
//                    withContext(Dispatchers.Main) {
//                        updateView()
//                    }
//                }
            }
        }
    }

    private fun updateView() {
        binding.apply {
            toAddAccount?.let { account ->
                if (account.allEvmLineChains.isNotEmpty() && account.allCosmosLineChains.isNotEmpty()) {
                    loading.visibility = View.GONE
                    recycler.visibility = View.VISIBLE
                    btnRestoreWallet.updateButtonView(true)

                    hdPath.text = selectHdPath.toString()

                    initRecyclerView(account)
                }
            }
        }
    }

    private fun initRecyclerView(account: BaseAccount) {
        binding.apply {
            recycler.itemAnimator = null
            walletSelectAdapter = WalletSelectAdapter(
                requireContext(),
                account,
                account.allEvmLineChains,
                account.allCosmosLineChains,
                selectedEvmTags,
                selectedCosmosTags,
                selectClickAction
            )
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = walletSelectAdapter
            walletSelectAdapter.submitList(account.allEvmLineChains + account.allCosmosLineChains)

            loadBalanceData(account)
        }
    }

    private fun loadBalanceData(account: BaseAccount) {
        lifecycleScope.launch(Dispatchers.IO) {
            account.apply {
                if (mnemonic.isNotEmpty()) {
                    val wordList = mnemonic.split(" ")
                    val seed = BaseKey.getHDSeed(BaseKey.toEntropy(wordList))
                    allEvmLineChains.asSequence().concurrentForEach { evmChain ->
                        if (evmChain.address?.isEmpty() == true) {
                            evmChain.setInfoWithSeed(seed, evmChain.setParentPath, lastHDPath)
                        }
                        if (!evmChain.fetched) {
                            walletViewModel.evmBalance(evmChain)
                        }
                    }

                    allCosmosLineChains.asSequence().concurrentForEach { chain ->
                        if (chain.address?.isEmpty() == true) {
                            chain.setInfoWithSeed(seed, chain.setParentPath, lastHDPath)
                        }
                        if (!chain.fetched) {
                            walletViewModel.balance(chain)
                        }
                    }

                } else {
                    allEvmLineChains.asSequence().concurrentForEach { evmChain ->
                        if (evmChain.address?.isEmpty() == true) {
                            evmChain.setInfoWithPrivateKey(Utils.hexToBytes(pKey))
                        }
                        if (!evmChain.fetched) {
                            walletViewModel.evmBalance(evmChain)
                        }
                    }

                    allCosmosLineChains.asSequence().concurrentForEach { chain ->
                        if (chain.address?.isEmpty() == true) {
                            chain.setInfoWithPrivateKey(Utils.hexToBytes(pKey))
                        }
                        if (!chain.fetched) {
                            walletViewModel.balance(chain)
                        }
                    }
                }
            }
        }
    }

    private fun setupLoadedData() {
        walletViewModel.balanceResult.observe(viewLifecycleOwner) { tag ->
            CoroutineScope(Dispatchers.IO).launch {
                toAddAccount?.let { account ->
                    for (i in 0 until account.allEvmLineChains.size) {
                        if (account.allEvmLineChains[i].tag == tag) {
                            withContext(Dispatchers.Main) {
                                if (::walletSelectAdapter.isInitialized) {
                                    walletSelectAdapter.notifyItemChanged(i + 1)
                                }
                            }
                        }
                    }

                    for (i in 0 until account.allCosmosLineChains.size) {
                        if (account.allCosmosLineChains[i].tag == tag) {
                            withContext(Dispatchers.Main) {
                                if (::walletSelectAdapter.isInitialized) {
                                    walletSelectAdapter.notifyItemChanged(i + (account.allEvmLineChains.size + 2))
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private val selectClickAction = object : WalletSelectAdapter.SelectListener {
        override fun select(selectTags: MutableList<String>) {
            selectedCosmosTags = selectTags
        }

        override fun evmSelect(selectEvmTags: MutableList<String>) {
            selectedEvmTags = selectEvmTags
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            var isClickable = true
            pathLayout.setOnClickListener {
                if (isClickable) {
                    isClickable = false

                    val setHdPath = HdPathSelectFragment {
                        selectHdPath = it
                        updateView()
                        onInitDataWithSelectedHdPath()
                    }
                    setHdPath.selectedNumber = selectHdPath
                    setHdPath.show(
                        requireActivity().supportFragmentManager,
                        HdPathSelectFragment::class.java.name
                    )

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }

            btnRestoreWallet.setOnClickListener {
                CreateNameFragment().show(
                    parentFragmentManager, CreateNameFragment::class.java.name
                )
                parentFragmentManager.setFragmentResultListener(
                    "create", this@WalletSelectFragment
                ) { _, bundle ->
                    bundle.getString("create")?.let { name ->
                        requireActivity().runOnUiThread {
                            if (toAddAccount?.type == BaseAccountType.MNEMONIC) {
                                mnemonic.split(" ").let { wordList ->
                                    val entropy = Utils.bytesToHex(BaseKey.toEntropy(wordList))
                                    accountViewModel.createByMnemonic(
                                        name, entropy, selectHdPath.toString()
                                    )
                                }

                            } else {
                                pKey.let { privateKey ->
                                    accountViewModel.createByPrivate(name, privateKey)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun onInitDataWithSelectedHdPath() {
        selectedEvmTags.clear()
        selectedCosmosTags.clear()
        selectedCosmosTags.add("cosmos118")
        binding.btnRestoreWallet.updateButtonView(false)

        lifecycleScope.launch(Dispatchers.IO) {
            toAddAccount =
                BaseAccount("", "", "", "", BaseAccountType.MNEMONIC, selectHdPath.toString())
            initMnemonicView()
        }
    }

    private fun checkRestore() {
        accountViewModel.create.observe(viewLifecycleOwner) {
            BaseData.baseAccount?.let { account ->
                Prefs.setDisplayEvmChains(account, selectedEvmTags)
                Prefs.setDisplayChains(account, selectedCosmosTags)
                ApplicationViewModel.shared.currentAccount(account, true)

                startToActivity()
            }
        }
    }

    private fun startToActivity() {
        if (initType == BaseConstant.CONST_RESTORE_MNEMONIC_ACCOUNT || initType == BaseConstant.CONST_RESTORE_PRIVATE_ACCOUNT) {
            requireActivity().overridePendingTransition(0, 0)
            requireActivity().finish()
        } else {
            Intent(requireActivity(), MainActivity::class.java).apply {
                startActivity(this)
            }
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}