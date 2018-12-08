package com.aleks.aleksiev.codewars.presentation.common

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.aleks.aleksiev.codewars.R

abstract class BaseActivity : AppCompatActivity() {

    // region menu options
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    // endregion

    // region show screen
    fun showScreen(
        content: Fragment,
        contentTag: String,
        addToBackStack: Boolean,
        transitionContent: Boolean
    ) {

        val ft = supportFragmentManager.beginTransaction()

        // Content area slide animation
        if (transitionContent) {

            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        }

        ft.replace(R.id.content_placeholder, content, contentTag)

        if (addToBackStack) {
            ft.addToBackStack(contentTag + System.identityHashCode(content).toString())
        }

        ft.commitAllowingStateLoss()
    }
    // endregion
}