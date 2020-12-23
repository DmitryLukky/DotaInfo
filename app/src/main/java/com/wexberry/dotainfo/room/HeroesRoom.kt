package com.wexberry.dotainfo.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wexberry.dotainfo.network.dataModels.Heroes

@Entity(tableName = "heroes_table")
data class HeroesRoom(

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "name") val name: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "localizedName") val localizedName: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "primaryAttr") val primaryAttr: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "attackType") val attackType: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "attackType") val roles: List<String>
)
