package com.example.data.network.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class HotelDto(
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("adress")
    @Expose
    var adress: String? = null,

    @SerializedName("minimal_price")
    @Expose
    var minimalPrice: Int? = null,

    @SerializedName("price_for_it")
    @Expose
    var priceForIt: String? = null,

    @SerializedName("rating")
    @Expose
    var rating: Int? = null,

    @SerializedName("rating_name")
    @Expose
    var ratingName: String? = null,

    @SerializedName("image_urls")
    @Expose
    var imageUrls: List<String>? = null,

    @SerializedName("about_the_hotel")
    @Expose
    var aboutTheHotelDto: AboutTheHotelDto? = null
)