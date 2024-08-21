package wannabit.io.cosmostaion.ui.viewmodel.chain

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.common.dpTimeToYear
import wannabit.io.cosmostaion.common.formatTxTime
import wannabit.io.cosmostaion.data.model.res.CosmosHistory
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.TransactionList
import wannabit.io.cosmostaion.data.repository.chain.HistoryRepository

class HistoryViewModel(private val historyRepository: HistoryRepository) : ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private var _historyResult = MutableLiveData<MutableList<Pair<String, CosmosHistory>>>()
    val historyResult: LiveData<MutableList<Pair<String, CosmosHistory>>> get() = _historyResult

    fun history(
        context: Context, chain: String, address: String?, limit: String, searchAfter: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        when (val response = historyRepository.cosmosHistory(chain, address, limit, searchAfter)) {
            is NetworkResult.Success -> {
                response.data.let { data ->
                    if (data.isSuccessful) {
                        val result: MutableList<Pair<String, CosmosHistory>> = mutableListOf()
                        data.body()?.forEach { history ->
                            history.header?.let {
                                val headerDate = formatTxTime(context, it.timestamp)
                                result.add(Pair(headerDate, history))
                            }
                        }
                        _historyResult.postValue(result)

                    } else {
                        _errorMessage.postValue("Error")
                    }
                }
            }

            is NetworkResult.Error -> {
                _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
    }

    private var _oktHistoryResult = MutableLiveData<MutableList<Pair<String, TransactionList>>>()
    val oktHistoryResult: LiveData<MutableList<Pair<String, TransactionList>>> get() = _oktHistoryResult
    fun oktHistory(device: String, address: String?, limit: String) =
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = historyRepository.oktHistory(device, address, limit)) {
                is NetworkResult.Success -> {
                    response.data.let { data ->
                        if (data.isSuccessful) {
                            val result: MutableList<Pair<String, TransactionList>> = mutableListOf()
                            if (data.body()?.data?.isNotEmpty() == true) {
                                data.body()?.data?.get(0)?.transactionLists?.forEach { history ->
                                    history.transactionTime.let {
                                        val headerDate = dpTimeToYear(it.toLong())
                                        result.add(Pair(headerDate, history))
                                    }
                                }
                            } else {
                                _errorMessage.postValue("Error")
                            }
                            _oktHistoryResult.postValue(result)
                        } else {
                            _errorMessage.postValue("Error")
                        }
                    }
                }

                is NetworkResult.Error -> {
                    _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                }
            }
        }

    private var _suiHistoryResult = MutableLiveData<MutableList<Pair<String, JsonObject>>>()
    val suiHistoryResult: LiveData<MutableList<Pair<String, JsonObject>>> get() = _suiHistoryResult

    fun suiHistory(chain: ChainSui) = viewModelScope.launch(Dispatchers.IO) {
        chain.suiFetcher()?.let { fetcher ->
            fetcher.suiHistory.clear()

            try {
                val loadFromHistoryDeferred =
                    async { historyRepository.suiFromHistory(fetcher, chain.mainAddress) }
                val loadToHistoryDeferred =
                    async { historyRepository.suiToHistory(fetcher, chain.mainAddress) }

                val fromHistoryResult = loadFromHistoryDeferred.await()
                val toHistoryResult = loadToHistoryDeferred.await()

                if (fromHistoryResult is NetworkResult.Success && toHistoryResult is NetworkResult.Success) {
                    val result: MutableList<Pair<String, JsonObject>> = mutableListOf()
                    fetcher.suiHistory.addAll(fromHistoryResult.data ?: mutableListOf())
                    toHistoryResult.data?.forEach { to ->
                        if (fetcher.suiHistory.firstOrNull { it["digest"].asString == to["digest"].asString } == null) {
                            fetcher.suiHistory.add(to)
                        }
                    }
                    fetcher.suiHistory.sortByDescending {
                        it["checkpoint"]?.toString()?.toLongOrNull() ?: Long.MIN_VALUE
                    }
                    fetcher.suiHistory.forEach { history ->
                        val headerDate = dpTimeToYear(history["timestampMs"].asString.toLong())
                        result.add(Pair(headerDate, history))
                    }
                    _suiHistoryResult.postValue(result)

                } else {
                    if (fromHistoryResult is NetworkResult.Error) {
                        _errorMessage.postValue("error type : ${fromHistoryResult.errorType}  error message : ${fromHistoryResult.errorMessage}")

                    } else if (toHistoryResult is NetworkResult.Error) {
                        _errorMessage.postValue("error type : ${toHistoryResult.errorType}  error message : ${toHistoryResult.errorMessage}")
                    }
                }

            } catch (_: Exception) {

            }
        }
    }
}