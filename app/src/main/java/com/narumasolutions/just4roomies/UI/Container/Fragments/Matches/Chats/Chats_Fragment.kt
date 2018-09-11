package com.narumasolutions.just4roomies.UI.Container.Fragments.Matches.Chats

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.narumasolutions.just4roomies.R
import com.narumasolutions.just4roomies.databinding.LayoutFragmentChatsBinding


class Chats_Fragment : Fragment() {


    private lateinit var viewModel: ChatsViewModel
    private lateinit var rvChats : RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<LayoutFragmentChatsBinding>(inflater, R.layout.layout_fragment_chats, container, false)

        val view = binding.root

        viewModel = ViewModelProviders.of(this).get(ChatsViewModel::class.java)

        binding.chatViewModel = viewModel

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvChats.layoutManager = LinearLayoutManager(activity)

        rvChats.setHasFixedSize(true)


    }

}