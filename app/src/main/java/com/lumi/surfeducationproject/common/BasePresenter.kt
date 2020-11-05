package com.lumi.surfeducationproject.common

import io.reactivex.rxjava3.disposables.CompositeDisposable

import moxy.MvpPresenter
import moxy.MvpView


abstract class BasePresenter<View : MvpView> : MvpPresenter<View>(), ControlDispose {

    protected val compositeDisposable = CompositeDisposable()

    override fun disposeAll() {
        compositeDisposable.dispose()
    }
}