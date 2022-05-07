package academy.nouri.mvp.data.server

import academy.nouri.mvp.BuildConfig
import academy.nouri.mvp.utils.BASE_URL
import academy.nouri.mvp.utils.NETWORK_TIME
import academy.nouri.mvp.utils.WRITE_NETWORK_TIME
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    private val apiService: ApiService

    init {
        //Gson
        val gson = GsonBuilder().setLenient().create()

        //Interceptor
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE

        //Client
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(NETWORK_TIME, TimeUnit.SECONDS)
            .writeTimeout(WRITE_NETWORK_TIME, TimeUnit.MINUTES)
            .connectTimeout(NETWORK_TIME, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()

        //Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

        //ApiServices
        apiService = retrofit.create(ApiService::class.java)
    }

    companion object {
        private var apiClient: ApiClient? = null

        fun getInstance(): ApiClient = apiClient ?: synchronized(this) {
            apiClient ?: ApiClient().also {
                apiClient = it
            }
        }
    }

    fun apiUseCase(): ApiUseCase {
        return ApiUseCase(apiService)
    }
}