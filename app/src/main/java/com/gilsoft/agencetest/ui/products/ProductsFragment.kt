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
        val product1 = Product(
            1,
            "Product Title 1",
            "Product description 1 is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
        )
        productsList.add(product1)
        val product2 = Product(
            2,
            "Product Title 2",
            "Product description 2 Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia."
        )
        productsList.add(product2)
        val product3 = Product(
            3,
            "Product Title 3",
            "Product description 3 There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable."
        )
        productsList.add(product3)
        val product4 = Product(
            4,
            "Product Title 4",
            "Product description 4 The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from de Finibus Bonorum et Malorum by Cicero are also reproduced in their exact original."
        )
        productsList.add(product4)
        val product5 = Product(
            5,
            "Product Title 5",
            "Product description 5 This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32."
        )
        productsList.add(product5)
        val product6 = Product(
            6,
            "Product Title 6",
            "Product description 6 All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words."
        )
        productsList.add(product6)
        val product7 = Product(
            7,
            "Product Title 7",
            "Product description 7 Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy."
        )
        productsList.add(product7)
        val product8 = Product(
            8,
            "Product Title 8",
            "Product description 8 Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
        )
        productsList.add(product8)
        val product9 = Product(
            9,
            "Product Title 9",
            "Product description 9 At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi."
        )
        productsList.add(product9)
        val product10 = Product(
            10,
            "Product Title 10",
            "Product description 10 But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted."
        )
        productsList.add(product10)
        val product11 = Product(
            11,
            "Product Title 11",
            "Product description 11 These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided."
        )
        productsList.add(product11)
        val product12 = Product(
            12,
            "Product Title 12",
            "Product description 12 It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters"
        )
        productsList.add(product12)

        var productsRecyclerView: RecyclerView = binding.recyclerViewProductsList
        var productsAdapter = ProductAdapter(productsList, context)
        productsRecyclerView.layoutManager = GridLayoutManager(context, 2)
        productsRecyclerView.adapter = productsAdapter

    }

}