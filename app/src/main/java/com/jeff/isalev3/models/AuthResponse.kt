package com.jeff.isalev3.models

import kotlinx.serialization.Serializable

//A class that will hold the login state that updates the UI and cached data
data class AuthUIState(
    val errorMessage: String?,
    val successful: String?,
    val observableLoginData: LoginResponse?,
)

//Parameters passed on login
data class AuthParams(
    val pin: String,
    val username: String,
    val password: String
)

//Response received from the server containing account data
//To add proforma and credit
@Serializable
data class LoginResponse(
    val message: String?,
    val access_token: String?,
    val status: Boolean,
    val stats: Stats?,
    val business: Business?,
    val items: List<Item>?,
    val categories: List<Category>?,
    val codeList: List<CodeType>?,
    val sales: List<Sale>?,
    val customers: List<Customer>?,
    val classifications: List<Classification>?,
    val stockReasons: List<StockReason>?
)

@Serializable
data class Stats(
    val totalSales: Int,
    val numberOfSales: Int,
    val totalTax: Int
)

@Serializable
data class Business(
    val id: Int,
    val pin: String,
    val branch: String,
    val phone: String,
    val name: String,
    val email: String,
    val source: String,
    val sourceId: Any?, // Adjust type as per your requirements
    val taxprNm: String,
    val bsnsActv: String,
    val bhfId: String,
    val bhfNm: String,
    val bhfOpenDt: String,
    val prvncNm: String,
    val dstrtNm: String,
    val sctrNm: String,
    val locDesc: String?,
    val hqYn: Any?, // Adjust type as per your requirements
    val mgrNm: String,
    val mgrTelNo: String,
    val mgrEmail: String,
    val dvcId: String,
    val sdcId: String,
    val mrcNo: String,
    val cmcKey: String,
    val shuriId: String,
    val shuriHqBranchId: String,
    val shuriHqBranchAccount: String,
    val status: String,
    val createdAt: String,
    val updatedAt: String,
    val deletedAt: String?
)

@Serializable
data class Item(
    val id: Int,
    val itemCd: String,
    val itemCdDf: String,
    val itemClsCd: String,
    val itemTyCd: String,
    val itemNm: String,
    val itemStdNm: String,
    val picture: String?,
    val orgnNatCd: String,
    val pkgUnitCd: String,
    val qtyUnitCd: String,
    val taxTyCd: String,
    val btchNo: String,
    val regBhfId: String?,
    val bcd: String,
    val dftPrc: String,
    val grpPrcL1: String,
    val grpPrcL2: String,
    val grpPrcL3: String,
    val grpPrcL4: String,
    val grpPrcL5: String,
    val addInfo: String,
    val sftyQty: String,
    val isrcAplcbYn: String,
    val rraModYn: String?,
    val charges: Int?, // Adjust type as per your requirements
    val useYn: String,
    val currentStock: String,
    val status: String,
    val createdAt: String,
    val updatedAt: String,
    val deletedAt: String?,
    val itemCategoryId: Int,
    val userId: Int,
    val businessId: Int,
    val ItemCategory: Category
)

//Represents an individual sale and a list of the items sold
@Serializable
data class Sale(
    val id: Int,
    val print: String,
    val trdInvcNo: Int,
    val invcNo: Int,
    val orgInvcNo: Int,
    val custTin: String,
    val checksum: String?,
    val custNm: String,
    val salesTyCd: String,
    val rcptTyCd: String,
    val pmtTyCd: String,
    val salesSttsCd: String,
    val cfmDt: String,
    val salesDt: String,
    val stockRlsDt: String,
    val cnclReqDt: String?,
    val cnclDt: String?,
    val rfdDt: String?,
    val rfdRsnCd: String?,
    val totItemCnt: Int,
    val taxblAmtA: String,
    val taxblAmtB: String,
    val taxblAmtC: String,
    val taxblAmtD: String,
    val taxblAmtE: String,
    val taxRtA: String,
    val taxRtB: String,
    val taxRtC: String,
    val taxRtD: String,
    val taxRtE: String,
    val taxAmtA: String,
    val taxAmtB: String,
    val taxAmtC: String,
    val taxAmtD: String,
    val taxAmtE: String,
    val totTaxblAmt: String,
    val totTaxAmt: String,
    val totAmt: String,
    val prchrAcptcYn: String,
    val remark: String,
    val resultDt: String?,
    val curRcptNo: String?,
    val totRcptNo: String?,
    val intrlData: String?,
    val rcptSign: String?,
    val short_url: String?,
    val short_id: String?,
    val long_url: String?,
    val sdcDateTime: String?,
    val status: String,
    val createdAt: String,
    val updatedAt: String,
    val deletedAt: String?,
    val saleReceiptId: Int,
    val userId: Int,
    val businessId: Int,
    val items: List<Item>
)

@Serializable
data class Category(
    val id: Int,
    val category: String,
    val description: String?
)

//Represents a code in the codelist array
@Serializable
data class CodeType(
    val cdCls: String,
    val useYn: String,
    val cdClsNm: String,
    val dtlList: List<TaxationDetail>,
    val cdClsDesc: String?,
    val userDfnNm1: String,
    val userDfnNm2: String?,
    val userDfnNm3: String?
)

@Serializable
data class TaxationDetail(
    val cd: String,
    val cdNm: String,
    val useYn: String,
    val cdDesc: String,
    val srtOrd: Int,
    val userDfnCd1: String,
    val userDfnCd2: String,
    val userDfnCd3: String?
)

//This represents a classification in the classifications list
@Serializable
data class Classification(
    val useYn: String,
    val mjrTgYn: String,
    val taxTyCd: String,
    val itemClsCd: String,
    val itemClsNm: String,
    val itemClsLvl: Int
)

//This represents a customer in the customer list
data class Customer(
    val id: Int,
    val custNo: String,
    val custPin: String,
    val custNm: String,
    val adrs: String?,
    val telNo: String,
    val email: String,
    val faxNo: String?,
    val useYn: String,
    val remark: String,
    val status: String,
    val createdAt: String,
    val updatedAt: String,
    val deletedAt: String?,
    val userId: Int,
    val businessId: Int
)

//This represents a Stock reason in the list of stock reasons
@Serializable
data class StockReason(
    val id: Int,
    val type: String,
    val code: String,
    val reason: String,
    val description: String
)
