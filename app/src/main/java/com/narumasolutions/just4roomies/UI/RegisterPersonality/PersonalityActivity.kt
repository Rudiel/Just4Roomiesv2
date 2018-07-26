package com.narumasolutions.just4roomies.UI.RegisterPersonality

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.mukesh.countrypicker.fragments.CountryPicker
import com.narumasolutions.just4roomies.Creators.AlertDialog
import com.narumasolutions.just4roomies.Creators.LoadingDialog
import com.narumasolutions.just4roomies.Model.Request.CreatePersonality
import com.narumasolutions.just4roomies.Model.Response.ErrorResponse
import com.narumasolutions.just4roomies.R
import com.narumasolutions.just4roomies.UI.Container.ContainerActivity
import com.narumasolutions.just4roomies.databinding.LayoutPersonalityBinding
import kotlinx.android.synthetic.main.layout_personality.*

class PersonalityActivity : AppCompatActivity() {

    private lateinit var loading: Dialog
    private lateinit var viewModel: PersonalityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<LayoutPersonalityBinding>(this, R.layout.layout_personality)

        viewModel = ViewModelProviders.of(this).get(PersonalityViewModel::class.java)

        viewModel.response.observe(this, Observer { response -> if (response != null) manageResponse(response) else showErrorMessage("") })

        viewModel.pbVisibility.observe(this, Observer { visibility -> if (visibility == View.GONE) hideLoading() else showLoading() })

        binding.personalityViewModel = viewModel

        rlNation.setOnClickListener { showCountryPicker() }

        btSavePersonality.setOnClickListener { validateFields() }

        loading = LoadingDialog().showLoadingDialog(this, "Guardando tu personalidad...")


    }

    private fun manageResponse(responseBody: Any) {
        if (responseBody is ErrorResponse) {

        } else
            openMainActivity()

    }

    private fun showCountryPicker() {

        val countryPicker: CountryPicker = CountryPicker.newInstance("Select your country")
        countryPicker.setListener { name, code, dialCode, flawDrawable ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ivCountry.imageTintMode = null
            }
            tvCountry.setText(name)
            ivCountry.setImageDrawable(resources.getDrawable(flawDrawable))
            countryPicker.dismiss()
        }
        countryPicker.setStyle(CountryPicker.STYLE_NORMAL, R.style.Theme_AppCompat_Light_Dialog)
        countryPicker.show(this.supportFragmentManager, "COUNTRY")

        // return this.context.getResources().getIdentifier("flag_" + drawable.toLowerCase(Locale.ENGLISH), "drawable", this.context.getPackageName());

    }


    fun showErrorMessage(message: String) {
        AlertDialog().showDialog(this, message, "").show()
    }

    fun openMainActivity() {
        startActivity(Intent(this@PersonalityActivity, ContainerActivity::class.java))
    }

    private fun validateFields() {


        val personality = CreatePersonality("",
                "", "",
                23,
                14.0,
                14.0,
                true,
                true,
                true,
                true,
                true,
                true)

        viewModel.registerPersonality(personality)
    }

    private fun hideLoading() {
        loading.hide()
    }

    private fun showLoading() {
        loading.show()
    }

}