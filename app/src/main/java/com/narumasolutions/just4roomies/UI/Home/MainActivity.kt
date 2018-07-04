package com.narumasolutions.just4roomies.UI.Home

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.WindowManager
import com.mukesh.countrypicker.fragments.CountryPicker
import com.mukesh.countrypicker.interfaces.CountryPickerListener
import com.narumasolutions.just4roomies.R
import com.narumasolutions.just4roomies.UI.Login.LoginActivity
import com.narumasolutions.just4roomies.UI.Register.RegisterActivity
import kotlinx.android.synthetic.main.layout_login.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var bindig: ScriptGroup.Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.layout_login)

        init()

    }

    fun init() {

        initViewModel()

        btIniciarSesion.setOnClickListener {

            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }

        btRegistrarse.setOnClickListener {
            startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
        }
    }

    fun initViewModel() {

    }
}
