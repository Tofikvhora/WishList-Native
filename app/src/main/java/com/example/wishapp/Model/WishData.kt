package com.example.taskmanager.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "wish-table")
data class WishData(
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0L,
    @ColumnInfo(name= "title-info")
    val title:String,
    @ColumnInfo(name= "description-info")
    val description:String,
    )




object DummyListData{
   val wishlist = listOf(
       WishData(title = "Hello im tofik vhora", description = "I'm a Software Engineer And Mobile app developer"),
       WishData(title = "Hello im tofik vhora", description = "I'm a Software Engineer And Mobile app developer"),
       WishData(title = "Hello im tofik vhora", description = "I'm a Software Engineer And Mobile app developer"),
       WishData(title = "Hello im tofik vhora", description = "I'm a Software Engineer And Mobile app developer"),
       WishData(title = "Hello im tofik vhora", description = "I'm a Software Engineer And Mobile app developer"),
   )
}