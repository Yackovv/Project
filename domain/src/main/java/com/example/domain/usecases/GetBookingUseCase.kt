package com.example.domain.usecases

import com.example.domain.enteties.Booking
import com.example.domain.repository.RemoteRepository
import javax.inject.Inject

class GetBookingUseCase @Inject constructor(
    private val repository: RemoteRepository
) {
    suspend operator fun invoke(): Booking {
        return repository.getBooking()
    }
}