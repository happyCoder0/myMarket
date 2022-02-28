package com.happycoder.mymarket.adapters

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.happycoder.mymarket.*
import com.happycoder.mymarket.models.Order
import com.happycoder.mymarket.models.Product
import kotlin.collections.ArrayList

class ProductListAdapter(private val context: Context,
                         list: List<Product>) :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var products: ArrayList<Product> = list as ArrayList<Product>
    private val copy: ArrayList<Product> = ArrayList<Product>().also {
        it.addAll(products)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val view: View = inflater.inflate(R.layout.product_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product: Product = products[position]
        holder.nameView.text = product.name
        holder.quantityView.text = "Quantity: " + product.quantity.toString()
        holder.priceButton.text = "$" + product.price.toString()
        holder.priceButton.setOnClickListener(){
            val chooserFragment: ChooserFragment = ChooserFragment()
            val bundle: Bundle = Bundle()
            bundle.putSerializable("product", product)
            chooserFragment.arguments = bundle
            (context as MainActivity).replaceFragment(R.id.mainFragment, chooserFragment)
        }

        if(product.quantity == 0){
            holder.quantityView.setTextColor(context.getColor(R.color.red))
            holder.priceButton.isEnabled = false
        }
    }

    override fun getItemCount(): Int = products.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.findViewById(R.id.nameView)
        val quantityView: TextView = view.findViewById(R.id.quantityView)
        val priceButton: Button = view.findViewById(R.id.priceButton)
    }

    fun filter(query: String?){
        if(query != null && query.isNotBlank()){
            val result: ArrayList<Product> = ArrayList()
            for(p in products){
                if (p.name.contains(query, ignoreCase = true) ||
                    p.name.lowercase() == query.lowercase()){
                    result.add(p)
                    Log.d("quantity", p.quantity.toString())
                }
            }
            products = result
        }else{
            products = copy
        }
        notifyDataSetChanged()
    }
}