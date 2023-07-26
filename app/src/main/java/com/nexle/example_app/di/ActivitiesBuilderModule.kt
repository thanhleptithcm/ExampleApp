package com.nexle.example_app.di

import com.nexle.example_app.di.shared.scope.PerActivity
import com.nexle.example_app.screen.category.CategoriesActivity
import com.nexle.example_app.screen.sign_up.SignUpActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesBuilderModule {

    @PerActivity
    @ContributesAndroidInjector(
        modules = [
            ActivitiesViewModelsModule::class
        ]
    )
    internal abstract fun contributeSignUpActivity(): SignUpActivity

    @PerActivity
    @ContributesAndroidInjector(
        modules = [
            ActivitiesViewModelsModule::class
        ]
    )
    internal abstract fun contributeCategoriesActivity(): CategoriesActivity
}