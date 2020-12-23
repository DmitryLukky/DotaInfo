package com.wexberry.dotainfo.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HeroesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: HeroesRepository
    val allHeroes: LiveData<List<HeroesRoom>>

    init {
        val wordsDao = HeroesRoomDatabase.getDatabase(application, viewModelScope).heroesDao()
        repository = HeroesRepository(wordsDao)
        allHeroes = repository.allHeroes
    }

    fun insert(heroes: HeroesRoom) = viewModelScope.launch {
        repository.insert(heroes)
    }
}