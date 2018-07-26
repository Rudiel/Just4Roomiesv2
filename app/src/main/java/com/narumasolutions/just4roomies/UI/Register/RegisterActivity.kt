package com.narumasolutions.just4roomies.UI.Register

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.ClipData
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import android.util.Patterns
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.narumasolutions.just4roomies.Creators.AlertDialog
import com.narumasolutions.just4roomies.Creators.IOnChooserImageListener
import com.narumasolutions.just4roomies.Creators.LoadingDialog
import com.narumasolutions.just4roomies.Model.Request.RegisterNew
import com.narumasolutions.just4roomies.Model.Response.ErrorResponse
import com.narumasolutions.just4roomies.R
import com.narumasolutions.just4roomies.UI.RegisterPersonality.PersonalityActivity
import com.narumasolutions.just4roomies.Utils.REQUEST_CAMERA
import com.narumasolutions.just4roomies.Utils.REQUEST_GALERY
import com.narumasolutions.just4roomies.databinding.LayoutRegisterBinding
import kotlinx.android.synthetic.main.layout_register.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : AppCompatActivity(), IOnChooserImageListener {


    private lateinit var viewModel: RegisterViewModel
    private lateinit var loading: Dialog
    private lateinit var mCurrentPhotoPath: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val binding = DataBindingUtil.setContentView<LayoutRegisterBinding>(this, R.layout.layout_register)

        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)

        viewModel.response.observe(this, Observer { response ->
            if (response != null) {
                manageResponse(response)
            } else
               // showErrorMessage("Ocurrió un error, intente de nuevo")
                openRegisterActivity()
        })

        viewModel.pbVisibility.observe(this, Observer { response -> if (response == View.GONE) hideLoading() else showLoading() })

        binding.registerViewModel = viewModel

        btRegister.setOnClickListener({ validateFields() })

        ivRegisterPhoto.setOnClickListener({ selectImageOptions() })

        loading = LoadingDialog().showLoadingDialog(this, "Registrando tu usuario...")


    }

    private fun manageResponse(responseBody: Any) {
        if (responseBody is ErrorResponse) {
            //showErrorMessage(responseBody.message)
            openRegisterActivity()
        } else {
            openRegisterActivity()
        }
    }

    private fun selectImageOptions() {

        AlertDialog().showImageOptionsDialog(this, this).show()
    }

    private fun validateFields() {

        if (etName.text.toString().isEmpty())
            showErrorMessage("Nombre")
        else if (etLastName.text.toString().isEmpty())
            showErrorMessage("Apellido")
        else if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.text).matches())
            showErrorMessage("Email")
        else if (etPassword.text.toString().isEmpty())
            showErrorMessage("Password")
        else if (etRepitPassword.text.toString().isEmpty())
            showErrorMessage("RepitPassword")
        else if (ivRegisterPhoto.drawable.constantState == resources.getDrawable(android.R.drawable.ic_menu_camera).constantState) {
            showErrorMessage("Image Empty")
        } else {
            if (!etPassword.text.toString().equals(etRepitPassword.text.toString()))
                showErrorMessage("Son diferentes")
            else
                viewModel.doRegister(RegisterNew(
                        etName.text.toString(),
                        etLastName.text.toString(),
                        etEmail.text.toString(),
                        "test",
                        etPassword.text.toString()
                ))
        }
    }

    private fun openCamera() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA)
            }
        } else
            cameraIntent()

    }

    private fun openGallery() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_GALERY)
            }
        } else
            galleryIntent()
    }

    private fun galleryIntent() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select File"), REQUEST_GALERY)
    }

    private fun cameraIntent() {

        val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (takePicture.resolveActivity(packageManager) != null) {
            var file: File? = null

            try {
                file = createImageFile();
            } catch (e: IOException) {
                e.printStackTrace()
            }

            if (file != null) {
                val uri = FileProvider.getUriForFile(this@RegisterActivity, "com.example.android.fileprovider", file)

                takePicture.putExtra(MediaStore.EXTRA_OUTPUT, uri)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    takePicture.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

                    val clipData = ClipData.newUri(this.contentResolver, "A photo", uri)
                    takePicture.clipData = clipData
                    takePicture.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                } else {
                    val resinfoList = this.packageManager.queryIntentActivities(takePicture, PackageManager.MATCH_DEFAULT_ONLY)

                    for (resolveInfo: ResolveInfo in resinfoList) {
                        val packageName = resolveInfo.activityInfo.packageName
                        this.grantUriPermission(packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                    }
                }

                startActivityForResult(takePicture, REQUEST_CAMERA)

            }

        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CAMERA -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED))
                    cameraIntent()
            }
            REQUEST_GALERY -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED))
                    galleryIntent()
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {

        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "J4R_" + timeStamp + "_"
        val storageDire = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val i = File.createTempFile(imageFileName, ".jpg", storageDire)
        mCurrentPhotoPath = i.absolutePath
        return i

    }


    private fun showErrorMessage(message: String) {
        AlertDialog().showDialog(this, message, getString(R.string.register)).show()
    }

    private fun openRegisterActivity() {
        startActivity(Intent(this@RegisterActivity, PersonalityActivity::class.java))
        finish()
    }

    private fun hideLoading() {
        loading.dismiss()
    }

    private fun showLoading() {
        loading.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_GALERY -> {
                    onGalleryResult(data)
                }
                REQUEST_CAMERA -> {
                    onCaptureImageResult()
                }
            }
        }
    }

    fun onCaptureImageResult() {

        val bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath)

        val bOutput = ByteArrayOutputStream()

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bOutput)

        Glide.with(this).load(bitmap).apply(RequestOptions.circleCropTransform()).into(ivRegisterPhoto)

    }

    fun onGalleryResult(data: Intent?) {
        val bm: Bitmap?
        try {
            bm = MediaStore.Images.Media.getBitmap(this.applicationContext.contentResolver, data?.data)
            Glide.with(this).load(bm).apply(RequestOptions.circleCropTransform()).into(ivRegisterPhoto)
        } catch (e: IOException) {
            e.printStackTrace()
        }


    }

    override fun onCameraClicked(dialog: Dialog) {
        dialog.dismiss()
        openCamera()
    }

    override fun onGalleryClicked(dialog: Dialog) {
        dialog.dismiss()
        openGallery()
    }


}