package com.example.project

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.RemoteRepositoryImpl
import com.example.domain.enteties.Hotel
import com.example.domain.usecases.GetHotelUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class HotelViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RemoteRepositoryImpl()
    private val getHotelUseCase = GetHotelUseCase(repository)

    val hotelFlow = MutableSharedFlow<Hotel>()

    fun getHotel(){
        viewModelScope.launch {
            hotelFlow.emit(getHotelUseCase.invoke())
        }
    }
}