package com.lumi.surfeducationproject.ui.dialogs

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.common.ActionsIntentImg
import com.lumi.surfeducationproject.common.EXTRA_WAY_GET_IMG
import kotlinx.android.synthetic.main.dialog_add_img.view.*


class AddImgDialog: DialogFragment() {

    private lateinit var addImgForGalleryBtn: Button
    private lateinit var addImgForCameraBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_add_img, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addImgForCameraBtn = view.add_img_for_camera_btn
        addImgForCameraBtn.setOnClickListener {
            sendMessageParentFragmentAndCloseDialog(ActionsIntentImg.CAMERA)

        }
        addImgForGalleryBtn = view.add_img_for_gallery_btn
        addImgForGalleryBtn.setOnClickListener {
            sendMessageParentFragmentAndCloseDialog(ActionsIntentImg.GALLERY)
        }
    }

    private fun sendMessageParentFragmentAndCloseDialog(action: ActionsIntentImg){
        val intent = Intent()
        intent.putExtra(EXTRA_WAY_GET_IMG, action)
        targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
        dismiss()
    }

}