package com.narumasolutions.just4roomies.Creators

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.narumasolutions.just4roomies.R
import io.apptik.widget.MultiSlider

class FiltersDialog {

    fun showFiltersDialog(context: Context): Dialog {

        val inflater = LayoutInflater.from(context)
        val v = inflater.inflate(R.layout.layout_filter_dialog, null, false)

        val seekPrice = v.findViewById<MultiSlider>(R.id.seekBarPrice)
        val seekAge = v.findViewById<MultiSlider>(R.id.seekBarAge)

        val btApply = v.findViewById<Button>(R.id.btFilterApply)
        val btReset = v.findViewById<Button>(R.id.btFilterReset)
        val btDate = v.findViewById<Button>(R.id.btFilterDate)

        val tvPriceMax = v.findViewById<TextView>(R.id.tvFilterPriceMax)
        val tvPriceMin = v.findViewById<TextView>(R.id.tvFilterPriceMin)

        val tvAgeMin = v.findViewById<TextView>(R.id.tvFilterAgeMin)
        val tvAgeMax = v.findViewById<TextView>(R.id.tvFilterAgeMax)

        val dialog = Dialog(context)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(v)

        seekPrice.min = 500
        seekPrice.max = 10000
        seekPrice.setOnThumbValueChangeListener { multiSlider, thumb, thumbIndex, value ->
            when (thumbIndex) {
                0 -> tvPriceMin.text = "$ " + value.toString()
                1 -> tvPriceMax.text = "$ " + value.toString()
            }
        }

        seekAge.min = 18
        seekAge.max = 99
        seekAge.setOnThumbValueChangeListener { multiSlider, thumb, thumbIndex, value ->
            when (thumbIndex) {
                0 -> tvAgeMin.text = value.toString()
                1 -> tvAgeMax.text = value.toString()
            }

        }

        return dialog

    }
}