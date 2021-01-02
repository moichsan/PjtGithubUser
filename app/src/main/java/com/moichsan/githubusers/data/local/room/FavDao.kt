package com.moichsan.githubusers.data.local.room

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moichsan.githubusers.data.Items

@Dao
interface FavDao {

    @Query("SELECT * FROM favorite_table WHERE id = :id")
    fun getLocalDataById(id: Int): Cursor

    @Query("SELECT * FROM favorite_table ORDER BY id_github DESC")
    fun getAllLocalData(): Cursor

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToLocalData(data: Items)

    @Query("DELETE FROM favorite_table WHERE id IN(:id)")
    fun deleteFromLocalData(id: Int) : Int
}