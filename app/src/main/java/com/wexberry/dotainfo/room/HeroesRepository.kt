package com.wexberry.dotainfo.room

import androidx.lifecycle.LiveData
import com.wexberry.dotainfo.network.DotaApiClient
import com.wexberry.dotainfo.network.dataModels.Heroes
import io.reactivex.Single

// Объявляем DAO как private свойство в конструкторе. Передаем DAO
// вместо всей базы данных, потому что нам необходим доступ только к данному объекту
class HeroesRepository(private val heroesDao: HeroesDao) {

    // Room выполняет все запросы в отдельном потоке.
    // Когда данные изменятся LiveData оповестит подписчиков.
    val allHeroesRoom: LiveData<List<HeroesRoom>> = heroesDao.getAlphabetizedHeroes()

    suspend fun insert(heroes: HeroesRoom) {
        heroesDao.insert(heroes)
    }
}