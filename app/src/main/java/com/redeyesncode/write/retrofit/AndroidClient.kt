package com.redeyesncode.write.retrofit

import com.redeyesncode.write.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AndroidClient {
    val BASE_URL = "http://43.205.18.211:3577/"


    val retrofitClient: Retrofit.Builder by lazy {
        val levelType: HttpLoggingInterceptor.Level
        if (BuildConfig.BUILD_TYPE.contentEquals("debug"))
            levelType = HttpLoggingInterceptor.Level.BODY else levelType = HttpLoggingInterceptor.Level.NONE
        val loggingInterceptor= HttpLoggingInterceptor()
        loggingInterceptor.setLevel(levelType)
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(loggingInterceptor)
        Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient.build()).addConverterFactory(
            GsonConverterFactory.create())
    }

    val apiInterface:ApiService by lazy {
        retrofitClient.build().create(ApiService::class.java)
    }
}