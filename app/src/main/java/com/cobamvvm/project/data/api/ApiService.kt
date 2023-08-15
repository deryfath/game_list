package com.cobamvvm.project.data.api

import com.cobamvvm.project.data.model.GameDetailResponse
import com.cobamvvm.project.data.model.GameResponse
import retrofit2.http.GET
import kotlinx.coroutines.Deferred
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("/api/games")
    fun getGameList(
        @Query("key") apiKey:String,
        @Query("page") page:Int,
        @Query("page_size") pageSize:Int,
        @Query("search") seach:String
    ):Deferred<GameResponse>

    @GET("/api/games/{id}")
    fun getGameDetail(
        @Path("id") id: Long,
        @Query("key") apiKey:String
    ):Deferred<GameDetailResponse>

}
