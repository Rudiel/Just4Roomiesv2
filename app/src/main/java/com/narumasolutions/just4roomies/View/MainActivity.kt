package com.narumasolutions.just4roomies.View

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.narumasolutions.just4roomies.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.layout_login)
    }
}
