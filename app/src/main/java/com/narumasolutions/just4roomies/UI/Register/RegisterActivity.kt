package com.narumasolutions.just4roomies.UI.Register

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.narumasolutions.just4roomies.Creators.AlertDialog
import com.narumasolutions.just4roomies.Creators.LoadingDialog
import com.narumasolutions.just4roomies.Model.Request.RegisterNew
import com.narumasolutions.just4roomies.Model.Response.UserResponse
import com.narumasolutions.just4roomies.R
import com.narumasolutions.just4roomies.databinding.LayoutRegisterBinding
import kotlinx.android.synthetic.main.layout_register.*

 class RegisterActivity : AppCompatActivity() {

    private lateinit var viewModel: RegisterViewModel
    private lateinit var loading : Dialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val binding = DataBindingUtil.setContentView<LayoutRegisterBinding>(this, R.layout.layout_register)

        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)

        viewModel.response.observe(this, Observer { response ->
            if (response != null) {
                showResponse(response)
            }
        })

       viewModel.pbVisibility.observe(this, Observer { response-> if(response == View.GONE) hideLoading() else showLoading() })

        binding.registerViewModel = viewModel

        btRegister.setOnClickListener({ validateFields() })

       loading= LoadingDialog().showLoadingDialog(this, "Registrando tu usuario...")


    }

    private fun showResponse(response: UserResponse) {
        if (response.Code == 400)
            openMainActivity()
        else showErrorMessage(response.Message)
    }

    private fun validateFields() {

        if (etName.text.toString().isEmpty())
            showErrorMessage("Nombre")
        else if (etLastName.text.toString().isEmpty())
            showErrorMessage("Apellido")
        else if (etEmail.text.toString().isEmpty())
            showErrorMessage("Email")
        else if (etPassword.text.toString().isEmpty())
            showErrorMessage("Password")
        else if (etRepitPassword.text.toString().isEmpty())
            showErrorMessage("RepitPassword")
        else {
            if (!etPassword.text.toString().equals(etRepitPassword.text.toString()))
                showErrorMessage("Son diferentes")
            else
                viewModel.doRegister(RegisterNew(
                        etName.text.toString(),
                        etLastName.text.toString(),
                        etEmail.text.toString(),
                        etPassword.text.toString()
                ))
        }
    }


    private fun showErrorMessage(message: String) {
        AlertDialog().showDialog(this, message, getString(R.string.register)).show()
    }

    private fun openMainActivity() {
        finish()
    }

    private fun hideLoading(){
        loading.dismiss()
    }

    private fun showLoading(){
        loading.show()
    }


}