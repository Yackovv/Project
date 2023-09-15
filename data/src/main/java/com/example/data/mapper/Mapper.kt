package com.example.data.mapper

import com.example.data.network.model.AboutTheHotelDto
import com.example.data.network.model.BookingDto
import com.example.data.network.model.HotelDto
import com.example.data.network.model.RoomDto
import com.example.domain.enteties.AboutTheHotel
import com.example.domain.enteties.Booking
import com.example.domain.enteties.Hotel
import com.example.domain.enteties.Room

object Mapper {

    fun mapHotelDtoToHotel(dto: HotelDto) = Hotel(
        id = dto.id ?: 0,
        name = dto.name ?: "",
        address = dto.adress ?: "",
        minimalPrice = dto.minimalPrice ?: 0,
        priceForIt = dto.priceForIt ?: "",
        rating = dto.rating ?: 0,
        ratingName = dto.ratingName ?: "",
        imageUrls = dto.imageUrls ?: emptyList(),
        aboutTheHotel = mapAboutTheHotelDtoToAboutTheHotel(dto.aboutTheHotelDto)
    )

    private fun mapAboutTheHotelDtoToAboutTheHotel(dto: AboutTheHotelDto?): AboutTheHotel {
        return if (dto != null) {
            AboutTheHotel(
                dto.description ?: "",
                dto.peculiarities ?: emptyList()
            )
        } else {
            AboutTheHotel("", emptyList())
        }
    }

    fun mapRoomDtoToRoom(dto: RoomDto) = Room(
        id = dto.id ?: 0,
        name = dto.name ?: "",
        price = dto.price ?: 0,
        pricePer = dto.pricePer ?: "",
        peculiarities = dto.peculiarities ?: emptyList(),
        imageUrls = dto.imageUrls ?: emptyList()
    )

    fun mapBookingDtoToBooking(dto: BookingDto) = Booking(
        id = dto.id ?: 0,
        hotelName = dto.hotelName ?: "",
        hotelAddress = dto.hotelAdress ?: "",
        horating = dto.horating ?: 0,
        ratingName = dto.ratingName ?: "",
        departure = dto.departure ?: "",
        arrivalCountry = dto.arrivalCountry ?: "",
        tourDateStart = dto.tourDateStart ?: "",
        tourDateStop = dto.tourDateStop ?: "",
        numberOfNights = dto.numberOfNights ?: 0,
        room = dto.room ?: "",
        nutrition = dto.nutrition ?: "",
        tourPrice = dto.tourPrice ?: 0,
        fuelCharge = dto.fuelCharge ?: 0,
        serviceCharge = dto.serviceCharge ?: 0
    )
}