package com.jydev.riiidsimapleapllication.ui.detail.adapter

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import com.jydev.riiidsimapleapllication.util.dp


class CommentItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        outRect.set(0,0,0,10.dp)
    }
}