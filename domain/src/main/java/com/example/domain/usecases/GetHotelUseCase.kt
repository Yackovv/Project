package com.example.domain.usecases

import com.example.domain.enteties.Hotel
import com.example.domain.repository.RemoteRepository
import javax.inject.Inject

class GetHotelUseCase @Inject constructor(
    private val repository: RemoteRepository
) {
    suspend operator fun invoke(): Hotel {
        return repository.getHotel()
    }
}