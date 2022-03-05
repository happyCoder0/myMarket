package com.happycoder.mymarket.models

data class Order(val product: Product,
                 var quantity: Int,
                 val comment: String)