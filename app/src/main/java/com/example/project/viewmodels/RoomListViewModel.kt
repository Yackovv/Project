package com.example.project.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.RemoteRepositoryImpl
import com.example.domain.enteties.Room
import com.example.domain.usecases.GetRoomListUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class RoomListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RemoteRepositoryImpl()
    private val getRoomListUseCase = GetRoomListUseCase(repository)

    val roomListFlow = MutableSharedFlow<List<Room>>()

    fun getRoomList() {
        viewModelScope.launch {
            val list = getRoomListUseCase.invoke()
            roomListFlow.emit(list)
        }
    }
}