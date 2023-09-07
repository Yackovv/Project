package com.example.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.project.databinding.FragmentHotelBinding
import kotlinx.coroutines.launch

class HotelFragment : Fragment() {

    private var _binding: FragmentHotelBinding? = null
    private val viewModel by lazy {
        ViewModelProvider(this)[HotelViewModel::class.java]
    }
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentHotelBinding == null")

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

        requireActivity().actionBar?.let {
            it.title = "Hotel"
        }

        viewModel.getHotel()
        lifecycleScope.launch {
            viewModel.hotelFlow.collect {
                val aboutTheHotelItem = it.aboutTheHotel

                if (aboutTheHotelItem != null) {
                    val items: List<HotelDelegate> = listOf(
                        HotelItem(it),
                        AboutTheHotelItem(aboutTheHotelItem)
                    )
                    val adapter = HotelListAdapter()
                    adapter.items = items
                    binding.rvHotel.adapter = adapter
                }
            }
        }
    }
}