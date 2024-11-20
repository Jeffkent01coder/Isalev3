package com.jeff.isalev3.ui.models

data class ItemClassification(
    val itemClsCd: String,
    val itemClsNm: String,
    val itemClsLvl: Int,
    val taxTyCd: String?,
    val mjrTgYn: String?,
    val useYn: String
){
    override fun toString(): String {
        return "$itemClsNm"
    }
}
