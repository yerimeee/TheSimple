package com.interview.task

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CART")
data class Cart(
    @PrimaryKey(autoGenerate = true) val cart_no :Int,
    @ColumnInfo(name = "order_no") val order_no : Int,
    @ColumnInfo(name = "menu_name") val menu_name: String,
    @ColumnInfo(name = "total_price") val total_price: Int,
    @ColumnInfo(name = "count") val count: Int
)
