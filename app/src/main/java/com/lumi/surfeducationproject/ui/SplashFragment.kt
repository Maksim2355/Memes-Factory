package com.lumi.surfeducationproject.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.navigation.StartAppNav

class SplashFragment : Fragment() {

    private lateinit var mStartApp: StartAppNav
    private lateinit var mAnimationChangeScale: Animation
    private lateinit var mLogoImgView: ImageView

    @SuppressLint("ResourceType")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mStartApp = context as StartAppNav
        mAnimationChangeScale = AnimationUtils.loadAnimation(context, R.animator.pulse_logo_app)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mLogoImgView = view.findViewById(R.id.logo)
    }

    override fun onResume() {
        super.onResume()
        mLogoImgView.startAnimation(mAnimationChangeScale)
        Handler().postDelayed({
            mStartApp.startApp()
        }, 700)
    }
}