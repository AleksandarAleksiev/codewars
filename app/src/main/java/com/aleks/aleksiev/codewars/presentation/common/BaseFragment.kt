package com.aleks.aleksiev.codewars.presentation.common

import android.content.Context
import android.support.v4.app.Fragment
import com.aleks.aleksiev.codewars.R
import com.aleks.aleksiev.codewars.presentation.RenderState
import com.aleks.aleksiev.codewars.presentation.main.MainActivity
import com.aleks.aleksiev.codewars.presentation.viewmodel.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected val parentActivity by lazy {
        activity as? MainActivity
    }

    val navigator by lazy {
        parentActivity?.navigator
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onResume() {
        super.onResume()

        displayHomeButton()
    }

    protected fun renderState(renderState: RenderState?) {
        when (renderState) {
            is RenderState.LoadingState -> navigator?.taskInProgress(renderState.isLoading)
            is RenderState.MessageErrorState -> navigator?.showSnackBar(renderState.errorMessage, R.color.colorAccent, R.color.white)
            is RenderState.MessageSuccessState -> navigator?.showSnackBar(renderState.message, R.color.colorPrimary, R.color.white)
            else -> {}
        }
    }

    private fun displayHomeButton() {

        val enableUpButton = (parentActivity?.supportFragmentManager?.backStackEntryCount ?: 0) > 0
        parentActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(enableUpButton)
        parentActivity?.supportActionBar?.setHomeButtonEnabled(enableUpButton)
    }
}