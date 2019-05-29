package com.aleks.aleksiev.codewars.presentation.main

import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.aleks.aleksiev.codewars.R
import com.aleks.aleksiev.codewars.databinding.ActivityMainBinding
import com.aleks.aleksiev.codewars.presentation.common.BaseActivity
import com.aleks.aleksiev.codewars.presentation.common.navigator.AppNavigation
import com.aleks.aleksiev.codewars.presentation.common.navigator.Navigation
import com.aleks.aleksiev.codewars.presentation.viewmodel.ViewModelFactory
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val mainViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
    }

    var rootView: View? = null
        private set

    val navigator: Navigation by lazy {
        AppNavigation().apply {
            mainActivity = this@MainActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.viewModel = mainViewModel
        binding.taskInProgressLayout.setOnTouchListener { _, _ -> true }
        rootView = binding.root
        if (savedInstanceState == null) {
            navigator.search()
        }
    }

    override fun onBackPressed() {
        navigator.taskInProgress(false)
        super.onBackPressed()
    }

    fun taskInProgress(inProgress: Boolean) {
        mainViewModel.viewState.taskInProgress = inProgress
    }

    // region HasSupportFragmentInjector
    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector
    // endregion
}