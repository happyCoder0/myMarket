package com.happycoder.mymarket

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

class ChooserFragment() : Fragment(R.layout.product_chooser_fragment) {

    lateinit var orderedQuantity: TextView
    lateinit var nameView: TextView

    lateinit var quantitySelector: SeekBar

    lateinit var editComment: EditText

    lateinit var addButton: Button
    lateinit var cancelButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireContext() as MainActivity

        val product: Product = arguments?.get("product") as Product

        with(view){
            orderedQuantity = findViewById(R.id.orderedQuantity)
            nameView = findViewById(R.id.productName)
            quantitySelector = findViewById(R.id.quantitySelector)
            editComment = findViewById(R.id.editComment)
            addButton = findViewById(R.id.addButton)
            cancelButton = findViewById(R.id.cancelButton)
        }


        nameView.text = product.name

        with(quantitySelector) {
            progressDrawable.colorFilter = PorterDuffColorFilter(
                resources
                    .getColor(R.color.purple_200), PorterDuff.Mode.MULTIPLY
            )

            thumb.colorFilter = PorterDuffColorFilter(
                resources
                    .getColor(R.color.white), PorterDuff.Mode.MULTIPLY
            )

            min = 1
            max = product.quantity

            setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    orderedQuantity.text = "Select the quantity: ${p1.toString()}"
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {

                }

            })
        }

        addButton.setOnClickListener {
            activity.addToCart(
                Order(
                    product, quantitySelector.progress,
                    editComment.text.toString()
                )
            )
            activity.replaceFragment(R.id.mainFragment, activity.listFragment)
        }
        cancelButton.setOnClickListener {
            activity.replaceFragment(R.id.mainFragment, activity.listFragment)
        }

    }
}