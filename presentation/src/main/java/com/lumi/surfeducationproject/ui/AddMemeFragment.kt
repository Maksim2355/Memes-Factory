package com.lumi.surfeducationproject.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.REQUEST_CODE_CAMERA
import com.lumi.surfeducationproject.REQUEST_CODE_GALLERY
import com.lumi.surfeducationproject.REQUEST_DIALOG_WAY_GET_IMG
import com.lumi.surfeducationproject.common.REQUEST_CODE_PERMISSION_CAMERA
import com.lumi.surfeducationproject.common.REQUEST_CODE_PERMISSION_GALLERY
import com.lumi.surfeducationproject.common.managers.BottomBarVisible
import com.lumi.surfeducationproject.common.params.EXTRA_WAY_GET_IMG
import com.lumi.surfeducationproject.databinding.FragmentAddMemeBinding
import com.lumi.surfeducationproject.di.injection_extension.injectViewModel
import com.lumi.surfeducationproject.navigation.NavigationBackPressed
import com.lumi.surfeducationproject.ui.dialogs.AddImgDialog
import com.lumi.surfeducationproject.ui.extension.activity_extension.setColorStatusBar
import com.lumi.surfeducationproject.ui.extension.fragment_extensions.setToolbar
import com.lumi.surfeducationproject.ui.extension.view_extension.onTextChanged
import com.lumi.surfeducationproject.utils.checkAppPermission
import com.lumi.surfeducationproject.utils.saveImg
import com.lumi.surfeducationproject.vm.AddMemeViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class AddMemeFragment : DaggerFragment() {

    private lateinit var binding: FragmentAddMemeBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var addMemeViewModel: AddMemeViewModel

    @Inject
    lateinit var bottomBarVisible: BottomBarVisible

    @Inject
    lateinit var navBack: NavigationBackPressed

    private var addImgDialog: AddImgDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().setColorStatusBar(R.color.colorPrimaryContent)
        binding = FragmentAddMemeBinding.inflate(inflater, container, false)
        addMemeViewModel = injectViewModel(viewModelFactory)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initToolbar()
        observeViews()
    }

    private fun initToolbar() {
        with(binding.addMemeToolbar) {
            navigationIcon = context?.let { ContextCompat.getDrawable(it, R.drawable.ic_close) }
            setToolbar(this)
            setNavigationOnClickListener { addMemeViewModel.navigateBack() }
        }
    }

    private fun initView() {
        with(binding) {
            createMemeBtn.setOnClickListener {
                addMemeViewModel.createMeme()
            }
            imgCloseIbtn.setOnClickListener {
                addMemeViewModel.removeImg()
            }
            addImgIbtn.setOnClickListener {
                addMemeViewModel.showAddImgDialog()
            }
            inputTitleMemeEt.onTextChanged { _, _, _, _ ->
                addMemeViewModel.updateTitleMeme(inputTitleMemeEt.text.toString())
            }
            inputDescriptionMemeEt.onTextChanged { _, _, _, _ ->
                addMemeViewModel.updateDescriptionMeme(inputDescriptionMemeEt.text.toString())
            }
        }
    }

    private fun observeViews() {
        with(addMemeViewModel) {
            imgUrl.observe(viewLifecycleOwner) {
                Glide.with(this@AddMemeFragment).load(it).into(binding.imgAddMemeIv)
            }
            isActiveCreateMemeButton.observe(viewLifecycleOwner) {
                if (it) {
                    binding.createMemeBtn.setTextColor(
                        getColor(
                            requireContext(),
                            R.color.colorAccent
                        )
                    )
                    binding.createMemeBtn.isClickable = true
                } else {
                    binding.createMemeBtn.setTextColor(
                        getColor(
                            requireContext(),
                            R.color.colorAccentTransparent
                        )
                    )
                    binding.createMemeBtn.isClickable = true
                }
            }
            titleMeme.observe(viewLifecycleOwner) {
                binding.inputTitleMemeEt.setText(it)
            }
            descriptionMeme.observe(viewLifecycleOwner) {
                binding.inputDescriptionMemeEt.setText(it)
            }
            showDownloadImgDialog.observe(viewLifecycleOwner) {
                if (it) {
                    addImgDialog = AddImgDialog().apply {
                        setTargetFragment(
                            this@AddMemeFragment,
                            REQUEST_DIALOG_WAY_GET_IMG
                        )
                        show(parentFragmentManager, EXTRA_WAY_GET_IMG)
                    }
                } else {
                    addImgDialog?.onDetach()
                }
            }
            navigateBackstack.observe(viewLifecycleOwner) {
                navBack.back()
            }
        }
    }

    private fun gemImgFromGallery() {
        if (requireActivity().checkAppPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        addMemeViewModel.hideDownloadImgDialog()
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
                        val url = saveImg(requireContext(), it)
                        if (url != null) {
                            addMemeViewModel.downloadImgUrl(url)
                        }
                    }
                }
                REQUEST_CODE_GALLERY -> {
                    data?.let {
                        addMemeViewModel.downloadImgUrl(it.data.toString())
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

    override fun onStart() {
        super.onStart()
        bottomBarVisible.hideBottomNavigationBar()
    }

    override fun onStop() {
        super.onStop()
        bottomBarVisible.showBottomNavigationBar()
    }

}
