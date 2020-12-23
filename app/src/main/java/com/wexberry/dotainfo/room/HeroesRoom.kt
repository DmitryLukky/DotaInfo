package com.wexberry.dotainfo.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "heroes_table")
data class HeroesRoom(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "localizedName") val localizedName: String,
    @ColumnInfo(name = "primaryAttr") val primaryAttr: String,
    @ColumnInfo(name = "attackType") val attackType: String,
    @ColumnInfo(name = "roles") val roles: String
)
