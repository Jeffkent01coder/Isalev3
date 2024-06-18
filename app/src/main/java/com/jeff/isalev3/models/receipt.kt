package com.jeff.isalev3.models

data class receipt(
    val id: Int,
    val custTin: String,
    val custMblNo: String?,
    val custEmail: String,
    val rptNo: Int,
    val rcptPbctDt: String,
    val trdeNm: String,
    val adrs: String,
    val topMsg: String,
    val btmMsg: String,
    val prchrAcptcYn: String,
    val status: String,
    val createdAt: String,
    val updatedAt: String,
    val deletedAt: String?,
    val customerId: Int
)
