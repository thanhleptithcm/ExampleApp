package com.nexle.example_app.di.module

import com.nexle.example_app.di.shared.scope.PerApplication
import com.nexle.example_app.helper.PrefersHelper
import com.nexle.example_app.retrofit.AppApi
import com.nexle.example_app.utils.AppConstant
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    @PerApplication
    fun provideRetrofit(mPrefersHelper: PrefersHelper): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor { chain ->
                        val ongoing = chain.request().newBuilder()
                        ongoing.addHeader("Cache-Control", "no-cache")
                        System.getProperty("http.agent")
                            ?.let { ongoing.addHeader("User-Agent", it) }
                        ongoing.addHeader("Content-Type", "application/json")
                        val token = mPrefersHelper.getToken()
                        if (token?.isNotEmpty() == true) {
                            ongoing.addHeader("Authorization", "Bearer $token")
                        }
                        chain.proceed(ongoing.build())
                    }
                    .build()
            )
            .build()
    }

    @Provides
    @PerApplication
    fun provideApi(retrofit: Retrofit): AppApi {
        return retrofit.create(AppApi::class.java)
    }
}
