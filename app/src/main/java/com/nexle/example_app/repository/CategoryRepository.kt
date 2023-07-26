package com.nexle.example_app.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nexle.example_app.di.shared.scope.PerApplication
import com.nexle.example_app.helper.PrefersHelper
import com.nexle.example_app.model.CategoryModel
import com.nexle.example_app.retrofit.AppApi
import javax.inject.Inject

@PerApplication
class CategoryRepository @Inject constructor(
    private val apiApp: AppApi,
    private val mPrefersHelper: PrefersHelper
) {

    private val category = MutableLiveData<List<CategoryModel>>()

    fun categoryData(): LiveData<List<CategoryModel>> {
        return category
    }

    suspend fun getCategories() {
        val result = apiApp.getCategories();

        if (result.isSuccessful && result.body() != null) {
            category.postValue(result.body())
        }
    }
}