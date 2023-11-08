package com.example.train_food_delivery.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [cartEntity::class],version = 2)
abstract class cartDatabase: RoomDatabase() {
    abstract fun cartDao(): cartDao

    companion object {
        @Volatile
        private var instance: cartDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, cartDatabase::class.java, "GroceryDatabase.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}