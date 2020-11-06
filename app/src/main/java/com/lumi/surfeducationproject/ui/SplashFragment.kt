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
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class SplashFragment : MvpAppCompatFragment(), SplashView {

    private lateinit var icLogoIv: ImageView

    @Inject
    lateinit var presenterProvider: Provider<SplashPresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }

    @Inject
    lateinit var navigationStartApp: NavigationStartApp

    private var isAuthUser: Boolean = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.instance.getFragmentAuthComponentOrCreateIfNull().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        icLogoIv = view.findViewById(R.id.logo_iv)
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
        this.isAuthUser = isAuthUser
        navigationStartApp.startApp(isAuthUser)
    }


    override fun onDetach() {
        super.onDetach()
        if (isAuthUser){
            App.instance.clearFragmentAuthComponent()
        }
    }

}