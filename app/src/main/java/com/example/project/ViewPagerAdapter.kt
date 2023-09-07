package com.example.project

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project.databinding.ItemIvHotelPhotoBinding

class ViewPagerAdapter(private val list: List<String>): RecyclerView.Adapter<ViewPagerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val binding = ItemIvHotelPhotoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewPagerHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        val photo = list[position]
        Log.d("11111", photo)
        Glide.with(holder.itemView.context).load(photo).into(holder.binding.ivHotelPicture)
    }
}