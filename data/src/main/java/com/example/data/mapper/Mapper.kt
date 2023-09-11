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
        id = dto.id,
        name = dto.name,
        adress = dto.adress,
        minimalPrice = dto.minimalPrice,
        priceForIt = dto.priceForIt,
        rating = dto.rating,
        ratingName = dto.ratingName,
        imageUrls = dto.imageUrls,
        aboutTheHotel = dto.aboutTheHotelDto?.let { mapAboutTheHotelDtoToAboutTheHotel(it) }
    )

    private fun mapAboutTheHotelDtoToAboutTheHotel(dto: AboutTheHotelDto) = AboutTheHotel(
        description = dto.description,
        peculiarities = dto.peculiarities
    )

    fun mapRoomDtoToRoom(dto: RoomDto) = Room(
        id = dto.id,
        name = dto.name,
        price = dto.price,
        pricePer = dto.pricePer,
        peculiarities = dto.peculiarities,
        imageUrls = dto.imageUrls
    )

    fun mapBookingDtoToBooking(dto: BookingDto) = Booking(
        id = dto.id,
        hotelName = dto.hotelName,
        hotelAddress = dto.hotelAdress,
        horating = dto.horating,
        ratingName = dto.ratingName,
        departure = dto.departure,
        arrivalCountry = dto.arrivalCountry,
        tourDateStart = dto.tourDateStart,
        tourDateStop = dto.tourDateStop,
        numberOfNights = dto.numberOfNights,
        room = dto.room,
        nutrition = dto.nutrition,
        tourPrice = dto.tourPrice,
        fuelCharge = dto.fuelCharge,
        serviceCharge = dto.serviceCharge
    )
}