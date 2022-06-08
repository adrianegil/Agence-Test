package com.gilsoft.agencetest.ui.products

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.gilsoft.agencetest.R
import com.gilsoft.agencetest.databinding.ProductItemListBinding
import com.gilsoft.agencetest.entity.Product

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

        val mainNavController =
            Navigation.findNavController(context as Activity, R.id.nav_host_fragment_content_main)

        val product: Product = productsList[position]
        holder.binding.textViewProductTitle.text = product.title
        holder.binding.cardViewProduct.setOnClickListener {
            val bundle = bundleOf("product" to product)
            Toast.makeText(context, product.title, Toast.LENGTH_SHORT).show()
            mainNavController.navigate(R.id.go_detailProductFragment, bundle)
        }

    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    class ViewHolder(binding: ProductItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding: ProductItemListBinding

        init {
            this.binding = binding
        }

    }
}