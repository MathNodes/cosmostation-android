package wannabit.io.cosmostaion.data.repository.chain

import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import wannabit.io.cosmostaion.common.safeApiCall
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.model.res.BnbHistoryResponse
import wannabit.io.cosmostaion.data.model.res.CosmosHistory
import wannabit.io.cosmostaion.data.model.res.NetworkResult

class HistoryRepositoryImpl : HistoryRepository {

    override suspend fun cosmosHistory(
        chain: String,
        address: String?,
        limit: String,
        searchId: Int?
    ): NetworkResult<Response<List<CosmosHistory>>> {
        return safeApiCall(Dispatchers.IO) {
            RetrofitInstance.mintscanApi.cosmosHistory(chain, address, limit, searchId)
        }
    }

    override suspend fun bnbHistory(
        address: String?,
        startTime: String,
        endTime: String
    ): NetworkResult<Response<BnbHistoryResponse>> {
        return safeApiCall(Dispatchers.IO) {
            RetrofitInstance.beaconApi.bnbHistory(address, startTime, endTime)
        }
    }
}