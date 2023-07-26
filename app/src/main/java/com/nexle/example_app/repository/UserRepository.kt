package com.nexle.example_app.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nexle.example_app.di.shared.scope.PerApplication
import com.nexle.example_app.helper.PrefersHelper
import com.nexle.example_app.repository.request.LoginRequest
import com.nexle.example_app.repository.request.SignupRequest
import com.nexle.example_app.repository.response.LoginResponse
import com.nexle.example_app.retrofit.AppApi
import javax.inject.Inject

@PerApplication
class UserRepository @Inject constructor(
    private val apiApp: AppApi,
    private val mPrefersHelper: PrefersHelper
) {

    private val loginLiveData = MutableLiveData<LoginResponse>()

    fun loginData(): LiveData<LoginResponse> {
        return loginLiveData
    }

    suspend fun signUp(email: String, pass: String) {
        val request = SignupRequest(email, pass, "Le", "Thanh")
        val result = apiApp.signup(request)
        if (result.isSuccessful && result.body() != null) {
            login(email, pass)
        }
    }

    suspend fun login(email: String, pass: String) {
        val request = LoginRequest(email, pass)
        val result = apiApp.login(request)
        if (result.isSuccessful && result.body() != null) {
            result.body().let {
                mPrefersHelper.setToken(it?.accessToken ?: "")
            }
            loginLiveData.postValue(result.body())
        }
    }
}