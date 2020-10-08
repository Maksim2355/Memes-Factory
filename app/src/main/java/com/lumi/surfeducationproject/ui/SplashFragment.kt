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
import com.lumi.surfeducationproject.navigation.StartAppNav
import com.lumi.surfeducationproject.presenters.SplashPresenter
import com.lumi.surfeducationproject.views.SplashView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class SplashFragment : MvpAppCompatFragment(), SplashView {

    private lateinit var startApp: StartAppNav
    private lateinit var logoImgView: ImageView

    private val presenter by moxyPresenter { SplashPresenter() }


    @SuppressLint("ResourceType")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        startApp = context as StartAppNav
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

    override fun onResume() {
        super.onResume()
        presenter.startApp()
    }

    @SuppressLint("ResourceType")
    override fun startAnimation() {
        val anim = AnimationUtils.loadAnimation(context, R.animator.pulse_logo_app)
        logoImgView.startAnimation(anim)
    }

    override fun startApp() {
        Handler().postDelayed({
            startApp.startApp()
        }, 700)
    }
}