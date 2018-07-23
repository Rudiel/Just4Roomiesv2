package com.narumasolutions.just4roomies.UI.Container.Fragments.SerachRoomies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.narumasolutions.just4roomies.Model.Response.Roomie
import com.narumasolutions.just4roomies.R
import com.narumasolutions.just4roomies.databinding.LayoutFragmentSearchroomieBinding
import kotlinx.android.synthetic.main.layout_container.*
import kotlinx.android.synthetic.main.layout_fragment_searchroomie.*

class SearchRoomies_Fragment : Fragment() {

    private lateinit var viewModel: SearchRoomiesViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<LayoutFragmentSearchroomieBinding>(inflater, R.layout.layout_fragment_searchroomie, container, false)
        val view = binding.root

        viewModel = ViewModelProviders.of(this).get(SearchRoomiesViewModel::class.java)

        viewModel.response.observe(this, Observer { if (it != null) onRoomiesSucces(it) })

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

        val displayMetrics = DisplayMetrics()
        val windowManager = activity?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        val paramsCardView = csvRoomies.layoutParams

        val paramsActions = llSearchRoomiesActions.layoutParams


        try {
            flContainer.parent
            paramsCardView.height = displayMetrics.heightPixels - (displayMetrics.heightPixels / 4 + 50) - (toolbar.height*2)
            paramsActions.height = displayMetrics.heightPixels - paramsCardView.height
            // paramsCardView.height = flContainer.measuredHeight -(toolbar.height *2) - (flContainer.measuredHeight/4 + 50)
            // paramsActions.height = flContainer.measuredHeight - paramsCardView.height
        } catch (e: Exception) {
            e.printStackTrace()
        }

        llSearchRoomiesActions.layoutParams = paramsActions
        csvRoomies.layoutParams = paramsCardView


    }


    private fun getRoomies() {
        viewModel.getRoomies()
    }

    private fun onRoomiesSucces(roomies: List<Roomie>) {

        csvRoomies.setAdapter(RoomiesDeck_Adapter(activity!!.applicationContext, R.layout.layout_searchroomie_item, roomies))
    }

}