package com.narumasolutions.just4roomies.Creators

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.widget.Button
import com.narumasolutions.just4roomies.R

class AlertDialog {

    fun showDialog(context: Context, message: String, title: String): Dialog {

        val alertDialog = AlertDialog.Builder(context, R.style.Theme_AppCompat_Light_Dialog_Alert)
        alertDialog.setMessage(message)
        alertDialog.setTitle(title)
        alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, _ -> dialog.dismiss() })

        return alertDialog.create()
    }

    fun showImageOptionsDialog(context: Context, listener: IOnChooserImageListener): Dialog {

        val inflater = LayoutInflater.from(context)
        val v = inflater.inflate(R.layout.layout_selector_image, null, false)

        val btGallery = v.findViewById<Button>(R.id.btImageSelectorGallery)

        val btCamera = v.findViewById<Button>(R.id.btImageSelectorCamera)


        val alertDialog = AlertDialog.Builder(context, R.style.Theme_AppCompat_Light_Dialog_Alert)
        alertDialog.setView(v)
        alertDialog.setTitle(context.getString(R.string.crearusuario_alerttitle))
        alertDialog.setMessage(context.getString(R.string.crearusuario_selectimagefrom))

        val dialog = alertDialog.create()

        btCamera.setOnClickListener { listener.onCameraClicked(dialog) }

        btGallery.setOnClickListener { listener.onGalleryClicked(dialog) }

        return dialog

    }

}