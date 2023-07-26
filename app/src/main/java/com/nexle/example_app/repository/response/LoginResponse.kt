package com.nexle.example_app.repository.response

import com.nexle.example_app.model.UserModel

data class LoginResponse(
    val user: UserModel?,
    val accessToken: String,
    val refreshToken: String
)