package com.narumasolutions.just4roomies.UI.Container.Fragments.Room

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.narumasolutions.just4roomies.Creators.AlertDialog
import com.narumasolutions.just4roomies.Model.Request.Room
import com.narumasolutions.just4roomies.R
import com.narumasolutions.just4roomies.databinding.LayoutFragmentRoomBinding
import kotlinx.android.synthetic.main.layout_fragment_room.*

class Room_Fragment : Fragment() {

    private lateinit var viewModel: RoomViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<LayoutFragmentRoomBinding>(inflater, R.layout.layout_fragment_room, container, false)
        val view = binding.root

        viewModel = ViewModelProviders.of(this).get(RoomViewModel::class.java)

        binding.roomViewModel = viewModel

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btDate.setOnClickListener { showDatePicker() }

        btSaveRoom.setOnClickListener { validateFiels() }

    }

    private fun validateFiels() {

        if (etPrice.text.trim().toString().isEmpty())
            showErrorMessage("Precio de Habitacion Necesarios")
        else if (btDate.text.toString().equals(resources.getString(R.string.ofrecer_fecha).toString()))
            showErrorMessage("Fecha de Disponibilidad Necesaria")
        else {
            val room = Room("", "12/12/12", true, 12.1, 12.23, 12.23, "Test1", "Test2", "Test3")
            viewModel.saveRoom(room)
        }
    }

    private fun showDatePicker() {
        val datePicker = DatePickerFragment()
        datePicker.show(activity?.supportFragmentManager, "datePicker")
    }

    private fun showErrorMessage(message: String) {
        AlertDialog().showDialog(context!!, message, resources.getString(R.string.ofrecer_alerta_titulo)).show()
    }

}