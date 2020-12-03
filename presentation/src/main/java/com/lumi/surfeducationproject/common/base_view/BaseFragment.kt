package com.lumi.surfeducationproject.common.base_view

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.ActionBar
import com.lumi.surfeducationproject.App
import com.lumi.surfeducationproject.R
import moxy.MvpAppCompatFragment


//Если ты, дорогой путник сюда добрался, то знай. этот фрагмент я создал, чтобы очищать потоки в презентере
abstract class BaseFragment: MvpAppCompatFragment()  {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    abstract fun getActionBar(): ActionBar?

}