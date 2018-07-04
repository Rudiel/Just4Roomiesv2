package com.narumasolutions.just4roomies.UI.Login;

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast
import com.mukesh.countrypicker.fragments.CountryPicker
import com.mukesh.countrypicker.BuildConfig

import com.narumasolutions.just4roomies.R;
import com.narumasolutions.just4roomies.databinding.LayoutLoginSesionBinding

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val binding = DataBindingUtil.setContentView<LayoutLoginSesionBinding>(this, R.layout.layout_login_sesion)

        val viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        viewModel.response.observe(this, Observer { response ->
            if (response == 400) openMainActivity() else response?.let { showErrorDialog(it) }
        })

        binding.loginViewModel = viewModel


    }

    fun showErrorDialog(code: Int) {

        var message = ""

        when (code) {
            500 -> message = "Bad Response"
            501 -> message = ""
            502 -> message = "Usuario no tiene el formato adecuado"
            503 -> message = "Contraseña no valida"
            else -> message = "Ocurrio un Error"
        }

        Toast.makeText(this, message + code, Toast.LENGTH_SHORT).show()
    }


    fun openMainActivity() {

    }

}
