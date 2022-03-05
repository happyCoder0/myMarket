package com.happycoder.mymarket

import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.happycoder.mymarket.models.Order
import com.happycoder.mymarket.models.Product

class ChooserFragment()
    : Fragment(R.layout.product_chooser_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireContext() as MainActivity
        val product: Product = arguments?.get("product") as Product
        val orderedQuantity: TextView = view.findViewById(R.id.orderedQuantity)
        val nameView: TextView = view.findViewById(R.id.productName)
        val quantitySelector: SeekBar = view.findViewById(R.id.quantitySelector)
        val editComment: EditText = view.findViewById(R.id.editComment)
        val addButton: Button = view.findViewById(R.id.addButton)
        val cancelButton: Button = view.findViewById(R.id.cancelButton)
        nameView.text = product.name
        quantitySelector.progressDrawable.colorFilter = PorterDuffColorFilter(resources
            .getColor(R.color.purple_200), PorterDuff.Mode.MULTIPLY)
        quantitySelector.thumb.colorFilter = PorterDuffColorFilter(resources
            .getColor(R.color.white), PorterDuff.Mode.MULTIPLY)
        quantitySelector.min = 1
        quantitySelector.max = product.quantity
        quantitySelector.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                orderedQuantity.text = "Select the quantity: ${p1.toString()}"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })
        addButton.setOnClickListener {
            (requireContext() as MainActivity).addToCart(Order(product, quantitySelector.progress,
            editComment.text.toString()))
            activity.replaceFragment(R.id.mainFragment, activity.listFragment)
        }
        cancelButton.setOnClickListener {
            activity.replaceFragment(R.id.mainFragment, activity.listFragment)
        }

    }
}