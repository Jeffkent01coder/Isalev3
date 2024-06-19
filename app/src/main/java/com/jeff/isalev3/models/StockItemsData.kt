package com.stanbestgroup.isalev2.Fragments.store.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StockData(
    @SerialName("items")
    val items: List<Item>,
    @SerialName("message")
    val message: String,
    @SerialName("status")
    val status: Boolean
)

@Serializable
data class Item(
    @SerialName("addInfo")
    val addInfo: String,
    @SerialName("bcd")
    val bcd: String,
    @SerialName("btchNo")
    val btchNo: String,
    @SerialName("businessId")
    val businessId: Int,
    @SerialName("charges")
    val charges: Any,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("currentStock")
    val currentStock: String,
    @SerialName("deletedAt")
    val deletedAt: Any,
    @SerialName("dftPrc")
    val dftPrc: String,
    @SerialName("grpPrcL1")
    val grpPrcL1: String,
    @SerialName("grpPrcL2")
    val grpPrcL2: String,
    @SerialName("grpPrcL3")
    val grpPrcL3: String,
    @SerialName("grpPrcL4")
    val grpPrcL4: String,
    @SerialName("grpPrcL5")
    val grpPrcL5: String,
    @SerialName("id")
    val id: Int,
    @SerialName("isrcAplcbYn")
    val isrcAplcbYn: Any,
    @SerialName("ItemCategory")
    val itemCategory: ItemCategory,
    @SerialName("itemCategoryId")
    val itemCategoryId: Int,
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
    @SerialName("orgnNatCd")
    val orgnNatCd: String,
    @SerialName("picture")
    val picture: Any,
    @SerialName("pkgUnitCd")
    val pkgUnitCd: String,
    @SerialName("qtyUnitCd")
    val qtyUnitCd: String,
    @SerialName("regBhfId")
    val regBhfId: Any,
    @SerialName("rraModYn")
    val rraModYn: Any,
    @SerialName("sftyQty")
    val sftyQty: String,
    @SerialName("status")
    val status: String,
    @SerialName("taxTyCd")
    val taxTyCd: String,
    @SerialName("updatedAt")
    val updatedAt: String,
    @SerialName("useYn")
    val useYn: String,
    @SerialName("userId")
    val userId: Int
)

@Serializable
data class ItemCategory(
    @SerialName("category")
    val category: String,
    @SerialName("description")
    val description: Any,
    @SerialName("id")
    val id: Int
)