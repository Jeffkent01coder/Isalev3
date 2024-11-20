package com.jeff.isalev3.ui.models

data class Code(
    val cd: String,
    val cdNm: String,
    val cdDesc: String?,
    val useYn: String?,
    val srtOrd: Int?,
    val userDfnCd1: String?,
    val userDfnCd2: String?,
    val userDfnCd3: String?
){
    override fun toString(): String {
        return cdNm
    }
}
