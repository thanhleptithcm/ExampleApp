package com.nexle.example_app.core.activity

import android.os.Bundle
import com.nexle.example_app.core.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity(
    contentLayoutId: Int
) : DaggerAppCompatActivity(contentLayoutId) {

    @Inject
    lateinit var mViewModelFactory: ViewModelProviderFactory

    abstract fun performDependencyInjection()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDependencyInjection()
    }
}