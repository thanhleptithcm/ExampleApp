package com.nexle.example_app.di

import android.content.Context
import com.nexle.example_app.MyApplication
import com.nexle.example_app.di.module.ApplicationModule
import com.nexle.example_app.di.shared.scope.PerApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ActivitiesBuilderModule::class,
    ]
)
interface ApplicationComponent : AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MyApplication): Builder

        @BindsInstance
        fun context(applicationContext: Context): Builder

        fun build(): ApplicationComponent
    }
}