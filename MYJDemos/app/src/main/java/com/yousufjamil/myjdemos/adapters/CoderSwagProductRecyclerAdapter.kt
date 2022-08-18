package com.yousufjamil.myjdemos.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yousufjamil.myjdemos.R
import com.yousufjamil.myjdemos.model.Category

class CoderSwagProductRecyclerAdapter(val context: Context, val categories: List<Category>): RecyclerView.Adapter<CoderSwagProductRecyclerAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.coderswag_recycler_view, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bindCategorey(context, categories[position])
    }

    override fun getItemCount(): Int {
        return categories.count()
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryImage = itemView.findViewById<ImageView>(R.id.coderswagProductRecyclerImg)
        val categoryTxt = itemView.findViewById<TextView>(R.id.coderswagProductRecyclerTxt)

        fun bindCategorey (context: Context, category: Category) {
            val resourceId = context.resources.getIdentifier(category.image, "drawable", context.packageName)
            categoryImage?.setImageResource(resourceId)
            categoryTxt?.text = category.title
        }
    }
}