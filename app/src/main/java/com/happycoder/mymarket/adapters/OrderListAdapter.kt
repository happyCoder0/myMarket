package com.happycoder.mymarket.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.happycoder.mymarket.R
import com.happycoder.mymarket.models.Order

class OrderListAdapter(
    private val context: Context,
    private val orderList: ArrayList<Order>
    ) : RecyclerView.Adapter<OrderListAdapter.ViewHolder>(){

    private val inflater = LayoutInflater.from(context)

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val productNameView: TextView = view.findViewById(R.id.productName)
        val decreaseButton: Button = view.findViewById(R.id.decreaseButton)
        val orderedQuantityView: TextView = view.findViewById(R.id.orderedQuantity)
        val increaseButton: Button = view.findViewById(R.id.increaseButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.cart_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order: Order = orderList[position]
        holder.productNameView.text = order.product.name
        holder.orderedQuantityView.text = order.quantity.toString()

        holder.increaseButton.setOnClickListener {
            if(order.quantity < order.product.quantity) {
                order.quantity += 1
                holder.orderedQuantityView.text = order.quantity.toString()
            } else {
                Toast.makeText(context, "You've ordered a max amount already",
                    Toast.LENGTH_LONG).show()
            }
        }

        holder.decreaseButton.setOnClickListener {
            if(order.quantity > 1){
                order.quantity -= 1
                holder.orderedQuantityView.text = order.quantity.toString()
            } else {
                orderList.removeAt(position)
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return orderList.size
    }
}