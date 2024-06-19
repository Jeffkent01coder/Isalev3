package com.stanbestgroup.isalev2.Room

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.DeleteTable
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Database(entities = [Entities.ItemEntity::class, Entities.Category::class], version = 1)
abstract class RoomDB :RoomDatabase(){
      abstract fun getDao():roomDAO

      companion object {
            @Volatile
            private var DATABASEINSTANCE:RoomDB?= null
            fun getDatabase(context: Context):RoomDB {
                  return DATABASEINSTANCE?: synchronized(this){
                        val databaseInstance = Room.databaseBuilder(context,RoomDB::class.java,"app_database").build()
                        DATABASEINSTANCE =databaseInstance
                        databaseInstance
                  }
            }
      }
}
@Dao
interface roomDAO {
      @Query("SELECT * FROM categories")
      fun getCategories():Flow<List<Entities.Category>>
      @Query("SELECT * FROM items")
      fun getItems():Flow<List<Entities.ItemEntity>>

      @Insert(onConflict =OnConflictStrategy.REPLACE)
      fun addCategory(category: Entities.Category)
      @Insert(onConflict = OnConflictStrategy.REPLACE)
      fun addItem(item:Entities.ItemEntity)
      @Query("DELETE FROM categories")
      fun deleteAllCategories()
      @Query("DELETE FROM items")
      fun deleteAllItems()


}
