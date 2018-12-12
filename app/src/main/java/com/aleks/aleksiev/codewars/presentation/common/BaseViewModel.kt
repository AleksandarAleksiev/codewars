package com.aleks.aleksiev.codewars.presentation.common

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.aleks.aleksiev.codewars.presentation.RenderState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _renderState = MutableLiveData<RenderState>()
    val renderState: LiveData<RenderState> = _renderState

    fun add(disposable: Disposable) = compositeDisposable.add(disposable)
    fun dispose() {
        compositeDisposable.clear()
    }

    override fun onCleared() {
        super.onCleared()
        dispose()
    }

    protected fun renderState(renderState: RenderState) {
        _renderState.postValue(renderState)
    }
}