package com.happycoder.mymarket.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.happycoder.mymarket.ChooserFragment
import com.happycoder.mymarket.MainActivity
import com.happycoder.mymarket.R
import com.happycoder.mymarket.models.Product

class ProductListAdapter(private val context: Context) :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    private var products: ArrayList<Product> = ArrayList()
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var copy: ArrayList<Product> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = inflater.inflate(R.layout.product_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product: Product = products[position]
        holder.nameView.text = product.name
        holder.quantityView.text = "Quantity: " + product.quantity.toString()
        holder.priceButton.text = "$" + product.price.toString()
        holder.priceButton.setOnClickListener() {
            if (product.quantity > 0) {
                val chooserFragment: ChooserFragment = ChooserFragment()
                val bundle: Bundle = Bundle()
                bundle.putSerializable("product", product)
                chooserFragment.arguments = bundle
                (context as MainActivity).replaceFragment(R.id.mainFragment, chooserFragment)
            }
        }
    }

    override fun getItemCount(): Int = products.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.findViewById(R.id.nameView)
        val quantityView: TextView = view.findViewById(R.id.quantityView)
        val priceButton: Button = view.findViewById(R.id.priceButton)
    }

    fun setItems(items: ArrayList<Product>) {
        products = items
        copy = products
        notifyDataSetChanged()
    }

    fun filter(query: String?) {
        if (query != null && query.isNotBlank()) {
            val result: ArrayList<Product> = ArrayList()
            for (p in products) {
                if (p.name.contains(query, ignoreCase = true) ||
                    p.name.lowercase() == query.lowercase()
                ) {
                    result.add(p)
                }
            }
            products = result
        } else {
            products = copy
        }
        notifyDataSetChanged()
    }
}