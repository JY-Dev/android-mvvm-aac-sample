package com.jydev.riiidsimapleapllication.ui.main.adapter

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import com.jydev.riiidsimapleapllication.util.dp


class PostItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        outRect.set(0,0,0,10.dp)
    }
}