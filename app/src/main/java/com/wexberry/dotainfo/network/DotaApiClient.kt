package com.wexberry.dotainfo.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object DotaApiClient {

    const val BASE_URL = "https://api.opendota.com/api/"

    val apiClient: DotaApiInterface by lazy {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return@lazy retrofit.create(DotaApiInterface::class.java)

        // Для создание экземпляра Retrofit лучше всего использовать делегированное свойство by lazy,
        // то есть отложенную инициализацию.
    }
}