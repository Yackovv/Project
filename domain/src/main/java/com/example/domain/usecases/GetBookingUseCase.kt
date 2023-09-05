package com.example.domain.usecases

import com.example.domain.enteties.Booking
import com.example.domain.repository.RemoteRepository

class GetBookingUseCase(
    private val repository: RemoteRepository
) {
    suspend operator fun invoke(): Booking {
        return repository.getBooking()
    }
}