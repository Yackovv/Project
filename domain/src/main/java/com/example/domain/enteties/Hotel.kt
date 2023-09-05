package com.example.domain.enteties

data class Hotel(
    var id: Int?,
    var name: String?,
    var adress: String?,
    var minimalPrice: Int?,
    var priceForIt: String?,
    var rating: Int?,
    var ratingName: String?,
    var imageUrls: List<String>?,
    var aboutTheHotel: AboutTheHotel?
)
