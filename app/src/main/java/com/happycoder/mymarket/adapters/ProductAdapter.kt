package com.happycoder.mymarket.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.happycoder.mymarket.R
import com.happycoder.mymarket.models.Product
import kotlin.collections.ArrayList

class ProductAdapter(context: Context, list: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var products: ArrayList<Product> = list as ArrayList<Product>
    private val context: Context = context
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
        holder.priceView.text = "$" + product.price.toString()

        if(product.quantity == 0){
            Log.d("onBindViewHolder quantity", product.quantity.toString())
            holder.quantityView.setTextColor(context.getColor(R.color.red))
        }
    }

    override fun getItemCount(): Int = products.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.findViewById(R.id.nameView)
        val quantityView: TextView = view.findViewById(R.id.quantityView)
        val priceView: Button = view.findViewById(R.id.priceView)
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