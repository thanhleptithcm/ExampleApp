package com.nexle.example_app.core.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    companion object {
        const val TAG = ">>>BaseViewModel"
    }

    override fun onCleared() {
        Log.d(TAG, "onCleared: $this")
        super.onCleared()
    }

}