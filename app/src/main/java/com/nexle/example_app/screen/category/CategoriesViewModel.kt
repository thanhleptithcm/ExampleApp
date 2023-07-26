package com.nexle.example_app.screen.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.nexle.example_app.core.activity.BaseActivityViewModel
import com.nexle.example_app.model.CategoryModel
import com.nexle.example_app.repository.CategoryRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(
    val repository: CategoryRepository
) : BaseActivityViewModel() {

    val categoryLiveData: LiveData<List<CategoryModel>> get() = repository.categoryData()

    init {
        viewModelScope.launch {
            repository.getCategories()
        }
    }
}