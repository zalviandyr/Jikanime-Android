package com.zukron.jikanime.network

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.zukron.jikanime.service.Utilities
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.internal.addHeaderLenient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/2/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
object RestApi {
    private const val BASE_URL = "https://api.jikan.moe/v3/"
    private const val cacheSize = (10 * 1024 * 1024).toLong() // 10 mb
    private var okHttpClient: OkHttpClient? = null
    val networkStateMutableLiveData: MutableLiveData<NetworkState> = MutableLiveData()

    val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.HEADERS)

    fun getApiService(context: Context): ApiService {
        val cache = Cache(File(context.cacheDir, "jikan-cache"), cacheSize)

        okHttpClient ?: OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
//                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(object : NetworkConnectionInterceptor() {
                    override fun isInternetAvailable(): Int {
                        return Utilities.getConnectionType(context)
                    }

                    override fun onInternetUnavailable() {
                        networkStateMutableLiveData.postValue(NetworkState.NO_CONNECTION)
                    }
                })
                .also {
                    okHttpClient = it.build()
                }

        return apiService
    }

    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient!!)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    }

    private val apiService: ApiService by lazy {
        retrofitBuilder
                .build()
                .create(ApiService::class.java)
    }
}