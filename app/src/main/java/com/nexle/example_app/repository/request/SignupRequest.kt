package com.nexle.example_app.repository.request

data class SignupRequest(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String
)