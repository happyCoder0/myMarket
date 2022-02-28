package com.happycoder.mymarket.util

import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class ApiHelper {

    companion object{
        const val url: String = "https://fathomless-sierra-70705.herokuapp.com"
    }

    fun loadProducts(): String{
        val u: URL = URL(url.plus("/products"))
        val request: HttpsURLConnection = u.openConnection() as HttpsURLConnection
        var json: String = ""
        with(request){
            readTimeout = 30000
            requestMethod = "GET"
            doInput = true
            doOutput = false
            connect()
            json = BufferedReader(InputStreamReader(inputStream)).lines().collect(Collectors.joining())
            inputStream.close()
            disconnect()
        }

        Log.d("json", json)

        return json
    }
}