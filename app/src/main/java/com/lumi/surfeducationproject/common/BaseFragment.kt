package com.lumi.surfeducationproject.common

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.ActionBar
import com.lumi.surfeducationproject.App
import com.lumi.surfeducationproject.R
import moxy.MvpAppCompatFragment

abstract class BaseFragment: MvpAppCompatFragment()  {

    //Интерфейс для презентера, который очищает все подписки при уничтожении фрагмента
    abstract fun disposeControl(): ControlDispose

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onDetach() {
        super.onDetach()
        disposeControl().disposeAll()
    }

    abstract fun getActionBar(): ActionBar?

}