package com.github.astat1cc.sergeybalakintesttask.featuremainscreen.data.local.models.cart

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.astat1cc.sergeybalakintesttask.featuremainscreen.data.local.models.cart.CartMainScreenLocalDto.Companion.TABLE_NAME
import com.github.astat1cc.sergeybalakintesttask.featuremainscreen.domain.entities.cart.Cart

@Entity(tableName = TABLE_NAME)
data class CartMainScreenLocalDto(
    val itemsCount: Int
) {

    @PrimaryKey(autoGenerate = true) var id: Int? = null

    fun mapToDomain() = Cart(itemsCount)

    companion object {

        fun fromDomain(cart: Cart) = CartMainScreenLocalDto(cart.itemsCount)

        const val TABLE_NAME = "cart_items_count_table"
    }
}