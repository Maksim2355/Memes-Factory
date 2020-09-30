package com.lumi.surfeducationproject

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class SplashFragment : Fragment() {

    private lateinit var mStartApp: StartAppNav

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mStartApp = context as StartAppNav
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            mStartApp.startApp()
        }, 1500)
    }
}