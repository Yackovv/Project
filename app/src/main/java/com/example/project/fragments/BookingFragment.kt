package com.example.project.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.project.adapter_delegate.BookingDelegate
import com.example.project.adapter_delegate.BookingListAdapter
import com.example.project.databinding.FragmentBookingBinding
import com.example.project.items.BookingCustomerInfoItem
import com.example.project.items.BookingHotelNameItem
import com.example.project.items.BookingInfoItem
import com.example.project.items.BookingPriceItem
import com.example.project.items.BookingTouristAdd
import com.example.project.items.BookingTouristInfoItem
import com.example.project.viewmodels.BookingViewModel
import kotlinx.coroutines.launch

class BookingFragment : Fragment() {

    private var _binding: FragmentBookingBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentBookingBinding == null")

    private val viewModel by lazy {
        ViewModelProvider(this)[BookingViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getBooking()
        lifecycleScope.launch {
            viewModel.bookingFlow.collect {
                val items: List<BookingDelegate> = listOf(
                    BookingHotelNameItem(it),
                    BookingInfoItem(it),
                    BookingCustomerInfoItem(),
                    BookingTouristInfoItem(),
                    BookingTouristInfoItem(),
                    BookingTouristAdd(),
                    BookingPriceItem(it)
                )
                val adapter = BookingListAdapter()
                adapter.items = items
                binding.rvBooking.adapter = adapter
            }
        }

        binding.ivBtnBookingBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    companion object {
        fun newInstance(): BookingFragment{
            return BookingFragment()
        }
    }
}