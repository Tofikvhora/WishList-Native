package com.example.taskmanager.Repo

import com.example.taskmanager.Model.WishDao
import com.example.taskmanager.Model.WishData
import kotlinx.coroutines.flow.Flow


class WishRepo(private val wishDao: WishDao) {


    suspend fun addWish(wish: WishData){
        wishDao.AddWish(wish)
    }
    fun getAllWish(): Flow<List<WishData>> = wishDao.getAllWishes();

    fun getWishById(id:Long) : Flow<WishData> {
       return  wishDao.getAWishById(id)
    }

    suspend fun updateWish(wish:WishData){
        wishDao.update(wish)
    }
    suspend fun deleteWish(wish: WishData){
        wishDao.delete(wish);
    }

}