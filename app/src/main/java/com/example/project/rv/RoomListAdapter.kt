package com.example.project.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.domain.enteties.Room
import com.example.project.R
import com.example.project.databinding.ItemRoomBinding
import com.google.android.material.chip.Chip
import java.text.NumberFormat
import java.util.Locale

class RoomListAdapter(private val roomList: List<Room>) : RecyclerView.Adapter<RoomViewHolder>() {

    var btnClickListener: (() -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val binding = ItemRoomBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RoomViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return roomList.size
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val binding = holder.binding
        val room = roomList[position]
        binding.btnSelectRoom.setOnClickListener {
            btnClickListener?.invoke()
        }
        with(binding) {
            vpRoom.adapter = ViewPagerAdapter(room.imageUrls)
            vpRoom.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            circleIndicator.setViewPager(vpRoom)
            tvRoomName.text = room.name
            val price = NumberFormat.getNumberInstance(Locale.getDefault()).format(room.price)
            tvPrice.text = String.format(
                holder.itemView.context.getString(R.string.room_price), price
            )
            tvConditionsRoom.text = room.pricePer.lowercase(Locale.getDefault())
            for (i in room.peculiarities) {
                Chip(root.context).apply {
                    text = i
                    chipGroupRoom.addView(this)
                }
            }
        }
    }
}