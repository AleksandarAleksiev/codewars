package com.aleks.aleksiev.codewars.presentation.main

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import com.aleks.aleksiev.codewars.R
import com.aleks.aleksiev.codewars.databinding.ActivityMainBinding
import com.aleks.aleksiev.codewars.presentation.common.BaseActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.viewModel = mainViewModel
        mainViewModel.search()
    }

    // region HasSupportFragmentInjector
    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector
    // endregion
}