package com.happycoder.mymarket

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.happycoder.mymarket.adapters.ProductListAdapter
import com.happycoder.mymarket.models.Product

class ListFragment()
    : Fragment(R.layout.product_list_fragment), Searchable<String?>{

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductListAdapter
    private lateinit var products: ArrayList<Product>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        products = ArrayList<Product>().also {
            it.addAll(LoadTask().execute().get())
        }

        recyclerView = view.findViewById(R.id.productList)

        adapter = ProductListAdapter(requireContext(), products)

        val recyclerView: RecyclerView = view.findViewById(R.id.productList)
        recyclerView.adapter = adapter
    }

    override fun search(criteria: String?) {
        adapter.filter(criteria)
    }

}