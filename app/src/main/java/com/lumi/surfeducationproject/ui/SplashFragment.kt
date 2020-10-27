package com.lumi.surfeducationproject.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.navigation.NavigationStartApp
import com.lumi.surfeducationproject.presenters.SplashPresenter
import com.lumi.surfeducationproject.views.SplashView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class SplashFragment : MvpAppCompatFragment(), SplashView {

    private lateinit var navigationStartApp: NavigationStartApp
    private lateinit var logoImgView: ImageView

    private val presenter by moxyPresenter { SplashPresenter() }


    @SuppressLint("ResourceType")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigationStartApp = context as NavigationStartApp
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logoImgView = view.findViewById(R.id.logo)
    }

    override fun onStart() {
        super.onStart()
        presenter.startApp()
    }

    @SuppressLint("ResourceType")
    override fun startAnimation() {
        val anim = AnimationUtils.loadAnimation(context, R.animator.pulse_logo_app)
        logoImgView.startAnimation(anim)
    }

    override fun startApp() {
        Handler().postDelayed({
            navigationStartApp.startApp()
        }, 500)
    }
}