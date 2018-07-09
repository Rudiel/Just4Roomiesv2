package com.narumasolutions.just4roomies.Creators

import android.app.Dialog
import android.content.Context
import com.narumasolutions.just4roomies.R

class LoadingDialog{

    fun showLoadingDialog(context : Context, message: String): Dialog{

        val dialog = Dialog(context)
        dialog.setContentView(R.layout.layout_loading)

        return dialog
    }

}