package com.narumasolutions.just4roomies.UI.Container.Fragments.Matches.Chats

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.narumasolutions.just4roomies.Model.Response.Chat
import com.narumasolutions.just4roomies.R

class Chats_Adapter(private val chatsList: List<Chat>) : RecyclerView.Adapter<Chats_Adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_searchroomie_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return chatsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }


    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    }

}