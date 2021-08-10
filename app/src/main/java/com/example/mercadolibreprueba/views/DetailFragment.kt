package com.example.mercadolibreprueba.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mercadolibreprueba.R
import com.example.mercadolibreprueba.adapters.AdapterDesc
import com.example.mercadolibreprueba.databinding.FragmentDetailBinding
import com.example.mercadolibreprueba.model.search.Results
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val adapterDesc = AdapterDesc()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.recyclerDesc.layoutManager = LinearLayoutManager(context)
        binding.recyclerDesc.adapter = adapterDesc

        val product = arguments?.getSerializable(getString(R.string.intent_product)) as Results

        binding.btnComprar.setOnClickListener {

            val url = Bundle().apply {
                putString(getString(R.string.intent_url), product.permalink)
            }
            view.findNavController().navigate(R.id.fragmentdetail_to_webview, url)


        }

        showProduct(product)

        return view
    }

    private fun showProduct(product: Results) {

        Glide.with(this)
            .load(product.thumbnail)
            .placeholder(R.drawable.image_not)
            .apply(RequestOptions().override(600, 200))
            .into(binding.imgProduct)

        binding.textCond.text = getString(R.string.state, product.condition)
        binding.textQuantity.text = getString(R.string.quantity_value, product.sold_quantity.toInt())
        binding.textName.text = product.title
        binding.textPrice.text = getString(R.string.price, product.price.toInt())
        binding.textDues.text = getString(R.string.dues, product.installments?.quantity ?: 0)
        binding.textShipping.text = if(product.shipping!!.free_shipping) getString(R.string.shipping_free) else getString(R.string.shipping_with_cost)
        binding.textAvailableQuantity.text = getString(R.string.quantity, product.available_quantity)
        binding.textLocation.text = getString(R.string.location_state, product.address!!.state_name, product.address.city_name)

        adapterDesc.setData(product.attributes)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}