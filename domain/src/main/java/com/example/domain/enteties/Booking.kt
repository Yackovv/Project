package com.example.domain.enteties

data class Booking (
    var id: Int?,
    var hotelName: String?,
    var hotelAddress: String?,
    var horating: Int?,
    var ratingName: String?,
    var departure: String?,
    var arrivalCountry: String?,
    var tourDateStart: String?,
    var tourDateStop: String?,
    var numberOfNights: Int?,
    var room: String?,
    var nutrition: String?,
    var tourPrice: Int?,
    var fuelCharge: Int?,
    var serviceCharge: Int?
)