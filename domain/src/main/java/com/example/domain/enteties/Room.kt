package com.example.domain.enteties

data class Room(
    var id: Int,
    var name: String,
    var price: Int,
    var pricePer: String,
    var peculiarities: List<String>,
    var imageUrls: List<String>
)
