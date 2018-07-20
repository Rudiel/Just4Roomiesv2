package com.narumasolutions.just4roomies.UI.Container.Fragments.SerachRoomies

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.narumasolutions.just4roomies.Model.Response.Roomie
import com.narumasolutions.just4roomies.R
import com.narumasolutions.just4roomies.databinding.LayoutSearchroomieItemBinding
import io.reactivex.annotations.Nullable

class RoomiesDeck_Adapter( context : Context ,resource: Int, roomiesList: List<Roomie>) : ArrayAdapter<Roomie>( context, resource, roomiesList) {

    var resource: Int = resource
    var list: List<Roomie> = roomiesList
    var vi: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder: ViewHolder
        val retView: View

        if (convertView == null) {
            val binding: LayoutSearchroomieItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent?.context), R.layout.layout_searchroomie_item, parent, false)

            holder = ViewHolder(binding)
            holder.bind(list.get(position), parent!!.context)
            retView= binding.root
            retView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
            holder.bind(list.get(position),parent!!.context)
            retView = convertView
        }
        return retView
    }

    internal class ViewHolder(private val binding: LayoutSearchroomieItemBinding) {
        private val viewModel = RoomieViewModel()

        fun bind(roomie: Roomie, context: Context) {
            viewModel.bind(roomie,context)
            binding.viewModel= viewModel
        }
    }


}