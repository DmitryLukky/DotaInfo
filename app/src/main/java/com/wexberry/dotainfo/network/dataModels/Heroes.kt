package com.wexberry.dotainfo.network.dataModels

import com.google.gson.annotations.SerializedName

data class Heroes(
    val id: Int,

    @SerializedName("name")
    val nameHero: String,

    @SerializedName("localized_name")
    val localizedName: String,

    @SerializedName("primary_attr")
    val primaryAttr: String,

    @SerializedName("attack_type")
    val attackType: String,

    val roles: List<String>
    )
