package com.wexberry.dotainfo.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(HeroesRoom::class), version = 1, exportSchema = false)
abstract class HeroesRoomDatabase : RoomDatabase() {

    abstract fun heroesDao(): HeroesDao

    private class HeroesDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.heroesDao())
                }
            }
        }

        suspend fun populateDatabase(heroesDao: HeroesDao) {
            // Удалить все слова из БД
            heroesDao.deleteAll()

            // Добавить слова
            var heroes = HeroesRoom(999, "TestName", "TestLocalizedName", "TestAttr", "TestAttackType", listOf("TestRoles1", "TestRoles2", "TestRoles3"))
            heroesDao.insert(heroes)

            heroes = HeroesRoom(998, "TestName2", "TestLocalizedName2", "TestAttr2", "TestAttackType2", listOf("TestRoles11", "TestRoles22", "TestRoles33"))
            heroesDao.insert(heroes)
        }
    }

    companion object {
        // Используется паттерн синглтон для одного экземпляра класса базы данных
        @Volatile
        private var INSTANCE: HeroesRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): HeroesRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HeroesRoomDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}