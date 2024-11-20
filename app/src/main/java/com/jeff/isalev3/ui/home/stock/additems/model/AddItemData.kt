package com.jeff.isalev3.ui.home.stock.additems.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddItemData(
    @SerialName("addInfo")
    val addInfo: String,
    @SerialName("bcd")
    val bcd: String,
    @SerialName("btchNo")
    val btchNo: String,
    @SerialName("currentStock")
    val currentStock: String,
    @SerialName("dftPrc")
    val dftPrc: Double,
    @SerialName("grpPrcL1")
    val grpPrcL1: Double,
    @SerialName("grpPrcL2")
    val grpPrcL2: Double,
    @SerialName("grpPrcL3")
    val grpPrcL3: Double,
    @SerialName("grpPrcL4")
    val grpPrcL4: Double,
    @SerialName("isrcAplcbYn")
    val isrcAplcbYn: String,
    @SerialName("itemCategoryId")
    val itemCategoryId: String,
    @SerialName("itemCd")
    val itemCd: String,
    @SerialName("itemCdDf")
    val itemCdDf: String,
    @SerialName("itemClsCd")
    val itemClsCd: String,
    @SerialName("itemNm")
    val itemNm: String,
    @SerialName("itemStdNm")
    val itemStdNm: String,
    @SerialName("itemTyCd")
    val itemTyCd: String,
    @SerialName("modrId")
    val modrId: String,
    @SerialName("modrNm")
    val modrNm: String,
    @SerialName("orgnNatCd")
    val orgnNatCd: String,
    @SerialName("pkgUnitCd")
    val pkgUnitCd: String,
    @SerialName("qtyUnitCd")
    val qtyUnitCd: String,
    @SerialName("regrId")
    val regrId: String,
    @SerialName("regrNm")
    val regrNm: String,
    @SerialName("taxTyCd")
    val taxTyCd: String,
    @SerialName("useYn")
    val useYn: String
)