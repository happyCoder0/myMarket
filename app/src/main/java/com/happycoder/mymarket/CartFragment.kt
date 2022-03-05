package com.happycoder.mymarket

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.happycoder.mymarket.adapters.OrderListAdapter

class CartFragment() : Fragment(R.layout.cart_fragment){
    private lateinit var orders: RecyclerView
    private lateinit var submitButton: Button
    private lateinit var orderListAdapter: OrderListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        orders = view.findViewById(R.id.orderList)
        submitButton = view.findViewById(R.id.submitOrder)
        val mainActivity = requireContext() as MainActivity
        orderListAdapter = OrderListAdapter(mainActivity, mainActivity.cart)
        orders.adapter = orderListAdapter
    }
}