package com.narumasolutions.just4roomies.Creators

import android.app.Dialog

interface IOnChooserImageListener {

    fun onCameraClicked(dialog: Dialog)

    fun onGalleryClicked(dialog: Dialog)
}