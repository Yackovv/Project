package com.example.project.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.project.R
import com.example.project.databinding.FragmentOrderPaidBinding
import kotlin.random.Random

class OrderIsPaidFragment : Fragment() {

    private var _binding: FragmentOrderPaidBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentOrderPaidBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderPaidBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBtnOrderBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.btnOk.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, HotelFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }

        val numberOrder = Random.nextInt(1_000_000, 9_999_999)
        binding.tvVerificationOrder.text = String.format(
            getString(R.string.tv_verification_order),
            numberOrder
        )
    }

    companion object {
        fun newInstance(): OrderIsPaidFragment {
            return OrderIsPaidFragment()
        }
    }
}