package com.yousufjamil.myjcoderswag.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yousufjamil.myjcoderswag.R
import com.yousufjamil.myjcoderswag.adapters.CategoryAdapter.Holder
import com.yousufjamil.myjcoderswag.model.Product

class CategoryAdapter(val context: Context, val products: List<Product>): RecyclerView.Adapter<Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.coderswag_products_recycler_view, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bindCategorey(context, products[position])
    }

    override fun getItemCount(): Int {
        return products.count()
    }


    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val productImage = itemView.findViewById<ImageView>(R.id.itemImageImg)
        val productName = itemView.findViewById<TextView>(R.id.itemNameTxt)
        val productPrice = itemView.findViewById<TextView>(R.id.itemPriceTxt)

        fun bindCategorey (context: Context, product: Product) {
            val resourceId = context.resources.getIdentifier(product.image, "drawable", context.packageName)
            productImage?.setImageResource(resourceId)
            productName?.text = product.title
            productPrice?.text = product.price
        }
    }
}