package com.example.domain.repository

import com.example.domain.enteties.Booking
import com.example.domain.enteties.Hotel
import com.example.domain.enteties.Room

interface RemoteRepository {
    suspend fun getHotel(): Hotel

    suspend fun getRoomList(): List<Room>

    suspend fun getBooking(): Booking
}