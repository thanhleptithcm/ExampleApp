package com.nexle.example_app.di.module

import android.content.Context
import android.content.SharedPreferences
import com.nexle.example_app.di.shared.scope.PerApplication
import com.nexle.example_app.helper.PrefersHelper
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
object PrefersModule {

    @Provides
    @PerApplication
    @Named("prefers_name")
    fun providePrefersName(): String {
        return PrefersHelper.FILE_NAME
    }

    @Provides
    @PerApplication
    fun provideSharedPreferences(
        context: Context,
        @Named("prefers_name") name: String
    ): SharedPreferences {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }
}