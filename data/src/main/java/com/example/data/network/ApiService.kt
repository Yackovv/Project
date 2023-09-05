package com.example.data.network

import com.example.data.network.model.BookingDto
import com.example.data.network.model.HotelDto
import com.example.data.network.model.RoomListDto
import retrofit2.http.GET

interface ApiService {

    @GET(HOTEL_ENDPOINT)
    suspend fun getHotel(): HotelDto

    @GET(ROOM_LIST_ENDPOINT)
    suspend fun getRoomList(): RoomListDto

    @GET(BOOKING_ENDPOINT)
    suspend fun getBooking(): BookingDto

    companion object {
        private const val HOTEL_ENDPOINT = "35e0d18e-2521-4f1b-a575-f0fe366f66e3"
        private const val ROOM_LIST_ENDPOINT = "f9a38183-6f95-43aa-853a-9c83cbb05ecd"
        private const val BOOKING_ENDPOINT = "e8868481-743f-4eb2-a0d7-2bc4012275c8"
    }
}