package com.wexberry.dotainfo.network.dataModels

import com.google.gson.annotations.SerializedName

data class Players(
    @SerializedName("solo_competitive_rank")
    val soloRank: String
)
