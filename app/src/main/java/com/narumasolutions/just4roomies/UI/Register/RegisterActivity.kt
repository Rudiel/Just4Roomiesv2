package com.narumasolutions.just4roomies.UI.Register

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.narumasolutions.just4roomies.R
import com.narumasolutions.just4roomies.databinding.LayoutRegisterBinding

class RegisterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<LayoutRegisterBinding>(this, R.layout.layout_register)

        val viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)

        viewModel.response.observe(this, Observer { })

        binding.registerViewModel = viewModel
    }

    fun showErrorMessage() {

    }

    fun openMainActivity() {

    }


}