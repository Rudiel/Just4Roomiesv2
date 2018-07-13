package com.narumasolutions.just4roomies.UI.Login;

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns
import android.view.WindowManager;
import com.narumasolutions.just4roomies.Creators.AlertDialog
import com.narumasolutions.just4roomies.Model.Request.User

import com.narumasolutions.just4roomies.R;
import com.narumasolutions.just4roomies.UI.Container.ContainerActivity
import com.narumasolutions.just4roomies.databinding.LayoutLoginSesionBinding
import kotlinx.android.synthetic.main.layout_login_sesion.*

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val binding = DataBindingUtil.setContentView<LayoutLoginSesionBinding>(this, R.layout.layout_login_sesion)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        viewModel.response.observe(this, Observer { response ->
            if (response == 200) openMainActivity() else response?.let { showErrorDialog(it) }
        })

        binding.loginViewModel = viewModel

        binding.setLifecycleOwner(this)

        btLogin.setOnClickListener({
            validateFields()
        })


    }

    fun showErrorDialog(code: Int) {

        var message = ""

        when (code) {
            500 -> message = "Bad Response"
            501 -> message = ""
            502 -> message = "Usuario no tiene el formato adecuado"
            503 -> message = "Contraseña no valida"
            401 -> message = "No esta autorizado"
            else -> message = "Ocurrio un Error"
        }

        AlertDialog().showDialog(this , message, getString(R.string.login_login)).show()

    }

    private fun validateFields() {
        if (!Patterns.EMAIL_ADDRESS.matcher(etUsuario.text).matches())
            showErrorDialog(502)
        else if (etPassword.text.isNullOrEmpty() or  (etPassword.text.length < 6))
            showErrorDialog(503)
        else {
            viewModel.doLogin(User(etUsuario.text.toString(),etPassword.text.toString()))
        }
    }

    fun openMainActivity() {
        startActivity(Intent(this@LoginActivity, ContainerActivity::class.java))
    }

}
