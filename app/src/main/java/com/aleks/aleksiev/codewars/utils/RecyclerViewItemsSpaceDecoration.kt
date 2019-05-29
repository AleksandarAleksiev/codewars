package com.aleks.aleksiev.codewars.utils

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import android.view.View

class RecyclerViewItemsSpaceDecoration(private val mLeft: Int, private val mTop: Int, private val mRight: Int, private val mBottom: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.top = mTop
        outRect.left = mLeft
        outRect.right = mRight
        outRect.bottom = mBottom
    }
}