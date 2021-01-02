package com.wexberry.dotainfo.room

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wexberry.dotainfo.network.DotaApiClient
import com.wexberry.dotainfo.network.dataModels.Heroes
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HeroesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: HeroesRepository
    val allHeroesRoom: LiveData<List<HeroesRoom>>
    var heroesToRoom: MutableLiveData<HeroesRoom>? = null
    var heroesRetrofit: MutableLiveData<List<Heroes>>? = null

    init {
        val heroesDao = HeroesRoomDatabase.getDatabase(application, viewModelScope).heroesDao()
        repository = HeroesRepository(heroesDao)
        allHeroesRoom = repository.allHeroesRoom
    }

    fun insert(heroes: HeroesRoom) = viewModelScope.launch {

        repository.insert(heroes)
    }

    fun deleteAllFromRoom() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun getAllHeroes() {
        val apiClient = DotaApiClient.apiClient.getAllHeroes()

        apiClient
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { it ->
                    heroesRetrofit?.postValue(it)

                    it.forEach {
                        val listRoom = HeroesRoom.heroesToRoom(it)
                        heroesToRoom?.postValue(listRoom)
                    }
                },
                { error ->
                    // Логируем ошибку
                    Log.d("TAG", error.toString())
                }
            ).isDisposed
    }
}