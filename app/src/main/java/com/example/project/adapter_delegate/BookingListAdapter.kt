package com.example.project.adapter_delegate

import android.content.Context
import com.example.domain.enteties.Booking
import com.example.project.R
import com.example.project.databinding.ItemBookingCustomerInfoBinding
import com.example.project.databinding.ItemBookingHotelNameBinding
import com.example.project.databinding.ItemBookingInfoBinding
import com.example.project.databinding.ItemBookingPriceBinding
import com.example.project.databinding.ItemBookingTouristAddBinding
import com.example.project.databinding.ItemBookingTouristInfoBinding
import com.example.project.items.BookingCustomerInfoItem
import com.example.project.items.BookingHotelNameItem
import com.example.project.items.BookingInfoItem
import com.example.project.items.BookingPriceItem
import com.example.project.items.BookingTouristAdd
import com.example.project.items.BookingTouristInfoItem
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

class BookingListAdapter : ListDelegationAdapter<List<BookingDelegate>>() {

    init {
        delegatesManager.addDelegate(bookingHotelNameDelegate())
        delegatesManager.addDelegate(bookingInfoDelegate())
        delegatesManager.addDelegate(bookingCustomerInfoDelegate())
        delegatesManager.addDelegate(bookingTouristInfoDelegate())
        delegatesManager.addDelegate(bookingTouristAddDelegate())
        delegatesManager.addDelegate(bookingPriceDelegate())
    }

    private fun bookingHotelNameDelegate(): AdapterDelegate<List<BookingDelegate>> =
        adapterDelegateViewBinding<BookingHotelNameItem, BookingDelegate, ItemBookingHotelNameBinding>(
            { layoutInflater, parent ->
                ItemBookingHotelNameBinding.inflate(layoutInflater, parent, false)
            }
        ) {
            bind {
                val booking = item.booking
                with(binding) {
                    chipBookingHotelScore.text = String.format(
                        getString(R.string.hotel_score),
                        booking.horating,
                        booking.ratingName
                    )
                    tvHotelName.text = booking.hotelName
                    tvHotelAddress.text = booking.hotelAddress
                }
            }
        }

    private fun bookingInfoDelegate(): AdapterDelegate<List<BookingDelegate>> =
        adapterDelegateViewBinding<BookingInfoItem, BookingDelegate, ItemBookingInfoBinding>(
            { layoutInflater, parent ->
                ItemBookingInfoBinding.inflate(layoutInflater, parent, false)
            }
        ) {
            bind {
                val booking = item.booking
                with(binding) {
                    tvDeparture.text = booking.departure
                    tvArrivalCountry.text = booking.arrivalCountry
                    tvTourDate.text = String.format(
                        getString(R.string.tv_dates),
                        booking.tourDateStart,
                        booking.tourDateStop
                    )
                    tvNumberOfNights.text = booking.numberOfNights.toString()
                    tvHotelName.text = booking.hotelName
                    tvRoom.text = booking.room
                    tvNutrition.text = booking.nutrition
                }
            }
        }

    private fun bookingCustomerInfoDelegate(): AdapterDelegate<List<BookingDelegate>> =
        adapterDelegateViewBinding<
                BookingCustomerInfoItem,
                BookingDelegate,
                ItemBookingCustomerInfoBinding
                >(
            { layoutInflater, parent ->
                ItemBookingCustomerInfoBinding.inflate(layoutInflater, parent, false)
            }
        ) {
            bind {

            }
        }

    private fun bookingTouristInfoDelegate(): AdapterDelegate<List<BookingDelegate>> =
        adapterDelegateViewBinding<
                BookingTouristInfoItem,
                BookingDelegate,
                ItemBookingTouristInfoBinding
                >(
            { layoutInflater, parent ->
                ItemBookingTouristInfoBinding.inflate(layoutInflater, parent, false)
            }
        ) {
        }

    private fun bookingTouristAddDelegate(): AdapterDelegate<List<BookingDelegate>> =
        adapterDelegateViewBinding<
                BookingTouristAdd,
                BookingDelegate,
                ItemBookingTouristAddBinding
                >(
            { layoutInflater, parent ->
                ItemBookingTouristAddBinding.inflate(layoutInflater, parent, false)
            }
        ) {
        }

    private fun bookingPriceDelegate(): AdapterDelegate<List<BookingDelegate>> =
        adapterDelegateViewBinding<
                BookingPriceItem,
                BookingDelegate,
                ItemBookingPriceBinding
                >(
            { layoutInflater, parent ->
                ItemBookingPriceBinding.inflate(layoutInflater, parent, false)
            }
        ) {
            bind {
                val booking = item.booking
                with(binding) {
                    tvTourPrice.text = getBookingPrice(context, booking.tourPrice ?: 0)
                    tvFuelCharge.text = getBookingPrice(context, booking.fuelCharge ?: 0)
                    tvServiceCharge.text = getBookingPrice(context, booking.serviceCharge ?: 0)
                    tvTotalPrice.text = getBookingPrice(context, getTotalPrice(booking))
                }
            }
        }

    private fun getBookingPrice(context: Context, price: Int): String {
        return String.format(context.getString(R.string.booking_price), price)
    }

    private fun getTotalPrice(booking: Booking): Int {
        val tourPrice = booking.tourPrice ?: 0
        val fuelCharge = booking.fuelCharge ?: 0
        val serviceCharge = booking.serviceCharge ?: 0
        return tourPrice + fuelCharge + serviceCharge
    }
}