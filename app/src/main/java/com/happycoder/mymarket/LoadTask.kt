package com.happycoder.mymarket

import android.os.AsyncTask
import com.google.gson.Gson
import com.happycoder.mymarket.models.Product
import com.happycoder.mymarket.util.ApiHelper
import java.util.*
import kotlin.collections.ArrayList

class LoadTask : AsyncTask<Void, Void, Array<Product>>(){

    override fun doInBackground(vararg p0: Void): Array<Product> {
        val helper: ApiHelper = ApiHelper()

        val json: String = helper.loadProducts()

        return Gson().fromJson(json, Array<Product>::class.java)
    }

    override fun onPostExecute(result: Array<Product>) {
        super.onPostExecute(result)
    }
}