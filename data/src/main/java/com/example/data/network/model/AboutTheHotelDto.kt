package com.example.data.network.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class AboutTheHotelDto(
    @SerializedName("description")
    @Expose
    var description: String? = null,

    @SerializedName("peculiarities")
    @Expose
    var peculiarities: List<String>? = null
)