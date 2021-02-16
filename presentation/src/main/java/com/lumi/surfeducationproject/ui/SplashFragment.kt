package com.lumi.surfeducationproject.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
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
import com.lumi.surfeducationproject.vm.SplashViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import javax.inject.Named

class SplashFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var splashViewModel: SplashViewModel

    private lateinit var binding: FragmentSplashBinding

    @Inject
    @Named(ACTIVITY_NAVIGATION)
    lateinit var navigationDestination: NavigationDestination


    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        splashViewModel = injectViewModel(viewModelFactory)
        val anim = AnimationUtils.loadAnimation(context, R.animator.pulse_logo_app)
        binding.logoIv.startAnimation(anim)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        splashViewModel.navigateTo.observe(viewLifecycleOwner) {
            Handler().postDelayed({
                it.getContentIfNotHandled()?.let { route ->
                    navigationDestination.navigateTo(route)
                }
            }, 1500)

        }
    }

}