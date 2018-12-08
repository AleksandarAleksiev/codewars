package com.aleks.aleksiev.codewars.presentation.common

import android.content.Context
import android.support.v4.app.Fragment
import com.aleks.aleksiev.codewars.presentation.viewmodel.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected val parentActivity by lazy {
        activity as? BaseActivity
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onResume() {
        super.onResume()

        displayHomeButton()
    }

    private fun displayHomeButton() {

        val enableUpButton = (parentActivity?.supportFragmentManager?.backStackEntryCount ?: 0) > 0
        parentActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(enableUpButton)
        parentActivity?.supportActionBar?.setHomeButtonEnabled(enableUpButton)
    }
}