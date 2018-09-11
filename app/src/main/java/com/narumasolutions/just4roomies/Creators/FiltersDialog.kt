package com.narumasolutions.just4roomies.Creators

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.Window
import android.widget.*
import com.narumasolutions.just4roomies.R
import io.apptik.widget.MultiSlider
import java.util.*

class FiltersDialog {

    private lateinit var context: Context
    private lateinit var fragmentManager: FragmentManager

    //Slider Price
    private lateinit var seekPrice: MultiSlider
    private lateinit var tvPriceMax: TextView
    private lateinit var tvPriceMin: TextView

    //Slider Age
    private lateinit var seekAge: MultiSlider
    private lateinit var tvAgeMin: TextView
    private lateinit var tvAgeMax: TextView

    //ImageButtons Genre
    private lateinit var ibMan: ImageButton
    private lateinit var ibWoman: ImageButton
    private lateinit var ibNeutral: ImageButton

    private lateinit var swFurnished: Switch

    private lateinit var btDate: Button


    fun showFiltersDialog(context: Context, fragmentManager: FragmentManager): Dialog {

        this.context = context
        this.fragmentManager = fragmentManager

        val inflater = LayoutInflater.from(context)
        val v = inflater.inflate(R.layout.layout_filter_dialog, null, false)

        seekPrice = v.findViewById(R.id.seekBarPrice)
        seekAge = v.findViewById(R.id.seekBarAge)

        val btApply = v.findViewById<Button>(R.id.btFilterApply)
        val btReset = v.findViewById<Button>(R.id.btFilterReset)
        btDate = v.findViewById(R.id.btFilterDate)

        tvPriceMax = v.findViewById(R.id.tvFilterPriceMax)
        tvPriceMin = v.findViewById(R.id.tvFilterPriceMin)

        tvAgeMin = v.findViewById(R.id.tvFilterAgeMin)
        tvAgeMax = v.findViewById(R.id.tvFilterAgeMax)

        ibMan = v.findViewById(R.id.ibMan)
        ibWoman = v.findViewById(R.id.ibWoman)
        ibNeutral = v.findViewById(R.id.ibNeutral)

        swFurnished = v.findViewById(R.id.swFurnished)

        val dialog = Dialog(context)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(v)

        val window = dialog.window

        val params = window.attributes
        params.width = LinearLayout.LayoutParams.MATCH_PARENT
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT

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

        ibMan.setOnClickListener {
            setDefaultButtonsState()
            ibMan.setImageDrawable(context.resources.getDrawable(R.drawable.flag_ac))
        }
        ibNeutral.setOnClickListener {
            setDefaultButtonsState()
            ibNeutral.setImageDrawable(context.resources.getDrawable(R.drawable.flag_ac))

        }
        ibWoman.setOnClickListener {
            setDefaultButtonsState()
            ibWoman.setImageDrawable(context.resources.getDrawable(R.drawable.flag_ac))
        }

        btDate.setOnClickListener { openDateDialog() }

        btReset.setOnClickListener { restoreValues() }


        return dialog

    }

    private fun openDateDialog() {

        val calendar = Calendar.getInstance()

        DatePickerDialog(context,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth -> btDate.text = " " + dayOfMonth + "/" + month + "/" + year },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE)).show()
    }

    private fun setDefaultButtonsState() {
        ibMan.setImageDrawable(context.resources.getDrawable(R.drawable.flag_id))
        ibWoman.setImageDrawable(context.resources.getDrawable(R.drawable.flag_id))
        ibNeutral.setImageDrawable(context.resources.getDrawable(R.drawable.flag_id))

    }

    private fun restoreValues() {
        setDefaultButtonsState()
        seekAge.repositionThumbs()
        seekPrice.repositionThumbs()
        swFurnished.isChecked = false
        btDate.text = context.getString(R.string.ofrecer_fecha)

    }


}