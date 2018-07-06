package com.narumasolutions.just4roomies.Creators

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import com.narumasolutions.just4roomies.R

class AlertDialog {

    fun showDialog(context: Context, message: String, title : String): Dialog {

        val alertDialog = AlertDialog.Builder(context, R.style.Theme_AppCompat_Light_Dialog_Alert)
        alertDialog.setMessage(message)
        alertDialog.setTitle(title)
        alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, _ -> dialog.dismiss() })

        return alertDialog.create()
    }

}