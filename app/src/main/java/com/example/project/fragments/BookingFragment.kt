package com.example.project.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.project.NumConvert
import com.example.project.R
import com.example.project.adapter_delegate.BookingDelegate
import com.example.project.adapter_delegate.BookingListAdapter
import com.example.project.databinding.FragmentBookingBinding
import com.example.project.items.BookingButtonItem
import com.example.project.items.BookingCustomerInfoItem
import com.example.project.items.BookingHotelNameItem
import com.example.project.items.BookingInfoItem
import com.example.project.items.BookingPriceItem
import com.example.project.items.BookingTouristAddItem
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

    private var touristCounter = 0
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

        val adapter = BookingListAdapter()
        val items = mutableListOf<BookingDelegate>()
        viewModel.getBooking()
        lifecycleScope.launch {
            viewModel.bookingFlow.collect {
                val item = listOf(
                    BookingHotelNameItem(it),
                    BookingInfoItem(it),
                    BookingCustomerInfoItem(),
                    BookingTouristInfoItem(NumConvert.getWordFromNumber(touristCounter++)),
                    BookingTouristAddItem(),
                    BookingPriceItem(it),
                    BookingButtonItem(it)
                )
                items.addAll(item)
                adapter.items = items
                binding.rvBooking.adapter = adapter
            }
        }

        adapter.addTouristListener = {
            items.add(
                items.size - 3, BookingTouristInfoItem(
                    NumConvert.getWordFromNumber(touristCounter++)
                )
            )
            adapter.notifyItemInserted(items.size - 3)
        }

        binding.ivBtnBookingBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        adapter.launchOrderPaidFragment = {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, OrderIsPaidFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
    }

    companion object {
        fun newInstance(): BookingFragment {
            return BookingFragment()
        }
    }
}