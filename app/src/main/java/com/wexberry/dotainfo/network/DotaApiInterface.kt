package com.wexberry.dotainfo.network

import com.wexberry.dotainfo.network.dataModels.Heroes
import com.wexberry.dotainfo.network.dataModels.HeroesResponse
import com.wexberry.dotainfo.network.dataModels.Players
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface DotaApiInterface {

    @GET("heroes")
    fun getAllHeroes(): Single<HeroesResponse>

    @GET("search")
    fun searchPlayersByName(
        @Query("search") search: String
    ): Single<Players>
}