package com.example.data.network.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class BookingDto(
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("hotel_name")
    @Expose
    var hotelName: String? = null,

    @SerializedName("hotel_adress")
    @Expose
    var hotelAdress: String? = null,

    @SerializedName("horating")
    @Expose
    var horating: Int? = null,

    @SerializedName("rating_name")
    @Expose
    var ratingName: String? = null,

    @SerializedName("departure")
    @Expose
    var departure: String? = null,

    @SerializedName("arrival_country")
    @Expose
    var arrivalCountry: String? = null,

    @SerializedName("tour_date_start")
    @Expose
    var tourDateStart: String? = null,

    @SerializedName("tour_date_stop")
    @Expose
    var tourDateStop: String? = null,

    @SerializedName("number_of_nights")
    @Expose
    var numberOfNights: Int? = null,

    @SerializedName("room")
    @Expose
    var room: String? = null,

    @SerializedName("nutrition")
    @Expose
    var nutrition: String? = null,

    @SerializedName("tour_price")
    @Expose
    var tourPrice: Int? = null,

    @SerializedName("fuel_charge")
    @Expose
    var fuelCharge: Int? = null,

    @SerializedName("service_charge")
    @Expose
    var serviceCharge: Int? = null
)
