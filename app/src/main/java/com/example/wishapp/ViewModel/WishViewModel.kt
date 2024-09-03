package com.example.taskmanager.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.Model.WishData
import com.example.taskmanager.Repo.WishRepo
import com.example.wishapp.Repo.Graph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel (private val repo:WishRepo = Graph.wishRepo) : ViewModel() {

    var wishTitleState by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")


   fun onWishTitleChange(newString:String){
       wishTitleState = newString
   }
    fun onWishDescriptionChange(newString:String){
        wishDescriptionState = newString

    }
    lateinit var getAllWish: Flow<List<WishData>>

    init {
        viewModelScope.launch {
            getAllWish = repo.getAllWish()
        }
    }

    fun addWishes(wish:WishData){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addWish(wish);
        }
    }
    fun updateWishes(wish:WishData){
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateWish(wish);
        }
    }
    fun deleteWishes(wish:WishData){
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteWish(wish);
        }
    }
    fun wishesById(id : Long):Flow<WishData>{
        return repo.getWishById(id)
    }

}