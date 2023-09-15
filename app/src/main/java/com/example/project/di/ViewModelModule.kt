package com.example.project.di

import androidx.lifecycle.ViewModel
import com.example.project.viewmodels.BookingViewModel
import com.example.project.viewmodels.HotelViewModel
import com.example.project.viewmodels.RoomListViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HotelViewModel::class)
    fun bindsHotelViewModel(impl: HotelViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RoomListViewModel::class)
    fun bindsRoomListViewModel(impl: RoomListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BookingViewModel::class)
    fun bindsBookingViewModel(impl: BookingViewModel): ViewModel
}