package com.narumasolutions.just4roomies.UI.Container.Fragments.SerachRoomies

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.narumasolutions.just4roomies.R
import com.narumasolutions.just4roomies.databinding.LayoutFragmentSearchroomieBinding

class SearchRoomies_Fragment : Fragment() {

    private lateinit var viewModel: SearchRoomiesViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<LayoutFragmentSearchroomieBinding>(inflater, R.layout.layout_fragment_searchroomie, container, false)
        val view = binding.root

        viewModel = ViewModelProviders.of(this).get(SearchRoomiesViewModel::class.java)

        binding.searchViewModel = viewModel

        binding.setLifecycleOwner(this)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        init()
        getRoomies()

    }

    private fun init() {

    }


    private fun getRoomies() {
        viewModel.getRoomies()
    }

}