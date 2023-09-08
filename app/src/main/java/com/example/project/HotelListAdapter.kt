package com.example.project

import androidx.viewpager2.widget.ViewPager2
import com.example.project.databinding.ItemHotelDescriptionBinding
import com.example.project.databinding.ItemNameHotelBinding
import com.google.android.material.chip.Chip
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import java.util.Locale

class HotelListAdapter : ListDelegationAdapter<List<HotelDelegate>>() {

    init {
        delegatesManager.addDelegate(aboutTheHotelDelegate())
        delegatesManager.addDelegate(hotelItemDelegate())
    }

    private fun aboutTheHotelDelegate(): AdapterDelegate<List<HotelDelegate>> =
        adapterDelegateViewBinding<AboutTheHotelItem, HotelDelegate, ItemHotelDescriptionBinding>(
            { layoutInflater, parent ->
                ItemHotelDescriptionBinding.inflate(layoutInflater, parent, false)
            }
        ) {
            bind {
                item.aboutTheHotel.peculiarities?.let {
                    for(i in it){
                        Chip(context).apply {
                            text = i
                            binding.chipGroupHotel.addView(this)
                        }
                    }
                }
                binding.tvBigDescription.text = item.aboutTheHotel.description
            }
        }

    private fun hotelItemDelegate(): AdapterDelegate<List<HotelDelegate>> =
        adapterDelegateViewBinding<HotelItem, HotelDelegate, ItemNameHotelBinding>(
            { layoutInflater, parent ->
                ItemNameHotelBinding.inflate(layoutInflater, parent, false)
            }
        ) {
            bind {
                val hotel = item.hotel
                val list = hotel.imageUrls
                if (list != null) {
                    binding.vpHotel.adapter = ViewPagerAdapter(list)
                    binding.vpHotel.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                }
                with(binding) {
                    circleIndicator.setViewPager(vpHotel)
                    chipScoreHotel.text = buildString {
                        append(hotel.rating)
                        append(" ")
                        append(hotel.ratingName)
                    }
                    tvHotelName.text = hotel.name
                    tvHotelAddress.text = hotel.adress
                    tvPrice.text = buildString {
                        append(
                            context.getString(R.string.tv_from_price).lowercase(Locale.getDefault())
                        )
                        append(hotel.minimalPrice)
                        append(" ")
                        append(getString(R.string.rub))
                    }
                    tvConditions.text = hotel.priceForIt
                }
            }
        }
}