package com.happycoder.mymarket.models

data class Order(val product: Product,
                 val quantity: Int,
                 val comment: String)