package com.lumi.surfeducationproject.common.base_view

import io.reactivex.rxjava3.disposables.CompositeDisposable

import moxy.MvpPresenter
import moxy.MvpView


abstract class BasePresenter<View : MvpView> : MvpPresenter<View>() {

    protected val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}