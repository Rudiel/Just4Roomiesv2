package com.narumasolutions.just4roomies.UI.Container

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.narumasolutions.just4roomies.R
import kotlinx.android.synthetic.main.layout_container.*

class ContainerActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout_container)

        setSupportActionBar(toolbar)

        val headerView = navigation_view.getHeaderView(0)

        navigation_view.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_buscarroomie -> {
                    closeSession()
                     true
                }

                R.id.menu_ajustes -> {
                    closeSession()
                    true
                }
                R.id.menu_editarperfil ->{
                    closeSession()
                    true
                }
                R.id.menu_ofrecer ->{
                    closeSession()
                    true
                }

                R.id.menu_chats ->{
                    closeSession()
                    true
                }
                else -> {
                    closeSession()
                    true
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return super.onPrepareOptionsMenu(menu)
    }

    fun closeSession(){

    }



}