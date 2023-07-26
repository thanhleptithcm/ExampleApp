package com.nexle.example_app.di.module

import com.nexle.example_app.di.shared.scope.PerApplication
import com.nexle.example_app.helper.PrefersHelper
import com.nexle.example_app.repository.CategoryRepository
import com.nexle.example_app.repository.UserRepository
import com.nexle.example_app.retrofit.AppApi
import dagger.Module
import dagger.Provides

@Module
class RepositoriesModule {

    @Provides
    @PerApplication
    fun provideUserRepository(
        api: AppApi,
        mPrefersHelper: PrefersHelper
    ): UserRepository {
        return UserRepository(api, mPrefersHelper)
    }

    @Provides
    @PerApplication
    fun provideCategoryRepository(
        api: AppApi,
        mPrefersHelper: PrefersHelper
    ): CategoryRepository {
        return CategoryRepository(api,mPrefersHelper )
    }
}