package com.happycoder.mymarket.util

import com.happycoder.mymarket.models.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface Api {
    @GET("./products")
    suspend fun getListOfProducts(): ArrayList<Product>
}

object ApiHelper {

    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .callTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://fathomless-sierra-70705.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private val api = retrofit.create(Api::class.java)

    suspend fun getListOfProducts(): ArrayList<Product> {
        return withContext(Dispatchers.IO) {
            api.getListOfProducts()
        }
    }
}