package wannabit.io.cosmostaion.data.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt60
import wannabit.io.cosmostaion.common.CosmostationConstants
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    private val okHttpClient: OkHttpClient by lazy {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpExceptionInterceptor = HttpExceptionInterceptor()

        OkHttpClient.Builder()
            .addInterceptor(httpExceptionInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val walletRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .baseUrl(CosmostationConstants.WALLET_API_URL)
            .build()
    }

    private val mintScanRetrofit: Retrofit by lazy {
        Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .baseUrl(CosmostationConstants.MINTSCAN_API_URL)
        .build()
    }

    private val beaconRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .baseUrl(ChainBinanceBeacon().lcdUrl)
            .build()
    }

    private val oktRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .baseUrl(ChainOkt60().lcdUrl)
            .build()
    }

    private val chainListRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .baseUrl(CosmostationConstants.CHAIN_BASE_URL)
            .build()
    }

    val walletApi: WalletApi by lazy {
        walletRetrofit.create(WalletApi::class.java)
    }

    val mintscanApi: MintscanApi by lazy {
        mintScanRetrofit.create(MintscanApi::class.java)
    }

    val beaconApi: LcdApi by lazy {
        beaconRetrofit.create(LcdApi::class.java)
    }

    val oktApi: LcdApi by lazy {
        oktRetrofit.create(LcdApi::class.java)
    }

    val chainListApi: ChainListApi by lazy {
        chainListRetrofit.create(ChainListApi::class.java)
    }
}