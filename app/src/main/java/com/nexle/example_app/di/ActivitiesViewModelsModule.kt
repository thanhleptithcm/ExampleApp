package com.nexle.example_app.di

import androidx.lifecycle.ViewModel
import com.nexle.example_app.di.shared.key.ViewModelKey
import com.nexle.example_app.screen.category.CategoriesViewModel
import com.nexle.example_app.screen.sign_up.SignUpViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ActivitiesViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    internal abstract fun provideSignUpViewModel(viewModel: SignUpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoriesViewModel::class)
    internal abstract fun provideCategoriesViewModel(viewModel: CategoriesViewModel): ViewModel
}