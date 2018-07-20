package com.narumasolutions.just4roomies.UI.Container.Fragments.SerachRoomies

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.narumasolutions.just4roomies.Model.Response.Roomie
import com.narumasolutions.just4roomies.R
import com.narumasolutions.just4roomies.databinding.LayoutSearchroomieItemBinding

class Roomies_Adapter : RecyclerView.Adapter<Roomies_Adapter.ViewHolder>() {


    private lateinit var roomiesList: List<Roomie>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: LayoutSearchroomieItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.layout_searchroomie_item, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (::roomiesList.isInitialized) roomiesList.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(roomiesList.get(position))
    }

    class ViewHolder(private val binding: LayoutSearchroomieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = RoomieViewModel()

        fun bind(roomie: Roomie) {
            //viewModel.bind(roomie)
            binding.viewModel = viewModel
        }
    }

    fun updateRoomiesList(roomiesList: List<Roomie>) {
        this.roomiesList = roomiesList
    }

}