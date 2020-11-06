package com.lumi.surfeducationproject

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.lumi.surfeducationproject.common.*
import com.lumi.surfeducationproject.domain.model.Meme
import com.lumi.surfeducationproject.navigation.*
import kotlinx.android.synthetic.main.activity_main.*


class AppActivity : AppCompatActivity(), NavigationStartApp, NavigationContent, StyleManager,
    NavigationAuth, SnackBarManager, PermissionManager {

    private val navController: NavController by lazy {
        findNavController(R.id.nav_host_fragment_container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.instance.startActivityComponent(this)
    }

    override fun startApp(isAuthUser: Boolean) {
        if (isAuthUser) {
            navController.navigate(R.id.action_splashFragment_to_tabFragment)
        } else {
            navController.navigate(R.id.action_splashFragment_to_authFragment)
        }
    }

    override fun startContentScreen() {
        navController.navigate(R.id.action_authFragment_to_tabFragment)
    }

    override fun setColorStatusBar(color: Int) {
        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(color)
    }

    override fun startAuthScreen() {
        navController.navigate(R.id.action_tabFragment_to_authFragment)
    }

    override fun showErrorMessage(message: String) {
        val snackbar = Snackbar.make(
            root_layout, message,
            Snackbar.LENGTH_LONG
        )
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(resources.getColor(R.color.colorError))
        val textView =
            snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.WHITE)
        textView.textSize = resources.getDimension(R.dimen.standard_text_size)
        snackbar.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        App.instance.clearActivityComponent()
    }

    override fun requestPermissionGallery(): Boolean {
        val permissionReadExternalStorage =
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        return if (permissionReadExternalStorage == PackageManager.PERMISSION_GRANTED) {
            true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ),
                REQUEST_CODE_PERMISSION_GALLERY
            )
            false
        }
    }

    override fun requestPermissionCamera(): Boolean {
        val permissionWriteExternalStorage =
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val permissionReadExternalStorage =
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        return if (permissionWriteExternalStorage != PackageManager.PERMISSION_GRANTED ||
            permissionReadExternalStorage != PackageManager.PERMISSION_GRANTED
        ) {
            if (permissionWriteExternalStorage != PackageManager.PERMISSION_GRANTED &&
                permissionReadExternalStorage != PackageManager.PERMISSION_GRANTED
            ){
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ),
                    REQUEST_CODE_PERMISSION_CAMERA
                )

            }else if (permissionWriteExternalStorage != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ),
                    REQUEST_CODE_PERMISSION_CAMERA
                )

            }else{
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ),
                    REQUEST_CODE_PERMISSION_CAMERA
                )

            }
            false
        } else true
    }

}