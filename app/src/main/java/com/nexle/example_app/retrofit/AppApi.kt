package com.nexle.example_app.retrofit

import com.nexle.example_app.model.CategoryModel
import com.nexle.example_app.repository.request.LoginRequest
import com.nexle.example_app.repository.request.SignupRequest
import com.nexle.example_app.repository.response.LoginResponse
import com.nexle.example_app.repository.response.SignupResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AppApi {
    @POST("auth/signin")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("auth/signup")
    suspend fun signup(@Body request: SignupRequest): Response<SignupResponse>

    @GET("categories")
    suspend fun getCategories(): Response<List<CategoryModel>>
}