package com.happycoder.mymarket

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.happycoder.mymarket.adapters.ProductListAdapter
import com.happycoder.mymarket.models.Product
import com.happycoder.mymarket.util.ApiHelper

class ListFragment() : Fragment(R.layout.product_list_fragment), Searchable<String?> {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductListAdapter
    private val productListViewModel: ProductListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext() as MainActivity

        val products = productListViewModel.productList.value

        recyclerView = view.findViewById(R.id.productList)

        adapter = ProductListAdapter(context)

        val recyclerView: RecyclerView = view.findViewById(R.id.productList)
        recyclerView.adapter = adapter

        productListViewModel.productList.observe(context, Observer {
            it ?: run{
                Log.d("items", "empty")
                return@Observer
            }
            adapter.setItems(it)
        })
    }

    override fun search(criteria: String?) {
        adapter.filter(criteria)
    }

}