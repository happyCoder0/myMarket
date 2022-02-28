package com.happycoder.mymarket.models

import java.io.Serializable

data class Product(val name: String, val quantity: Int, val price: Float) : Serializable
