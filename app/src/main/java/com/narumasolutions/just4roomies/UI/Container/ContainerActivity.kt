package com.narumasolutions.just4roomies.UI.Container

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.narumasolutions.just4roomies.R
import com.narumasolutions.just4roomies.UI.Container.Fragments.EditProfile.EditProfile_Fragment
import com.narumasolutions.just4roomies.UI.Container.Fragments.Matches.Matches_Fragment
import com.narumasolutions.just4roomies.UI.Container.Fragments.Room.Room_Fragment
import com.narumasolutions.just4roomies.UI.Container.Fragments.SerachRoomies.SearchRoomies_Fragment
import com.narumasolutions.just4roomies.UI.Container.Fragments.Settings.Settings_Fragment
import kotlinx.android.synthetic.main.layout_container.*

class ContainerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.layout_container)

        init()

        setFragment(SearchRoomies_Fragment(), false)

    }

    private fun init() {

        ivFilter.visibility = View.VISIBLE

        setSupportActionBar(toolbar)

        val headerView = navigation_view.getHeaderView(0)

        navigation_view.setNavigationItemSelectedListener {
            navigation_drawer.closeDrawers()
            ivFilter.visibility = View.GONE
            when (it.itemId) {

                R.id.menu_ajustes -> {
                    setFragment(Settings_Fragment(),false)
                    closeSession()
                    true
                }
                R.id.menu_editarperfil -> {
                    setFragment(EditProfile_Fragment(), false)
                    clearSelectionBottom()
                    true
                }
                else -> {
                    closeSession()
                    true
                }
            }
        }

        bnMenu.setOnNavigationItemSelectedListener {

            ivFilter.visibility = View.GONE

            when (it.itemId) {
                R.id.bnChat -> {
                    setFragment(Matches_Fragment(), false)
                }

                R.id.bnRoomies -> {
                    ivFilter.visibility = View.VISIBLE
                    setFragment(SearchRoomies_Fragment(), false)
                }

                R.id.bnOfrecer ->{
                    setFragment(Room_Fragment(), false)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }


        val mDrawerToggle = ActionBarDrawerToggle(
                this,
                navigation_drawer,
                toolbar,
                R.string.app_name
                , R.string.app_name)

        navigation_drawer.addDrawerListener(mDrawerToggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        mDrawerToggle.syncState()
    }

    private fun clearSelectionBottom(){
        for (i in 0 until bnMenu.menu.size()){
            val menuItem = bnMenu.menu.getItem(i)
            menuItem.isChecked = false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return super.onPrepareOptionsMenu(menu)
    }

    fun closeSession() {

    }

    fun setFragment(fragment: Fragment, backstack: Boolean) {
        if (backstack)
            supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment).addToBackStack(null).commit()
        else
            supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit()
    }


}