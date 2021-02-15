package com.lumi.surfeducationproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.databinding.FragmentSplashBinding
import com.lumi.surfeducationproject.di.injection_extension.injectViewModel
import com.lumi.surfeducationproject.di.named.ACTIVITY_NAVIGATION
import com.lumi.surfeducationproject.navigation.NavigationDestination
import com.lumi.surfeducationproject.vm.MemeDetailsViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import javax.inject.Named

class SplashFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var memeDetailsViewModel: MemeDetailsViewModel

    private lateinit var binding: FragmentSplashBinding

    @Inject
    @Named(ACTIVITY_NAVIGATION)
    lateinit var navigationDestination: NavigationDestination

    private var isAuthUser: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        memeDetailsViewModel = injectViewModel(viewModelFactory)
        val anim = AnimationUtils.loadAnimation(context, R.animator.pulse_logo_app)

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

}