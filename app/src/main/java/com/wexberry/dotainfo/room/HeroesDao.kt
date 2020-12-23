package com.wexberry.dotainfo.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HeroesDao {

    @Query("SELECT * from heroes_table ORDER BY localizedName ASC")
    fun getAlphabetizedHeroes(): LiveData<List<HeroesRoom>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(heroes: HeroesRoom)

    @Query("DELETE FROM heroes_table")
    suspend fun deleteAll()
}