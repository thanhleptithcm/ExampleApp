package com.nexle.example_app.model

data class CategoryModel(
    val id: Int,
    val name: String,
    var isSelected : Boolean = false
)