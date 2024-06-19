package com.jeff.isalev3.Repositories

import com.jeff.isalev3.models.Category
import com.jeff.isalev3.models.Item
import com.stanbestgroup.isalev2.Room.Entities
import com.stanbestgroup.isalev2.Room.roomDAO

class RoomRepository(private val dao: roomDAO) {

    val categoriesList = dao.getCategories()
    val itemsList = dao.getItems()

    suspend fun addNewItem(itemList: List<Item>?) {
        itemList?.let { items ->
            items.forEach { item ->
                val itemCategoryId = item.ItemCategory.id

                dao.addItem(
                    Entities.ItemEntity(
                        0,
                        item.id,
                        item.itemCd,
                        item.itemCdDf,
                        item.itemClsCd,
                        item.itemTyCd,
                        item.itemNm,
                        item.itemStdNm,
                        item.picture,
                        item.orgnNatCd,
                        item.pkgUnitCd,
                        item.qtyUnitCd,
                        item.taxTyCd,
                        item.btchNo,
                        item.regBhfId,
                        item.bcd,
                        item.dftPrc,
                        item.grpPrcL1,
                        item.grpPrcL2,
                        item.grpPrcL3,
                        item.grpPrcL4,
                        item.grpPrcL5,
                        item.addInfo,
                        item.sftyQty,
                        item.isrcAplcbYn,
                        item.rraModYn,
                        item.charges,
                        item.useYn,
                        item.currentStock,
                        item.status,
                        item.createdAt,
                        item.updatedAt,
                        item.deletedAt,
                        itemCategoryId,
                        item.userId,
                        item.businessId
                    )
                )
            }
        }
    }

    suspend fun addNewCategory(categoryList: List<Category>?) {
        categoryList?.let { categories ->
            categories.forEach { category ->
                dao.addCategory(
                    Entities.Category(0, category.category, category.id, category.description)
                )
            }
        }
    }

    suspend fun clearCategoriesTable() {
        dao.deleteAllCategories()
    }

    suspend fun clearItemsTable() {
        dao.deleteAllItems()
    }
}

