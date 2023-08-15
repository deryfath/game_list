package com.cobamvvm.project.module.viewmodel

import androidx.lifecycle.MutableLiveData
import com.cobamvvm.project.data.DataRepository
import com.cobamvvm.project.data.model.GameDetailResponse
import com.cobamvvm.project.data.model.Result
import kotlinx.coroutines.launch

class MainViewModel(private val repository: DataRepository):AbstractVIewModel() {

    val gameList = MutableLiveData<MutableList<Result>>()
    val gameListSearch = MutableLiveData<MutableList<Result>>()
    val gameDetail = MutableLiveData<GameDetailResponse>()

    fun getGameList(curPage:Int){
        launch {
            try {
                setLoading()
                val dataRepo = repository.getGameList(curPage,10,"")

                gameList.postValue(dataRepo.results)

            }catch (t:Throwable){
                println("ERROR COROUTINE "+t.message)
                gameList.postValue(mutableListOf())

                setError(t)
            }finally {
                setLoading(false)
            }
        }
    }

    fun getSearchGameList(keyword:String){
        launch {
            try {
                setLoading()
                val dataRepo = repository.getGameList(1,15, keyword)

                gameListSearch.postValue(dataRepo.results)

            }catch (t:Throwable){
                println("ERROR COROUTINE "+t.message)
                gameListSearch.postValue(mutableListOf())

                setError(t)
            }finally {
                setLoading(false)
            }
        }
    }

    fun getGameDetail(id:Long){
        launch {
            try {
                val dataRepo = repository.getGameDetail(id)

                gameDetail.postValue(dataRepo)

            }catch (t:Throwable){
                println("ERROR COROUTINE "+t.message)
            }
        }
    }

    fun start(){
        gameList.value = arrayListOf()
    }
}
