package com.example.project.items

import com.example.domain.enteties.Hotel
import com.example.project.adapter_delegate.HotelDelegate

data class HotelItem(val hotel: Hotel): HotelDelegate
