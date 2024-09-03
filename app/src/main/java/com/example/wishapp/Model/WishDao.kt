package com.example.taskmanager.Model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
abstract class WishDao {

    // add all data in table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun AddWish(wishEntity: WishData)

    // loads all wishes from the table
    @Query("Select * from `wish-table`")
    abstract  fun getAllWishes(): Flow<List<WishData>>

    @Update
    abstract suspend fun update(wishEntity: WishData)

    @Delete
    abstract suspend  fun delete(wishEntity: WishData)

    @Query("Select * from `wish-table` where id=:id")
    abstract  fun getAWishById(id:Long): Flow<WishData>


}