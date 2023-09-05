package com.example.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RoomListDto(
    @SerializedName("rooms")
    @Expose
    var rooms: List<RoomDto>? = null
)
