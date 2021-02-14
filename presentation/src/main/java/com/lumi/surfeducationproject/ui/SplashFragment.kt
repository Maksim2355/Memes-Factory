package com.lumi.surfeducationproject.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.lumi.surfeducationproject.App
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.navigation.NavigationStartApp
import com.lumi.surfeducationproject.presenters.SplashPresenter
import com.lumi.surfeducationproject.views.SplashView
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_splash.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class SplashFragment : DaggerFragment() {


    @Inject
    lateinit var navigationStartApp: NavigationStartApp

    private var isAuthUser: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    @SuppressLint("ResourceType")
    override fun startAnimation() {
        val anim = AnimationUtils.loadAnimation(context, R.animator.pulse_logo_app)
    }

    override fun startApp(isAuthUser: Boolean) {
        this.isAuthUser = isAuthUser
        navigationStartApp.startApp(isAuthUser)
    }
}