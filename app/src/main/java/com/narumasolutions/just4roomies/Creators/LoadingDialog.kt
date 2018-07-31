package com.narumasolutions.just4roomies.Creators

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.narumasolutions.just4roomies.R
import android.widget.LinearLayout
import android.widget.TextView


class LoadingDialog {

    fun showLoadingDialog(context: Context, message: String): Dialog {

        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.layout_loading)

        val window = dialog.window

        window.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        window.setBackgroundDrawableResource(R.color.naranja_degradado_transparente_dialog)

        val params = window.attributes
        params.width = LinearLayout.LayoutParams.MATCH_PARENT
        params.height = LinearLayout.LayoutParams.MATCH_PARENT

        dialog.window.attributes = params as android.view.WindowManager.LayoutParams

        if (!message.isEmpty())
            dialog.findViewById<TextView>(R.id.tvLoadingMessage).text = message

        return dialog
    }

    fun showLoadingDialogTrans(context: Context, meesage: String): Dialog {


        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.layout_loading_transparent)

        val window = dialog.window

        window.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        window.setBackgroundDrawableResource(R.color.transparent)

        val params = window.attributes
        params.width = LinearLayout.LayoutParams.MATCH_PARENT
        params.height = LinearLayout.LayoutParams.MATCH_PARENT

        dialog.window.attributes = params as android.view.WindowManager.LayoutParams
        if (!meesage.isEmpty())
            dialog.findViewById<TextView>(R.id.tvLoadinMessageTran).text = meesage

        return dialog
    }

}