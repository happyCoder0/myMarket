package com.happycoder.mymarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var searchView: SearchView
    private lateinit var mainFragment: ListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainFragment = supportFragmentManager.findFragmentById(R.id.mainFragment) as ListFragment
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)

        if (menu != null) {
            searchView = menu.findItem(R.id.actionSearch).actionView as SearchView
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                mainFragment.search(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                mainFragment.search(query)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}