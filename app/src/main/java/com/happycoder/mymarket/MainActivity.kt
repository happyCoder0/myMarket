package com.happycoder.mymarket

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE
import com.happycoder.mymarket.models.Order
import java.util.stream.Collectors

class MainActivity : AppCompatActivity() {

    lateinit var searchView: SearchView
    lateinit var mainFragment: Fragment
    lateinit var cartFragment: CartFragment
    lateinit var listFragment: ListFragment
    lateinit var cart: ArrayList<Order>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cartFragment = CartFragment()
        listFragment = ListFragment()
        cart = ArrayList()
        mainFragment = listFragment
        replaceFragment(R.id.mainFragment, listFragment)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        searchView = menu.findItem(R.id.actionSearch).actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                if (mainFragment is ListFragment) {
                    (mainFragment as ListFragment).search(newText)
                }
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (mainFragment is ListFragment) {
                    (mainFragment as ListFragment).search(query)
                }
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.cart -> {
                replaceFragment(R.id.mainFragment, cartFragment)
            }
            R.id.assortment -> {
                replaceFragment(R.id.mainFragment, listFragment)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun replaceFragment(old: Int, new: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setTransition(TRANSIT_FRAGMENT_FADE)
            .replace(old, new)
            .addToBackStack(null)
            .commit()
    }

    fun addToCart(elem: Order){
        val names = cart.stream().map { it.product.name }.collect(Collectors.toList())
        if (!names.contains(elem.product.name)) {
            cart.add(elem)
        } else {
            Toast.makeText(this, "You've already ordered that :)", Toast.LENGTH_LONG)
                .show()
        }
    }
}