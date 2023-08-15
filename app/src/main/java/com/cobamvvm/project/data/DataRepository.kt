package com.cobamvvm.project.data

import com.cobamvvm.project.BuildConfig
import com.cobamvvm.project.data.api.ApiService
import com.cobamvvm.project.data.local.DataBase
import com.cobamvvm.project.data.model.GameDetailResponse
import com.cobamvvm.project.data.model.GameResponse

class DataRepository(
    private val apiDataRepository: ApiService,
    private val localDataSource: DataBase
) {

    suspend fun getGameList(
        page: Int,
        pageSize: Int,
        search: String
    ): GameResponse {
        return apiDataRepository.getGameList(BuildConfig.API_KEY, page, pageSize, search).await()
    }

    suspend fun getGameDetail(id: Long): GameDetailResponse {
        return apiDataRepository.getGameDetail(id, BuildConfig.API_KEY).await()
    }

}
