package com.jeff.isalev3.models

import kotlinx.serialization.Serializable

@Serializable
data class getProfomaResponse(
      val status: Boolean,
      val message:String?,
      val profoma:List<ProfomaInvoice>?,
)
@Serializable
data class ProfomaInvoice(
      val id: Int,
      val PRINT: String,
      val trdInvcNo: Int,
      val invcNo: Int,
      val orgInvcNo: Int,
      val custTin: String,
      val custNm: String?,
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
      val sdcDateTime: String?,
      val short_url: String?,
      val short_id: String?,
      val long_url: String?,
      val status: String,
      val createdAt: String,
      val updatedAt: String,
      val deletedAt: String,
      val profomaReceiptId: Int?,
      val userId: Int,
      val businessId: Int,
      val items: List<ProfomaItem>
      )
@Serializable
data class ProfomaItem(
      val id: Int,
      val itemSeq: Int,
      val itemCd: String,
      val itemClsCd: String,
      val itemNm: String,
      val itemNmDef: String,
      val bcd: String?,
      val pkgUnitCd: String,
      val pkg: String,
      val qtyUnitCd: String,
      val qty: String,
      val prc: String,
      val splyAmt: String,
      val dcRt: String,
      val dcAmt: String,
      val taxTyCd: String,
      val taxblAmt: String,
      val taxAmt: String,
      val totAmt: String,
      val status: String,
      val charges: Any?,
      val createdAt: String,
      val updatedAt: String,
      val deletedAt: String?,
      val profomaId: Int,
      val itemId: Int
)