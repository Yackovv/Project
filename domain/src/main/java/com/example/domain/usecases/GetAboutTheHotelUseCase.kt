package com.example.domain.usecases

import com.example.domain.enteties.Hotel
import com.example.domain.repository.RemoteRepository

class GetAboutTheHotelUseCase(
    private val repository: RemoteRepository
) {
    suspend operator fun invoke(): Hotel {
        return repository.getHotel()
    }
}