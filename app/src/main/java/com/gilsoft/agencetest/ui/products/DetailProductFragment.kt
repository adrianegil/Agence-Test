package com.gilsoft.agencetest.ui.products

import android.app.Activity
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.gilsoft.agencetest.R
import com.gilsoft.agencetest.databinding.DetailProductFragmentBinding
import com.gilsoft.agencetest.entity.Product
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

class DetailProductFragment : Fragment(), OnMapReadyCallback {
    private var _binding: DetailProductFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var map: GoogleMap


    private lateinit var viewModel: DetailProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailProductFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val mainNavController =
            Navigation.findNavController(context as Activity, R.id.nav_host_fragment_content_main)

        arguments?.let { bundle ->
            val product: Product = (bundle.getSerializable("product") as Product?)!!
            binding.textViewProductTitleDetail.text = product.title
            binding.textViewProductDescription.text = product.description
        }

        binding.buttonBuy.setOnClickListener {
            val dialog = AlertDialog.Builder(
                requireActivity()
            )
            dialog.setTitle(getString(R.string.confirm_buy))
            dialog.setMessage(getString(R.string.confirm_buy_again))
            dialog.setPositiveButton(getString(R.string.yes)) { dialog13: DialogInterface, which: Int ->

                Toast.makeText(context, getString(R.string.buy_confirm_correct), Toast.LENGTH_SHORT)
                    .show()
                mainNavController.popBackStack(R.id.nav_productsFragment, false)

            }
            dialog.setNegativeButton(
                "No"
            ) { dialog13: DialogInterface, which: Int -> dialog13.dismiss() }
            dialog.setNeutralButton(getString(R.string.cancel)) { dialog1, which -> dialog1.dismiss() }
            dialog.show()
        }
        //binding.mapView.getMapAsync(this)
        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync(this)

        return root
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
    }


}