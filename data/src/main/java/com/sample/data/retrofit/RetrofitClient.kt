package com.sample.data.retrofit

import com.sample.data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val retrofitClient: Retrofit.Builder by lazy {

        val levelType: Level = if (BuildConfig.BUILD_TYPE.contentEquals("debug"))
            Level.BODY else Level.NONE

        val logging = HttpLoggingInterceptor()
        logging.setLevel(levelType)

        val okhttpClient = OkHttpClient.Builder()
        okhttpClient.addInterceptor(logging)

        Retrofit.Builder()
            .baseUrl("http://api.duckduckgo.com")
            .client(okhttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val retrofitInterface: RetrofitAPIInterface by lazy {
        retrofitClient
            .build()
            .create(RetrofitAPIInterface::class.java)
    }
}