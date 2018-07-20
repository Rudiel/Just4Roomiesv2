package com.narumasolutions.just4roomies.Creators

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import com.narumasolutions.just4roomies.R
import android.widget.LinearLayout
import android.view.ViewGroup



class LoadingDialog{

    fun showLoadingDialog(context : Context, message: String): Dialog{

        val dialog = Dialog(context,android.R.style.Theme_NoTitleBar_Fullscreen)

        val params = dialog.getWindow().getAttributes()
        params.width = LinearLayout.LayoutParams.MATCH_PARENT
        params.height = LinearLayout.LayoutParams.MATCH_PARENT

        dialog.getWindow().setAttributes(params as android.view.WindowManager.LayoutParams)
        dialog.setContentView(R.layout.layout_loading)

        return dialog
    }

}