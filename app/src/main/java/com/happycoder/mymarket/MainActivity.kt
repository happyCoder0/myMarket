package com.happycoder.mymarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.happycoder.mymarket.adapters.ProductAdapter
import com.happycoder.mymarket.models.Product
import kotlin.collections.ArrayList

//TODO: work out how to load json from api

class MainActivity : AppCompatActivity() {

    lateinit var searchView: SearchView
    lateinit var products: ArrayList<Product>
    //private val copy: ArrayList<Product> = ArrayList()
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        /*
        * new Product("Pen", new Random().nextInt(100), 2.99f));
           add(new Product("Carrot", new Random().nextInt(100), 15f));
           add(new Product("Fairy", new Random().nextInt(100), 45.99f));
           add(new Product("Broccoli", new Random().nextInt(100), 25f));
           add(new Product("Fantola", new Random().nextInt(100), 39.99f)*/

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        products = ArrayList<Product>().also{
            it.addAll( LoadTask().execute().get())
        }

        recyclerView = findViewById(R.id.productList)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val loadTask: LoadTask = LoadTask()

        adapter = ProductAdapter(this, products)

        val recyclerView: RecyclerView = findViewById(R.id.productList)
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)

        if (menu != null) {
            searchView = menu.findItem(R.id.actionSearch).actionView as SearchView
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("newText", newText.orEmpty())
                adapter.filter(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter(query)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}