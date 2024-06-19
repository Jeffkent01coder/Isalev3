package com.stanbestgroup.isalev2.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

class Entities {
      @Entity(tableName = "categories")
      data class Category(
            @PrimaryKey(autoGenerate = true) val id: Int,
            @ColumnInfo(name = "category") val category: String,
            @ColumnInfo(name = "category_id") val categoryId: Int,
            @ColumnInfo(name = "description") val description: String?
      )
      @Entity(tableName = "Items")
      data class ItemEntity(
            @PrimaryKey(autoGenerate = true) val id: Int,
            @ColumnInfo(name = "item_id") val itemId: Int,
            @ColumnInfo(name = "item_cd") val itemCd: String,
            @ColumnInfo(name = "item_cd_df") val itemCdDf: String,
            @ColumnInfo(name = "item_cls_cd") val itemClsCd: String,
            @ColumnInfo(name = "item_ty_cd") val itemTyCd: String,
            @ColumnInfo(name = "item_nm") val itemNm: String,
            @ColumnInfo(name = "item_std_nm") val itemStdNm: String,
            @ColumnInfo(name = "picture") val picture: String?,
            @ColumnInfo(name = "orgn_nat_cd") val orgnNatCd: String,
            @ColumnInfo(name = "pkg_unit_cd") val pkgUnitCd: String,
            @ColumnInfo(name = "qty_unit_cd") val qtyUnitCd: String,
            @ColumnInfo(name = "tax_ty_cd") val taxTyCd: String,
            @ColumnInfo(name = "btch_no") val btchNo: String,
            @ColumnInfo(name = "reg_bhf_id") val regBhfId: String?,
            @ColumnInfo(name = "bcd") val bcd: String,
            @ColumnInfo(name = "dft_prc") val dftPrc: String,
            @ColumnInfo(name = "grp_prc_l1") val grpPrcL1: String,
            @ColumnInfo(name = "grp_prc_l2") val grpPrcL2: String,
            @ColumnInfo(name = "grp_prc_l3") val grpPrcL3: String,
            @ColumnInfo(name = "grp_prc_l4") val grpPrcL4: String,
            @ColumnInfo(name = "grp_prc_l5") val grpPrcL5: String,
            @ColumnInfo(name = "add_info") val addInfo: String,
            @ColumnInfo(name = "sfty_qty") val sftyQty: String,
            @ColumnInfo(name = "isrc_aplcb_yn") val isrcAplcbYn: String,
            @ColumnInfo(name = "rra_mod_yn") val rraModYn: String?,
            @ColumnInfo(name = "charges") val charges: Int?,
            @ColumnInfo(name = "use_yn") val useYn: String,
            @ColumnInfo(name = "current_stock") val currentStock: String,
            @ColumnInfo(name = "status") val status: String,
            @ColumnInfo(name = "created_at") val createdAt: String,
            @ColumnInfo(name = "updated_at") val updatedAt: String,
            @ColumnInfo(name = "deleted_at") val deletedAt: String?,
            @ColumnInfo(name = "item_category_id") val itemCategoryId: Int,
            @ColumnInfo(name = "user_id") val userId: Int,
            @ColumnInfo(name = "business_id") val businessId: Int,
            )


}