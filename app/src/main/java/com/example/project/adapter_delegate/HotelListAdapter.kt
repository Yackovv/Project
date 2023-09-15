package com.example.project.adapter_delegate

import androidx.viewpager2.widget.ViewPager2
import com.example.project.R
import com.example.project.databinding.ItemButtonBinding
import com.example.project.databinding.ItemHotelDescriptionBinding
import com.example.project.databinding.ItemNameHotelBinding
import com.example.project.items.AboutTheHotelItem
import com.example.project.items.HotelButtonItem
import com.example.project.items.HotelItem
import com.example.project.rv.ViewPagerAdapter
import com.google.android.material.chip.Chip
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import java.text.NumberFormat
import java.util.Locale

class HotelListAdapter : ListDelegationAdapter<List<HotelDelegate>>() {

    var clickListener: (() -> Unit)? = null

    init {
        delegatesManager.addDelegate(aboutTheHotelDelegate())
        delegatesManager.addDelegate(hotelItemDelegate())
        delegatesManager.addDelegate(buttonHotelDelegate())
    }

    private fun aboutTheHotelDelegate(): AdapterDelegate<List<HotelDelegate>> =
        adapterDelegateViewBinding<AboutTheHotelItem, HotelDelegate, ItemHotelDescriptionBinding>(
            { layoutInflater, parent ->
                ItemHotelDescriptionBinding.inflate(layoutInflater, parent, false)
            }
        ) {
            bind {
                item.aboutTheHotel.peculiarities.let {
                    for (i in it) {
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
                with(binding) {
                    val hotel = item.hotel
                    vpHotel.adapter = ViewPagerAdapter(hotel.imageUrls)
                    vpHotel.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                    circleIndicator.setViewPager(vpHotel)
                    chipScoreHotel.text = String.format(
                        getString(R.string.hotel_score),
                        hotel.rating,
                        hotel.ratingName
                    )
                    tvHotelName.text = hotel.name
                    tvHotelAddress.text = hotel.address
                    val minPrice = NumberFormat
                        .getNumberInstance(Locale.getDefault())
                        .format(hotel.minimalPrice)
                    tvPrice.text = String.format(getString(R.string.tv_hotel_price), minPrice)
                    tvConditions.text = hotel.priceForIt
                }
            }
        }

    private fun buttonHotelDelegate(): AdapterDelegate<List<HotelDelegate>> =
        adapterDelegateViewBinding<HotelButtonItem, HotelDelegate, ItemButtonBinding>(
            { layoutInflater, parent ->
                ItemButtonBinding.inflate(layoutInflater, parent, false)
            }
        ) {
            bind {
                binding.button.text = getString(R.string.btn_to_select_room)
                binding.button.setOnClickListener {
                    clickListener?.invoke()
                }
            }
        }
}