package com.example.domain.usecases

import com.example.domain.enteties.Room
import com.example.domain.repository.RemoteRepository
import javax.inject.Inject

class GetRoomListUseCase @Inject constructor(
    private val repository: RemoteRepository
) {
    suspend operator fun invoke(): List<Room> {
        return repository.getRoomList()
    }
}