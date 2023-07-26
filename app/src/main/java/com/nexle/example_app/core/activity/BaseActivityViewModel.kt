package com.nexle.example_app.core.activity

import android.util.Log
import com.nexle.example_app.core.viewmodel.BaseViewModel

abstract class BaseActivityViewModel : BaseViewModel() {

    companion object {
        const val TAG = "BaseActivityViewModel"
    }

    override
    fun onCleared() {
        Log.d(TAG, "onCleared: $this")
        super.onCleared()
    }
}