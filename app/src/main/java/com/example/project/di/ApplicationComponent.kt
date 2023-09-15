package com.example.project.di

import com.example.project.fragments.BookingFragment
import com.example.project.fragments.HotelFragment
import com.example.project.fragments.RoomListFragment
import dagger.Component

@ApplicationScope
@Component(modules = [ViewModelModule::class, RemoteDataModule::class])
interface ApplicationComponent {

    fun inject(fragment: BookingFragment)

    fun inject(fragment: HotelFragment)

    fun inject(fragment: RoomListFragment)
}