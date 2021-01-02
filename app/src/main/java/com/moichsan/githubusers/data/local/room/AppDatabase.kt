package com.moichsan.githubusers.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.moichsan.githubusers.data.Items

@Database(entities = [(Items::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): FavDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context?): AppDatabase? {
            if (INSTANCE == null && context != null) {
                INSTANCE
                    ?: synchronized(this) {
                        INSTANCE
                            ?: buildDatabase(
                                context
                            ).also {
                                INSTANCE = it
                            }
                    }
            }
            return INSTANCE
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "Github.db"
            ).allowMainThreadQueries()
                .build()
    }
}