package com.interview.task

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CartDao {
    @Query("SELECT * FROM CART ")
    fun getAllCart(): List<Cart>

    @Insert
    fun insertCart(cart: Cart)

    @Delete
    fun delete(cart: Cart)
}