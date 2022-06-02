package com.gilsoft.agencetest.ui.products

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.gilsoft.agencetest.R
import com.gilsoft.agencetest.databinding.ProductItemListBinding
import com.gilsoft.agencetest.entity.Product
import java.util.ArrayList

class ProductAdapter(var productsList: ArrayList<Product>, var context: Context?) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ProductItemListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.product_item_list,
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product: Product = productsList.get(position)
        holder.binding.textViewProductTitle.text = product.title
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    class ViewHolder(binding: ProductItemListBinding) : RecyclerView.ViewHolder(binding.getRoot()) {
        val binding: ProductItemListBinding

        init {
            this.binding = binding
        }

    }
}