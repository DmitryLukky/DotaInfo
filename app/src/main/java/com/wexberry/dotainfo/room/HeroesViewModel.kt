package com.wexberry.dotainfo.room

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.wexberry.dotainfo.network.DotaApiClient
import com.wexberry.dotainfo.network.dataModels.Heroes
import com.wexberry.dotainfo.ui.fragments.RetrofitFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HeroesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: HeroesRepository
    val allHeroesRoom: LiveData<List<HeroesRoom>>

    init {
        val heroesDao = HeroesRoomDatabase.getDatabase(application, viewModelScope).heroesDao()
        repository = HeroesRepository(heroesDao)
        allHeroesRoom = repository.allHeroesRoom
    }

    fun insert(heroes: HeroesRoom) = viewModelScope.launch {

        repository.insert(heroes)
    }
}