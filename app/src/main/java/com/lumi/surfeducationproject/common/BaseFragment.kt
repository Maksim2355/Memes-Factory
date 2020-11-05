package com.lumi.surfeducationproject.common

import androidx.appcompat.app.ActionBar
import com.lumi.surfeducationproject.App
import moxy.MvpAppCompatFragment

abstract class BaseFragment: MvpAppCompatFragment()  {

    //Интерфейс для презентера, который очищает все подписки при уничтожении фрагмента
    abstract fun disposeControl(): ControlDispose

    override fun onDetach() {
        super.onDetach()
        disposeControl().disposeAll()
        App.instance.clearFragmentComponent()
    }

    abstract fun getActionBar(): ActionBar?

}