package com.example.project.items

import com.example.domain.enteties.Booking
import com.example.project.adapter_delegate.BookingDelegate

data class BookingPriceItem(val booking: Booking) : BookingDelegate