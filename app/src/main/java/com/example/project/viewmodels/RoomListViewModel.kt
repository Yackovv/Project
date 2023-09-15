package com.example.project.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.enteties.Room
import com.example.domain.usecases.GetRoomListUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RoomListViewModel @Inject constructor(
    private val getRoomListUseCase: GetRoomListUseCase
) : ViewModel() {

    val roomListFlow = MutableSharedFlow<List<Room>>()

    fun getRoomList() {
        viewModelScope.launch {
            roomListFlow.emit(getRoomListUseCase.invoke())
        }
    }
}