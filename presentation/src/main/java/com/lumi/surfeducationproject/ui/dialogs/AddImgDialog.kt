package com.lumi.surfeducationproject.ui.dialogs

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.common.ActionsIntentImg
import com.lumi.surfeducationproject.common.params.EXTRA_WAY_GET_IMG
import com.lumi.surfeducationproject.databinding.DialogAddImgBinding


class AddImgDialog: DialogFragment() {

    companion object{
        const val CAMERA_MESSAGE = 0
        const val GALLERY_MESSAGE = 1
    }

    lateinit var binding: DialogAddImgBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogAddImgBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addImgForCameraBtn.setOnClickListener {
            sendMessageParentFragmentAndCloseDialog(ActionsIntentImg.CAMERA)
        }
        binding.addImgForGalleryBtn.setOnClickListener {
            sendMessageParentFragmentAndCloseDialog(ActionsIntentImg.GALLERY)
        }
    }

    private fun sendMessageParentFragmentAndCloseDialog(action: ActionsIntentImg){
        val intent = Intent()
        if (action == ActionsIntentImg.CAMERA) {
            intent.putExtra(EXTRA_WAY_GET_IMG, CAMERA_MESSAGE)
        }else{
            intent.putExtra(EXTRA_WAY_GET_IMG, GALLERY_MESSAGE)
        }
        targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
        dismiss()
    }

}