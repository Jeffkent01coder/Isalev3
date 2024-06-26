package com.jeff.isalev3.models

data class getSalesUIState(
      val errorMessage:String?,
      val statusMessage:String?,
      val statusResponse:String?,
      val observableData:GetSalesResponse?
)
data class getProfomaUIState(
      val errorMessage:String?,
      val statusMessage:String?,
      val statusResponse:String?,
      val observableData:getProfomaResponse?
)

data class getItemsUIState(
      val errorMessage:String?,
      val statusMessage:String?,
      val statusResponse:String?,
      val observableData: StockData?
)