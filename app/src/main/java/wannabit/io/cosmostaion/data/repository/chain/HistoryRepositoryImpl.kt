package wannabit.io.cosmostaion.data.repository.chain

import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import wannabit.io.cosmostaion.common.safeApiCall
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.model.res.CosmosHistory
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.OktHistoryResponse

class HistoryRepositoryImpl : HistoryRepository {

    override suspend fun cosmosHistory(
        chain: String,
        address: String?,
        limit: String,
        searchAfter: String
    ): NetworkResult<Response<List<CosmosHistory>>> {
        return safeApiCall(Dispatchers.IO) {
            RetrofitInstance.mintscanApi.cosmosHistory(chain, address, limit, searchAfter)
        }
    }

    override suspend fun oktHistory(
        device: String,
        address: String?,
        limit: String
    ): NetworkResult<Response<OktHistoryResponse>> {
        return safeApiCall(Dispatchers.IO) {
            RetrofitInstance.mintscanApi.oktHistory(device, address, limit)
        }
    }
}