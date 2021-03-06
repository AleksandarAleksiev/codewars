package com.aleks.aleksiev.codewars.presentation.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.appcompat.app.AppCompatActivity
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

        ft.commit()
    }
    // endregion
}