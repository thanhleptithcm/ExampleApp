package com.nexle.example_app.screen.sign_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.nexle.example_app.core.activity.BaseActivityViewModel
import com.nexle.example_app.repository.UserRepository
import com.nexle.example_app.repository.response.LoginResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    val repository: UserRepository
) : BaseActivityViewModel() {

    val loginLiveData: LiveData<LoginResponse> get() = repository.loginData()

    fun requestSignUp(email: String, pass: String) {
        viewModelScope.launch {
            repository.signUp(email, pass)
        }
    }
}