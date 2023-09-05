package com.example.data.network.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class RoomDto(
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("price")
    @Expose
    var price: Int? = null,

    @SerializedName("price_per")
    @Expose
    var pricePer: String? = null,

    @SerializedName("peculiarities")
    @Expose
    var peculiarities: List<String>? = null,

    @SerializedName("image_urls")
    @Expose
    var imageUrls: List<String>? = null
)