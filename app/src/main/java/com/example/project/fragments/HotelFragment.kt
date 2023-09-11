package com.example.project.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.project.R
import com.example.project.adapter_delegate.HotelDelegate
import com.example.project.adapter_delegate.HotelListAdapter
import com.example.project.databinding.FragmentHotelBinding
import com.example.project.items.AboutTheHotelItem
import com.example.project.items.HotelItem
import com.example.project.viewmodels.HotelViewModel
import kotlinx.coroutines.launch

class HotelFragment : Fragment() {

    private var _binding: FragmentHotelBinding? = null
    private val viewModel by lazy {
        ViewModelProvider(this)[HotelViewModel::class.java]
    }
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentHotelBinding == null")

    private var hotelName = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHotelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getHotel()
        lifecycleScope.launch {
            viewModel.hotelFlow.collect { hotel ->
                hotel.aboutTheHotel?.let {
                    val items: List<HotelDelegate> = listOf(
                        HotelItem(hotel),
                        AboutTheHotelItem(it)
                    )
                    val adapter = HotelListAdapter()
                    adapter.items = items
                    binding.rvHotel.adapter = adapter
                    hotel.name?.let { name ->
                        hotelName = name
                    }
                }
            }
        }

        binding.btnSelectRoom.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, RoomListFragment.newInstance(hotelName))
                .addToBackStack(null)
                .commit()
        }
    }
}