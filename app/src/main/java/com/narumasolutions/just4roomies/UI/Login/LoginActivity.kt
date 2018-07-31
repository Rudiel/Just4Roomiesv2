package com.narumasolutions.just4roomies.UI.Login;

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.WindowManager;
import com.google.firebase.iid.FirebaseInstanceId
import com.narumasolutions.just4roomies.Creators.AlertDialog
import com.narumasolutions.just4roomies.Creators.LoadingDialog
import com.narumasolutions.just4roomies.Model.Request.User
import com.narumasolutions.just4roomies.Model.Response.ErrorResponse

import com.narumasolutions.just4roomies.R;
import com.narumasolutions.just4roomies.UI.Container.ContainerActivity
import com.narumasolutions.just4roomies.databinding.LayoutLoginSesionBinding
import kotlinx.android.synthetic.main.layout_login_sesion.*

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    private lateinit var loading: Dialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val binding = DataBindingUtil.setContentView<LayoutLoginSesionBinding>(this, R.layout.layout_login_sesion)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        viewModel.response.observe(this, Observer { response ->
            if (response != null) {
                manageResponse(response)
            }
            else
                showErrorDialog("Ocurrio un error intente de nuevo")
        })

        viewModel.loadingVisibility.observe(this, Observer { visibility -> if (visibility == View.VISIBLE) showLoading() else hideLoading() })

        binding.loginViewModel = viewModel

        binding.setLifecycleOwner(this)

        btLogin.setOnClickListener({
            validateFields()
        })

        loading = LoadingDialog().showLoadingDialog(this, "Iniciando Sesion..")

        val token=  FirebaseInstanceId.getInstance().getToken()
        Log.d("ID_NOT", "" +token)
       // Log.d("TOKEN",""+FirebaseInstanceId.getInstance().instanceId.result.token)


    }

    private fun manageResponse(responseBody : Any){
        if(responseBody is ErrorResponse){
            showErrorDialog(responseBody.message)
        }else {
            openMainActivity()
        }

        Log.d("RESPONSE",responseBody.toString())
    }

    fun showErrorDialog(message: String) {

        AlertDialog().showDialog(this, message, getString(R.string.login_login)).show()

    }

    private fun validateFields() {
        if (!Patterns.EMAIL_ADDRESS.matcher(etUsuario.text).matches())
            showErrorDialog("Formato incorrecto")
        else if (etPassword.text.isNullOrEmpty() or (etPassword.text.length < 6))
            showErrorDialog("Password no valido")
        else {
            btLogin.isActivated= false
            viewModel.doLogin(User(etUsuario.text.toString(), etPassword.text.toString()))
        }
    }

    fun openMainActivity() {
        startActivity(Intent(this@LoginActivity, ContainerActivity::class.java))
    }

    fun showLoading() {
        loading.show()
    }

    fun hideLoading() {
        btLogin.isActivated= true
        loading.hide()
    }

}
