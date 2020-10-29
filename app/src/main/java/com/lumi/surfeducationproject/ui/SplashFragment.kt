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
import com.lumi.surfeducationproject.App
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.navigation.NavigationStartApp
import com.lumi.surfeducationproject.presenters.SplashPresenter
import com.lumi.surfeducationproject.views.SplashView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class SplashFragment : MvpAppCompatFragment(), SplashView {


    @Inject
    lateinit var presenterProvider: Provider<SplashPresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    @Inject
    lateinit var navigationStartApp: NavigationStartApp

    private lateinit var icLogoIv: ImageView

    @SuppressLint("ResourceType")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.instance.startFragmentComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        icLogoIv = view.findViewById(R.id.logo)
    }

    override fun onStart() {
        super.onStart()
        presenter.startApp()
    }

    @SuppressLint("ResourceType")
    override fun startAnimation() {
        val anim = AnimationUtils.loadAnimation(context, R.animator.pulse_logo_app)
        icLogoIv.startAnimation(anim)
    }

    override fun startApp(isAuthUser: Boolean) {
        Handler().postDelayed({
            navigationStartApp.startApp(isAuthUser)
        }, 500)
    }


    override fun onDetach() {
        super.onDetach()
        App.instance.clearFragmentComponent()
    }

}