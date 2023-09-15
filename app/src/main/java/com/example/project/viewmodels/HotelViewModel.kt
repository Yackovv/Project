package com.example.project.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.enteties.Hotel
import com.example.domain.usecases.GetHotelUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HotelViewModel @Inject constructor(
    private val getHotelUseCase: GetHotelUseCase
) : ViewModel() {

    val hotelFlow = MutableSharedFlow<Hotel>()

    fun getHotel() {
        viewModelScope.launch {
            hotelFlow.emit(getHotelUseCase.invoke())
        }
    }
}