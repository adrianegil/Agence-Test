package com.gilsoft.agencetest.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gilsoft.agencetest.databinding.ProductsFragmentBinding
import com.gilsoft.agencetest.entity.Product

class ProductsFragment : Fragment() {

    private lateinit var viewModel: ProductsViewModel

    private var _binding: ProductsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProductsFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initRecyclerView()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initRecyclerView() {
        var productsList: ArrayList<Product> = ArrayList()
        val product1 = Product(1, "Product Title 1", "Product description 1")
        productsList.add(product1)
        val product2 = Product(2, "Product Title 2", "Product description 2")
        productsList.add(product2)
        val product3 = Product(3, "Product Title 3", "Product description 3")
        productsList.add(product3)
        val product4 = Product(4, "Product Title 4", "Product description 4")
        productsList.add(product4)
        val product5 = Product(5, "Product Title 5", "Product description 5")
        productsList.add(product5)
        val product6 = Product(6, "Product Title 6", "Product description 6")
        productsList.add(product6)
        val product7 = Product(7, "Product Title 7", "Product description 7")
        productsList.add(product7)
        val product8 = Product(8, "Product Title 8", "Product description 8")
        productsList.add(product8)
        val product9 = Product(9, "Product Title 9", "Product description 9")
        productsList.add(product9)
        val product10 = Product(10, "Product Title 10", "Product description 10")
        productsList.add(product10)
        val product11 = Product(11, "Product Title 11", "Product description 11")
        productsList.add(product11)
        val product12 = Product(12, "Product Title 12", "Product description 12")
        productsList.add(product12)

        var productsRecyclerView: RecyclerView = binding.recyclerViewProductsList
        var productsAdapter = ProductAdapter(productsList, context)
        productsRecyclerView.layoutManager = GridLayoutManager(context, 2)
        productsRecyclerView.adapter = productsAdapter

    }

}