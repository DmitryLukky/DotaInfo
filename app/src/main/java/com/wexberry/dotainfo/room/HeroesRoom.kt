package com.wexberry.dotainfo.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wexberry.dotainfo.network.dataModels.Heroes

@Entity(tableName = "heroes_table")
data class HeroesRoom(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "localizedName") val localizedName: String,
    @ColumnInfo(name = "primaryAttr") val primaryAttr: String,
    @ColumnInfo(name = "attackType") val attackType: String,
    @ColumnInfo(name = "roles") val roles: String
) {
    companion object {
        fun heroesToRoom(heroes: Heroes): HeroesRoom {
            return HeroesRoom(
                heroes.id,
                heroes.nameHero,
                heroes.localizedName,
                heroes.primaryAttr,
                heroes.attackType,
                heroes.roles.toString()
            )
        }
    }
}
