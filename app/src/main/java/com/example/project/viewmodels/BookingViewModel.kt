package com.example.project.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.enteties.Booking
import com.example.domain.usecases.GetBookingUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BookingViewModel @Inject constructor(
    private val getBookingUseCase: GetBookingUseCase
) : ViewModel() {

    val bookingFlow = MutableSharedFlow<Booking>()

    fun getBooking() {
        viewModelScope.launch {
            val booking = getBookingUseCase.invoke()
            bookingFlow.emit(booking)
        }
    }
}