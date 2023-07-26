package com.nexle.example_app.di.module

import android.app.Application
import com.nexle.example_app.MyApplication
import com.nexle.example_app.R
import com.nexle.example_app.di.shared.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(
    includes = [
        PrefersModule::class,
        NetworkModule::class,
        RepositoriesModule::class,
    ]
)
object ApplicationModule {

    @Provides
    @PerApplication
    fun provideApplication(application: Application): Application {
        return application
    }

    @Provides
    @PerApplication
    fun provideBaseApplication(application: MyApplication): MyApplication {
        return application
    }

    @Provides
    @PerApplication
    @Named("app_name")
    fun provideAppName(application: MyApplication): String {
        return application.getString(R.string.app_name)
    }
}