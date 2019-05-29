package com.aleks.aleksiev.codewars.presentation.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aleks.aleksiev.codewars.presentation.RenderState
import com.aleks.aleksiev.codewars.utils.Event
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _renderState = MutableLiveData<Event<RenderState>>()
    val renderState: LiveData<Event<RenderState>> = _renderState

    fun add(disposable: Disposable) = compositeDisposable.add(disposable)
    fun dispose() {
        compositeDisposable.clear()
    }

    override fun onCleared() {
        super.onCleared()
        dispose()
    }

    fun renderState(renderState: Event<RenderState>) {
        _renderState.postValue(renderState)
    }
}