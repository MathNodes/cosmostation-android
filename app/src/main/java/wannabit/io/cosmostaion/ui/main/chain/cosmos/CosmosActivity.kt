package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseActivity
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.toMoveBack
import wannabit.io.cosmostaion.data.repository.chain.ProposalRepositoryImpl
import wannabit.io.cosmostaion.data.repository.tx.TxRepositoryImpl
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepositoryImpl
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ActivityCosmosBinding
import wannabit.io.cosmostaion.ui.viewmodel.chain.ProposalViewModel
import wannabit.io.cosmostaion.ui.viewmodel.chain.ProposalViewModelProviderFactory
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModelProviderFactory
import wannabit.io.cosmostaion.ui.viewmodel.tx.TxViewModel
import wannabit.io.cosmostaion.ui.viewmodel.tx.TxViewModelProviderFactory

class CosmosActivity : BaseActivity() {

    private lateinit var binding: ActivityCosmosBinding

    private lateinit var txViewModel: TxViewModel
    private lateinit var walletViewModel: WalletViewModel
    private lateinit var proposalViewModel: ProposalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCosmosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.parentLayout.setBackgroundResource(Prefs.background)

        if (savedInstanceState == null) {
            BaseData.baseAccount?.allCosmosLineChains?.firstOrNull {
                it.tag == intent.getStringExtra(
                    "selectedChainTag"
                )
            }?.let { chain ->
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container, CosmosDetailFragment.newInstance(chain)
                ).commitAllowingStateLoss()

            } ?: run {
                BaseData.baseAccount?.allEvmLineChains?.firstOrNull {
                    it.tag == intent.getStringExtra(
                        "selectedChainTag"
                    )
                }?.let { chain ->
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fragment_container, CosmosDetailFragment.newInstance(chain)
                    ).commitAllowingStateLoss()
                }
            }
        }
        initViewModel()
    }

    private fun initViewModel() {
        val txRepository = TxRepositoryImpl()
        val txViewModelProviderFactory = TxViewModelProviderFactory(txRepository)
        txViewModel = ViewModelProvider(
            this, txViewModelProviderFactory
        )[TxViewModel::class.java]

        val walletRepository = WalletRepositoryImpl()
        val walletViewModelProviderFactory = WalletViewModelProviderFactory(walletRepository)
        walletViewModel =
            ViewModelProvider(this, walletViewModelProviderFactory)[WalletViewModel::class.java]

        val proposalRepository = ProposalRepositoryImpl()
        val proposalViewModelProviderFactory = ProposalViewModelProviderFactory(proposalRepository)
        proposalViewModel =
            ViewModelProvider(this, proposalViewModelProviderFactory)[ProposalViewModel::class.java]
    }

    override fun onBackPressed() {
        super.onBackPressed()
        toMoveBack()
    }
}

enum class TxType {
    DELEGATE, UN_DELEGATE, RE_DELEGATE, VAULT_DEPOSIT, VAULT_WITHDRAW,
    MINT_CREATE_COLLATERAL, MINT_CREATE_PRINCIPAL, MINT_DEPOSIT, MINT_WITHDRAW, MINT_BORROW, MINT_REPAY,
    LEND_DEPOSIT, LEND_WITHDRAW, LEND_BORROW, LEND_REPAY, POOL_WITHDRAW, EARN_DEPOSIT, EARN_WITHDRAW
}