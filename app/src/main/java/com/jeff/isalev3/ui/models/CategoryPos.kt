package com.jeff.isalev3.ui.models

data class CategoryPos(
    val id: Int,
    val category: String,
    val description: String?
){
    override fun toString(): String {
        return category
    }
}