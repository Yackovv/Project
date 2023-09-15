package com.example.data.repository

import com.example.data.mapper.Mapper
import com.example.data.network.model.ApiFactory
import com.example.domain.enteties.Booking
import com.example.domain.enteties.Hotel
import com.example.domain.enteties.Room
import com.example.domain.repository.RemoteRepository
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor() : RemoteRepository {
    override suspend fun getHotel(): Hotel {
        return Mapper.mapHotelDtoToHotel(ApiFactory.apiService.getHotel())
    }

    override suspend fun getRoomList(): List<Room> {
        return ApiFactory.apiService.getRoomList().rooms?.map {
            Mapper.mapRoomDtoToRoom(it)
        } ?: emptyList()
    }

    override suspend fun getBooking(): Booking {
        return Mapper.mapBookingDtoToBooking(ApiFactory.apiService.getBooking())
    }
}