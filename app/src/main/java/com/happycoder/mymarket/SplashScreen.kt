package com.happycoder.mymarket

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.happycoder.mymarket.models.Product
import com.happycoder.mymarket.util.ApiHelper

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        InitialLoadTask(this).execute()
    }

    private class InitialLoadTask(private val context: Context) :
        AsyncTask<Void, Void, ArrayList<Product>>() {

        override fun doInBackground(vararg p0: Void?): ArrayList<Product> {
            return arrayListOf(
                *Gson().fromJson(ApiHelper().loadProducts(), Array<Product>::class.java)
            )
        }

        override fun onPostExecute(result: ArrayList<Product>?) {
            super.onPostExecute(result)

            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
            (context as SplashScreen).finish()

        }

    }
}