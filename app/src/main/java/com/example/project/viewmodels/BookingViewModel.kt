package com.example.project.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.RemoteRepositoryImpl
import com.example.domain.enteties.Booking
import com.example.domain.usecases.GetBookingUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class BookingViewModel(): ViewModel() {

    private val repository = RemoteRepositoryImpl()
    private val getBookingUseCase = GetBookingUseCase(repository)

    val bookingFlow = MutableSharedFlow<Booking>()

    fun getBooking(){
        viewModelScope.launch {
            val booking = getBookingUseCase.invoke()
            bookingFlow.emit(booking)
        }
    }
}