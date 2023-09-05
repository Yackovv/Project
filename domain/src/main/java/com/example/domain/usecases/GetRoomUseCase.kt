package com.example.domain.usecases

import com.example.domain.enteties.Room
import com.example.domain.repository.RemoteRepository

class GetRoomUseCase(
    private val repository: RemoteRepository
) {
    suspend operator fun invoke(): List<Room> {
        return repository.getRoomList()
    }
}