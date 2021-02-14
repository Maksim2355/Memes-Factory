package com.lumi.surfeducationproject.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.lumi.surfeducationproject.App
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.common.REQUEST_CODE_PERMISSION_CAMERA
import com.lumi.surfeducationproject.common.REQUEST_CODE_PERMISSION_GALLERY
import com.lumi.surfeducationproject.common.managers.*
import com.lumi.surfeducationproject.common.params.EXTRA_WAY_GET_IMG
import com.lumi.surfeducationproject.databinding.FragmentAddMemeBinding
import com.lumi.surfeducationproject.navigation.NavigationBackPressed
import com.lumi.surfeducationproject.ui.dialogs.AddImgDialog
import com.lumi.surfeducationproject.ui.extension.activity_extension.setColorStatusBar
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class AddMemeFragment : DaggerFragment(), View.OnClickListener {

    companion object {
        private const val REQUEST_DIALOG_WAY_GET_IMG = 100
        private const val REQUEST_CODE_CAMERA = 101
        private const val REQUEST_CODE_GALLERY = 102
    }

    private lateinit var binding: FragmentAddMemeBinding

    @Inject
    lateinit var bottomBarVisible: BottomBarVisible

    @Inject
    lateinit var navBack: NavigationBackPressed


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().setColorStatusBar(R.color.colorPrimaryContent)
        binding = FragmentAddMemeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initToolbar(view)
    }

    private fun initToolbar(view: View) {
        binding.addMemeToolbar.navigationIcon =
            context?.let { ContextCompat.getDrawable(it, R.drawable.ic_close) }
        (activity as AppCompatActivity).setSupportActionBar(binding.addMemeToolbar)
        binding.addMemeToolbar.setNavigationOnClickListener { navBack.back() }
    }

    private fun initView(view: View) {
        binding.createMemeBtn.setOnClickListener(this)
        binding.imgCloseIbtn.setOnClickListener(this)
        binding.addImgIbtn.setOnClickListener(this)
        input_title_meme_et.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun afterTextChanged(s: Editable?) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    presenter.updateTitle(input_title_meme_et.text.toString())
                }
            }
        )
        input_description_meme_et.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun afterTextChanged(s: Editable?) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    presenter.updateDescription(input_description_meme_et.text.toString())
                }
            }
        )
    }

    override fun onClick(v: View?) {
        v?.let {
            when (v.id) {
                R.id.img_close_ibtn -> presenter.deleteImg()
                R.id.create_meme_btn -> presenter.createMeme()
                R.id.add_img_ibtn -> showAddImgDialog()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        bottomBarVisible.hideBottomNavigationBar()
    }

    override fun onStop() {
        super.onStop()
        bottomBarVisible.showBottomNavigationBar()
    }

    private fun gemImgFromGallery() {
        if (permissionManager.requestPermissionGallery()) {
            val galleryIntent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(galleryIntent, REQUEST_CODE_GALLERY)
        }
    }

    private fun getImgFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_CODE_CAMERA)
    }

    override fun showImg(url: String) {
        img_meme_container.visibility = View.VISIBLE
        Glide.with(this).load(url).into(img_add_meme_iv)
    }

    override fun hideImg() {
        img_meme_container.visibility = View.GONE
    }

    override fun disableCreateMemeBtn() {
        create_meme_btn.setTextColor(resources.getColor(R.color.colorAccentTransparent))
        create_meme_btn.isClickable = false
    }

    override fun enableCreateMemeBtn() {
        create_meme_btn.setTextColor(resources.getColor(R.color.colorAccent))
        create_meme_btn.isClickable = true
    }

    override fun showAddImgDialog() {
        val addImgDialog = AddImgDialog()
        addImgDialog.setTargetFragment(this, REQUEST_DIALOG_WAY_GET_IMG)
        fragmentManager?.let {
            addImgDialog.show(it, EXTRA_WAY_GET_IMG)
        }
    }

    override fun clearFieldsAndImg() {
        input_title_meme_et.text = null
        input_description_meme_et.text = null
    }

    override fun showErrorSnackBar(messageError: String) {
        snackBarManager.showErrorMessage(messageError)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_DIALOG_WAY_GET_IMG -> {
                    //Получаем выбор нашего пользователя из диалога
                    val action = data?.getIntExtra(EXTRA_WAY_GET_IMG, 3)
                    if (action == AddImgDialog.CAMERA_MESSAGE) {
                        getImgFromCamera()
                    } else {
                        gemImgFromGallery()
                    }
                }
                REQUEST_CODE_CAMERA -> {
                    val imgBtmp = data?.extras?.get("data") as Bitmap
                    imgBtmp.let {
                        val url = fileManager.saveImg(it)
                        if (url != null) presenter.updateImg(url)
                    }
                }
                REQUEST_CODE_GALLERY -> {
                    data?.let {
                        presenter.updateImg(it.data.toString())
                    }
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_PERMISSION_CAMERA -> {
                if (grantResults.size > 2
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED
                ) {
                    getImgFromCamera()
                }
            }
            REQUEST_CODE_PERMISSION_GALLERY -> {
                if (grantResults.size > 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    gemImgFromGallery()
                }
            }
        }
    }

}
