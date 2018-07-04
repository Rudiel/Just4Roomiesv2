package com.narumasolutions.just4roomies.UI.RegisterPersonality

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.narumasolutions.just4roomies.R
import com.narumasolutions.just4roomies.databinding.LayoutPersonalityBinding

class PersonalityActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<LayoutPersonalityBinding>(this, R.layout.layout_personality)

        val viewModel = ViewModelProviders.of(this).get(PersonalityViewModel::class.java)


    }

    private fun showErrorMessage(){

    }

    private fun opneMainActivity(){

    }

}