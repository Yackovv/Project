package com.example.project

import androidx.viewpager2.widget.ViewPager2
import com.example.project.databinding.ItemHotelDescriptionBinding
import com.example.project.databinding.ItemNameHotelBinding
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

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
                    with(binding) {
                        tvDescription1.text = it[0]
                        tvDescription2.text = it[1]
                        tvDescription3.text = it[2]
                        tvDescription4.text = it[3]
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
                binding.circleIndicator.setViewPager(binding.vpHotel)
                binding.tvScoreHotel.text = "${hotel.rating} ${hotel.ratingName}"
                binding.tvHotelName.text = hotel.name
                binding.tvPrice.text = hotel.minimalPrice.toString()
                binding.tvConditions.text = hotel.priceForIt
            }
        }
}