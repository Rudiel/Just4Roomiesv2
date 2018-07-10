package com.narumasolutions.just4roomies.Creators

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import com.narumasolutions.just4roomies.R

class LoadingDialog{

    fun showLoadingDialog(context : Context, message: String): Dialog{

        val dialog = Dialog(context)
        dialog.window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
        dialog.setContentView(R.layout.layout_loading)

        return dialog
    }

}