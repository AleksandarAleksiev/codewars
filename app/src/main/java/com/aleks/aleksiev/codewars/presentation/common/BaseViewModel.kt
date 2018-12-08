package com.aleks.aleksiev.codewars.presentation.common

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    fun add(disposable: Disposable) = compositeDisposable.add(disposable)
    fun dispose() {
        compositeDisposable.clear()
    }

    override fun onCleared() {
        super.onCleared()
        dispose()
    }
}