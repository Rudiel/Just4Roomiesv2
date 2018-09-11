package com.narumasolutions.just4roomies.UI.Container.Fragments.SerachRoomies

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import com.daprlabs.cardstack.SwipeDeck
import com.narumasolutions.just4roomies.Creators.FiltersDialog
import com.narumasolutions.just4roomies.Creators.LoadingDialog
import com.narumasolutions.just4roomies.Model.Response.Roomie
import com.narumasolutions.just4roomies.R
import com.narumasolutions.just4roomies.databinding.LayoutFragmentSearchroomieBinding
import kotlinx.android.synthetic.main.layout_container.*
import kotlinx.android.synthetic.main.layout_fragment_searchroomie.*

class SearchRoomies_Fragment : Fragment() {

    private lateinit var viewModel: SearchRoomiesViewModel
    private lateinit var roomiesList: List<Roomie>
    private lateinit var loading: Dialog
    private lateinit var currentRoomie: Roomie


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<LayoutFragmentSearchroomieBinding>(inflater, R.layout.layout_fragment_searchroomie, container, false)
        val view = binding.root

        viewModel = ViewModelProviders.of(this).get(SearchRoomiesViewModel::class.java)

        viewModel.response.observe(this, Observer { if (it != null) onRoomiesSucces(it) })

        viewModel.loadingVisibility.observe(this, Observer { if (it == View.VISIBLE) showLoading() else hideLoading() })

        binding.searchViewModel = viewModel

        binding.setLifecycleOwner(this)


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        init()

        getRoomies()

        loading = LoadingDialog().showLoadingDialogTrans(context!!, "Obteniendo Roomies...")
    }

    private fun init() {

        sdRoomies.setEventCallback(object : SwipeDeck.SwipeEventCallback {


            override fun cardSwipedLeft(position: Int) {
                if (position + 1 < roomiesList.size) {
                    currentRoomie = roomiesList[position + 1]
                    checkHaveRoomAvailable()
                }
            }

            override fun cardSwipedRight(position: Int) {
                sendRequest(roomiesList[position])
                if (position + 1 < roomiesList.size) {
                    currentRoomie = roomiesList[position + 1]
                    checkHaveRoomAvailable()
                }


            }


            override fun cardActionUp() {
            }

            override fun cardsDepleted() {
                //Cuando se acaban las cards
                llSearchRoomiesActions.visibility = View.INVISIBLE
                clRoomiesEnd.visibility = View.VISIBLE
            }

            override fun cardActionDown() {
            }


        })


        val displayMetrics = DisplayMetrics()
        val windowManager = activity?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        val paramsCardView = sflRoomies.layoutParams

        val paramsActions = llSearchRoomiesActions.layoutParams

        try {
            paramsCardView.height = displayMetrics.heightPixels - (toolbar.height * 2) - (displayMetrics.heightPixels / 4)
            paramsActions.height = displayMetrics.heightPixels - paramsCardView.height
            // paramsCardView.height = flContainer.measuredHeight -(toolbar.height *2) - (flContainer.measuredHeight/4 + 50)
            // paramsActions.height = flContainer.measuredHeight - paramsCardView.height
        } catch (e: Exception) {
            e.printStackTrace()
        }

        llSearchRoomiesActions.layoutParams = paramsActions
        sflRoomies.layoutParams = paramsCardView

        sdRoomies.setLeftImage(R.id.left_image)
        sdRoomies.setRightImage(R.id.right_image)

        btSearchRoomieLike.setOnClickListener { sdRoomies.swipeTopCardRight(250) }

        btSearchRoomieNope.setOnClickListener { sdRoomies.swipeTopCardLeft(250) }

        btSearchRoomieRoom.setOnClickListener { openRoomDialog() }

        btRoomiesEnd.setOnClickListener { getRoomies() }

        val ivFilter = activity?.toolbar?.findViewById<ImageView>(R.id.ivFilter)
        ivFilter?.setOnClickListener { showFilterDialog() }

    }


    private fun getRoomies() {
        viewModel.getRoomies()

    }

    private fun onRoomiesSucces(roomies: List<Roomie>) {
        this.roomiesList = roomies
        sdRoomies.setAdapter(RoomiesDeck_Adapter(activity!!.applicationContext, R.layout.layout_searchroomie_item, roomiesList))
        clRoomiesEnd.visibility = View.GONE
        llSearchRoomiesActions.visibility = View.VISIBLE
        currentRoomie = roomiesList[0]
    }


    private fun sendRequest(roomie: Roomie) {
        Log.d("Roomie", "" + roomie.Nombre)
    }


    private fun showLoading() {
        loading.show()
    }

    private fun hideLoading() {
        loading.hide()
    }

    private fun openRoomDialog() {

    }

    private fun showFilterDialog() {

        FiltersDialog().showFiltersDialog(context!!, activity!!.supportFragmentManager).show()
    }

    private fun checkHaveRoomAvailable() {
        btSearchRoomieRoom.isEnabled = currentRoomie.Room?.Id != null
    }

}