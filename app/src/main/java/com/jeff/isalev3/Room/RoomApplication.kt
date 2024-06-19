package com.stanbestgroup.isalev2.Room

import android.app.Application
import com.jeff.isalev3.Repositories.RoomRepository

class RoomApplication: Application() {
      private val database by lazy { RoomDB.getDatabase(this) }
      val repository by lazy { RoomRepository(database.getDao()) }
}