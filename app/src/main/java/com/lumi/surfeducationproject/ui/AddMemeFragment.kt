package com.lumi.surfeducationproject.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lumi.surfeducationproject.App
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.common.SnackBarManager
import com.lumi.surfeducationproject.common.StyleManager
import moxy.MvpAppCompatFragment
import javax.inject.Inject


class AddMemeFragment : MvpAppCompatFragment() {


    @Inject
    lateinit var styleManager: StyleManager

    @Inject
    lateinit var snackBarManager: SnackBarManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.instance.startFragmentComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        styleManager.setColorStatusBar(R.color.colorPrimaryContent)
        return inflater.inflate(R.layout.fragment_add_meme, container, false)
    }



    override fun onDetach() {
        super.onDetach()
        App.instance.clearFragmentComponent()
    }

}