package com.example.project.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.project.ProjectApp
import com.example.project.R
import com.example.project.databinding.FragmentRoomListBinding
import com.example.project.rv.RoomListAdapter
import com.example.project.viewmodels.RoomListViewModel
import com.example.project.viewmodels.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class RoomListFragment : Fragment() {

    private var _binding: FragmentRoomListBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentRoomListBinding == null")
    private val component by lazy {
        (requireActivity().application as ProjectApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[RoomListViewModel::class.java]
    }

    private var hotelName = ""

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArguments()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoomListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvToolBar.text = hotelName
        binding.ivBtnRoomBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        viewModel.getRoomList()

        lifecycleScope.launch {
            viewModel.roomListFlow.collect {
                for (i in it) {
                    Log.d("11111", it.toString())
                }
                val adapter = RoomListAdapter(it)
                binding.rvRooms.adapter = adapter
                adapter.btnClickListener = {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, BookingFragment.newInstance())
                        .addToBackStack(null)
                        .commit()
                }
            }
        }
    }

    private fun parseArguments() {
        val arg = requireArguments()
        if (!arg.containsKey(HOTEL_NAME)) {
            throw RuntimeException("Hotel name is absent")
        }
        hotelName = arg.getString(HOTEL_NAME, "")
    }

    companion object {

        private const val HOTEL_NAME = "hotel_name"
        fun newInstance(hotelName: String): RoomListFragment {
            return RoomListFragment().apply {
                arguments = Bundle().apply {
                    putString(HOTEL_NAME, hotelName)
                }
            }
        }
    }
}