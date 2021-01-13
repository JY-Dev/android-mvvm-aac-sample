package com.jydev.riiidsimapleapllication.ui.detail

import android.os.Bundle
import com.jydev.riiidsimapleapllication.R
import com.jydev.riiidsimapleapllication.base.BaseActivity
import com.jydev.riiidsimapleapllication.databinding.ActivityDetailBinding
import com.jydev.riiidsimapleapllication.ui.detail.adapter.CommentItemDecoration
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailActivity : BaseActivity() {
    companion object {
        const val DEFAULT_VALUE = 1
    }

    val bind by binding<ActivityDetailBinding>(R.layout.activity_detail)
    val userId: Int by lazy { intent.getIntExtra(KEY, DEFAULT_VALUE) }
    val viewModel: DetailViewModel by viewModel { parametersOf(userId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind.run {
            lifecycleOwner = this@DetailActivity
            itemDecoration = CommentItemDecoration()
            detailViewModel = viewModel
            activity = this@DetailActivity
        }
    }
}