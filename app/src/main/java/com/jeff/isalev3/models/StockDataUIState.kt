package com.stanbestgroup.isalev2.Fragments.store.data

data class StockDataUIState(
    val errorMessage: String?,
    val statusMessage: String?,
    val statusResponse: String?,
    val observableData: StockData?
)
