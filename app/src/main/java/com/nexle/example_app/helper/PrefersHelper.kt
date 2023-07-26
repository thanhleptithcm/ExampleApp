package com.nexle.example_app.helper

import android.content.SharedPreferences
import com.nexle.example_app.di.shared.scope.PerApplication
import javax.inject.Inject

@PerApplication
class PrefersHelper @Inject constructor(
    private val sf: SharedPreferences
) {

    companion object {
        const val FILE_NAME = "app_name_prefers"
        const val TOKEN_KEY = "local.prefers.USER_TOKEN_KEY"
    }

    fun getToken(): String? {
        return sf.getString(TOKEN_KEY, null)
    }

    fun setToken(token: String?) {
        this.sf.edit().putString(TOKEN_KEY, token).apply()
    }
}