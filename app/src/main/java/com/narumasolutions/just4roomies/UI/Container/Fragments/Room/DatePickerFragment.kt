package com.narumasolutions.just4roomies.UI.Container.Fragments.Room

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.widget.Button
import android.widget.DatePicker
import com.narumasolutions.just4roomies.Creators.FiltersDialog
import com.narumasolutions.just4roomies.R
import java.util.*

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

        val fragment = fragmentManager?.findFragmentByTag("datePickerRoom")

        if (fragment != null) {
            val btDateRoom = activity?.findViewById<Button>(R.id.btDate)

            btDateRoom?.text = "" + dayOfMonth + "/" + month + "/" + year
        }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(activity, this, year, month, day)
        datePickerDialog.datePicker.minDate = c.timeInMillis
        return datePickerDialog
    }


}