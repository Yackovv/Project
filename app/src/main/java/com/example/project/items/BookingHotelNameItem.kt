package com.example.project.items

import com.example.domain.enteties.Booking
import com.example.project.adapter_delegate.BookingDelegate

data class BookingHotelNameItem(val booking: Booking) : BookingDelegate
