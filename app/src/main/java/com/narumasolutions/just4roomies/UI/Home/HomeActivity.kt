package com.narumasolutions.just4roomies.UI.Home

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.WindowManager
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.narumasolutions.just4roomies.Creators.AlertDialog
import com.narumasolutions.just4roomies.R
import com.narumasolutions.just4roomies.UI.Login.LoginActivity
import com.narumasolutions.just4roomies.UI.Register.RegisterActivity
import kotlinx.android.synthetic.main.layout_login.*

class HomeActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var bindig: ScriptGroup.Binding

    private lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //checkLogin()

        this.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.layout_login)

        init()

    }

    fun init() {

        callbackManager = CallbackManager.Factory.create()

        btIniciarSesion.setOnClickListener {

            startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
        }

        btRegistrarse.setOnClickListener {
            startActivity(Intent(this@HomeActivity, RegisterActivity::class.java))
        }

        btFacebook.setReadPermissions("email")

        btLoginFacebook.setOnClickListener({ btFacebook.performClick() })

        btFacebook.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {

            }

            override fun onCancel() {
                showErroDialog("Has cancelado el inicio de sesion")
            }


            override fun onError(error: FacebookException?) {
                showErroDialog(error.toString())
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun checkLogin() {
        val intent = Intent(this@HomeActivity, RegisterActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    private fun showErroDialog(message: String) {
        AlertDialog().showDialog(this, message, "Facebook Login")
    }
}
