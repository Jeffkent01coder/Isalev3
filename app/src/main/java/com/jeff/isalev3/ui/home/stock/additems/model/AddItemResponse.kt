package com.jeff.isalev3.ui.home.stock.additems.model

data class AddItemResponse(
    val success: Boolean,
    val message: String,
    val addItemData: AddItemData
)
data class AddItemUIState(
    val errorMessage: String?,
    val successMessage: String?,
    val addItemResponse: AddItemResponse?
)