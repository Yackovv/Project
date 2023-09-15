package com.example.project.items

import android.util.Patterns
import com.example.project.adapter_delegate.BookingDelegate
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

class BookingCustomerInfoItem : BookingDelegate {

    fun getMaskWatcherPhone(): MaskFormatWatcher {
        val mask = MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER)
        mask.placeholder = '*'
        mask.isShowingEmptySlots = true
        mask.isForbidInputWhenFilled = false
        return MaskFormatWatcher(mask)
    }

    fun checkValidNumber(numberPhone: String) : Boolean {
        return if(numberPhone.isNotEmpty()){
            !numberPhone.contains('*')
        } else {
            false
        }
    }
    fun checkValidEmail(email: String) = (email.isNotEmpty() &&
            Patterns.EMAIL_ADDRESS.matcher(email).matches())
}