package com.example.project.adapter_delegate

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import com.example.domain.enteties.Booking
import com.example.project.R
import com.example.project.databinding.ItemBookingCustomerInfoBinding
import com.example.project.databinding.ItemBookingHotelNameBinding
import com.example.project.databinding.ItemBookingInfoBinding
import com.example.project.databinding.ItemBookingPriceBinding
import com.example.project.databinding.ItemBookingTouristAddBinding
import com.example.project.databinding.ItemBookingTouristInfoBinding
import com.example.project.databinding.ItemButtonBinding
import com.example.project.items.BookingButtonItem
import com.example.project.items.BookingCustomerInfoItem
import com.example.project.items.BookingHotelNameItem
import com.example.project.items.BookingInfoItem
import com.example.project.items.BookingPriceItem
import com.example.project.items.BookingTouristAddItem
import com.example.project.items.BookingTouristInfoItem
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

class BookingListAdapter : ListDelegationAdapter<List<BookingDelegate>>() {

    var launchOrderPaidFragment: (() -> Unit)? = null
    var addTouristListener: (() -> Unit)? = null
    private var buttonListenerTouristInfo: (() -> Boolean)? = null
    private var buttonListenerPhone: (() -> Boolean)? = null
    private var buttonListenerMail: Boolean = false

    init {
        delegatesManager.addDelegate(bookingHotelNameDelegate())
        delegatesManager.addDelegate(bookingInfoDelegate())
        delegatesManager.addDelegate(bookingCustomerInfoDelegate())
        delegatesManager.addDelegate(bookingTouristInfoDelegate())
        delegatesManager.addDelegate(bookingTouristAddDelegate())
        delegatesManager.addDelegate(bookingPriceDelegate())
        delegatesManager.addDelegate(bookingButtonDelegate())
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
                    tvNumberOfNights.text = String.format(
                        getString(R.string.tv_number_nights),
                        booking.numberOfNights
                    )
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
            val bgError =
                ContextCompat.getDrawable(context, R.drawable.bg_radius_edit_text_error)
            val bgUsual =
                ContextCompat.getDrawable(context, R.drawable.bg_radius_edit_text)

            bind {

                val watcherPhone = item.getMaskWatcherPhone()
                watcherPhone.installOn(binding.etPhone)
                binding.etPhone.setOnFocusChangeListener { _, b ->
                    if (b) {
                        binding.etPhone.text?.insert(0, "+")
                    }
                }

                buttonListenerPhone = {
                    val isValid = item.checkValidNumber(binding.etPhone.text.toString())
                    binding.etPhone.background =
                        if (!isValid) {
                            bgUsual
                        } else {
                            bgError
                        }
                    isValid
                }

                binding.etEmail.setOnFocusChangeListener { _, b ->
                    binding.etEmail.background = if (!b) {
                        if (item.checkValidEmail(binding.etEmail.text.toString())) {
                            buttonListenerMail = true
                            bgUsual
                        } else {
                            buttonListenerMail = false
                            bgError
                        }
                    } else {
                        buttonListenerMail = true
                        bgUsual
                    }
                }
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
            var visibility = View.VISIBLE
            var arrow: Drawable?
            bind {
                with(binding) {

                    tvTouristNumber.text = String.format(
                        getString(R.string.tv_tourist_count),
                        item.numberTourist
                    )

                    val list = listOf(
                        etName, etSecondName, etBirthday,
                        etNationality, etPassport, etPassportValidity
                    )
                    buttonListenerTouristInfo = {
                        checkEditText(context, list)
                    }
                    chipOpenTourist.setOnClickListener {
                        if (visibility == View.VISIBLE) {
                            visibility = View.GONE
                            arrow = getDrawable(R.drawable.ic_arrow_down)
                        } else {
                            visibility = View.VISIBLE
                            arrow = getDrawable(R.drawable.ic_arrow_up)
                        }
                        chipOpenTourist.closeIcon = arrow
                        inputLayoutName.visibility = visibility
                        inputLayoutSecondName.visibility = visibility
                        inputLayoutBirthday.visibility = visibility
                        inputLayoutNationality.visibility = visibility
                        inputLayoutPassport.visibility = visibility
                        inputLayoutPassportValidity.visibility = visibility
                    }
                }
            }
        }

    private fun bookingTouristAddDelegate(): AdapterDelegate<List<BookingDelegate>> =
        adapterDelegateViewBinding<
                BookingTouristAddItem,
                BookingDelegate,
                ItemBookingTouristAddBinding
                >(
            { layoutInflater, parent ->
                ItemBookingTouristAddBinding.inflate(layoutInflater, parent, false)
            }
        ) {
            bind {
                binding.chipTouristAdd.setOnClickListener {
                    addTouristListener?.invoke()
                }
            }
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
                    tvTourPrice.text = getBookingPrice(context, booking.tourPrice)
                    tvFuelCharge.text = getBookingPrice(context, booking.fuelCharge)
                    tvServiceCharge.text = getBookingPrice(context, booking.serviceCharge)
                    tvTotalPrice.text = getBookingPrice(context, getTotalPrice(booking))
                }
            }
        }

    private fun bookingButtonDelegate(): AdapterDelegate<List<BookingDelegate>> =
        adapterDelegateViewBinding<BookingButtonItem, BookingDelegate, ItemButtonBinding>(
            { layoutInflater, parent ->
                ItemButtonBinding.inflate(layoutInflater, parent, false)
            }
        ) {
            bind {
                binding.button.text = String.format(
                    getString(R.string.btn_total_pay),
                    getTotalPrice(item.booking)
                )
                binding.button.setOnClickListener {
                    val correctInfo = buttonListenerTouristInfo?.invoke() ?: false
                    val correctPhone = buttonListenerPhone?.invoke() ?: false
                    if (correctInfo && buttonListenerMail && correctPhone) {
                        launchOrderPaidFragment?.invoke()
                    }
                }
            }
        }

    private fun checkEditText(context: Context, list: List<EditText>): Boolean {
        val bgUsual = ContextCompat.getDrawable(context, R.drawable.bg_radius_edit_text)
        val bgError = ContextCompat.getDrawable(context, R.drawable.bg_radius_edit_text_error)
        var result = true
        for (i in list) {
            i.background = if (!i.text.isNullOrEmpty()) {
                bgUsual
            } else {
                result = false
                bgError
            }
        }
        return result
    }

    private fun getBookingPrice(context: Context, price: Int): String {
        return String.format(context.getString(R.string.booking_price), price)
    }

    private fun getTotalPrice(booking: Booking): Int {
        val tourPrice = booking.tourPrice
        val fuelCharge = booking.fuelCharge
        val serviceCharge = booking.serviceCharge
        return tourPrice + fuelCharge + serviceCharge
    }
}