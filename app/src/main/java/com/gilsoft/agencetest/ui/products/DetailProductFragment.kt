package com.gilsoft.agencetest.ui.products

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.gilsoft.agencetest.R
import com.gilsoft.agencetest.databinding.DetailProductFragmentBinding
import com.gilsoft.agencetest.entity.Product
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class DetailProductFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMyLocationClickListener,
    GoogleMap.OnMyLocationButtonClickListener {
    private var _binding: DetailProductFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var map: GoogleMap
    private lateinit var locationManager:LocationManager


    companion object {
        const val REQUEST_CODE_LOCATION = 0
    }

    private lateinit var viewModel: DetailProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailProductFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val mainNavController =
            Navigation.findNavController(context as Activity, R.id.nav_host_fragment_content_main)

        locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager

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
        map.setOnMyLocationClickListener(this)
        map.setOnMyLocationButtonClickListener(this)
        //createMarker()
        enabledLocation()
    }

    private fun createMarker() {
        val coordinate = LatLng(23.13833, -82.36417)
        val marker = MarkerOptions().position(coordinate).title("Posición del Usuario")
        map.addMarker(marker)
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinate, 18f), 2000, null)
    }

    private fun enabledLocation() {
        if (!::map.isInitialized) return
        if (isLocationPermissionGranted())
            map.isMyLocationEnabled = true
        else
            requestLocationPermission()
    }

    private fun isLocationPermissionGranted() = ContextCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED


    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
            Toast.makeText(
                requireContext(),
                "Debe aceptar los permisos de Ubicación en Ajustes para que pueda ver su Posición Actual en el Mapa",
                Toast.LENGTH_LONG
            ).show()
        else
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION)
            /*ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION
            )*/
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_LOCATION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    map.isMyLocationEnabled = true
                    if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                        Toast.makeText(
                            requireContext(),
                            "Por favor active la Ubicación del dispositivo",
                            Toast.LENGTH_LONG
                        ).show()

                } else
                    Toast.makeText(
                        requireContext(),
                        "Debe aceptar los permisos de Ubicación en Ajustes para que pueda ver su Posición Actual en el Mapa",
                        Toast.LENGTH_LONG
                    ).show()
            }
        }
    }

    override fun onMyLocationClick(p0: Location) {
        Toast.makeText(requireContext(), "Esta es tu Ubicación Actual", Toast.LENGTH_SHORT).show()
    }

    override fun onMyLocationButtonClick(): Boolean {
        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            Toast.makeText(
                requireContext(),
                "Por Favor active la Ubicación del dispositivo",
                Toast.LENGTH_LONG
            ).show()
        else
        Toast.makeText(requireContext(), "Buscando tu Ubicación Actual", Toast.LENGTH_SHORT).show()
        return false
    }

}