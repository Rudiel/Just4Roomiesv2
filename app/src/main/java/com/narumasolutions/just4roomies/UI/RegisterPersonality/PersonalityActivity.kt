package com.narumasolutions.just4roomies.UI.RegisterPersonality

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mukesh.countrypicker.fragments.CountryPicker
import com.narumasolutions.just4roomies.R
import com.narumasolutions.just4roomies.databinding.LayoutPersonalityBinding
import kotlinx.android.synthetic.main.layout_personality.*

class PersonalityActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<LayoutPersonalityBinding>(this, R.layout.layout_personality)

        val viewModel = ViewModelProviders.of(this).get(PersonalityViewModel::class.java)

        viewModel.response.observe(this, Observer { })

        viewModel.showCountryPicker.observe(this, Observer { response -> if(response==true) showCountryPicker() })

        binding.personalityViewModel = viewModel



    }

    fun showCountryPicker(){
        val countryPicker : CountryPicker = CountryPicker.newInstance("Select your country")
        countryPicker.setListener { name, code, dialCode, flawDrawable ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ivCountry.imageTintMode = null
            }
            tvCountry.setText(name)
            ivCountry.setImageDrawable(resources.getDrawable(flawDrawable))
            countryPicker.dismiss()
        }
        countryPicker.setStyle(CountryPicker.STYLE_NORMAL,R.style.CountryDialogTheme)
        countryPicker.show(this.supportFragmentManager,"COUNTRY")

    }

    fun showErrorMessage() {

    }

    fun openMainActivity() {

    }

}