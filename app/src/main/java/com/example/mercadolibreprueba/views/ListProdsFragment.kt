package com.example.mercadolibreprueba.views

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mercadolibreprueba.R
import com.example.mercadolibreprueba.adapters.AdapterProducts
import com.example.mercadolibreprueba.adapters.OnItemClickListener
import com.example.mercadolibreprueba.databinding.ActivityMainBinding
import com.example.mercadolibreprueba.databinding.FragmentListProdsBinding
import com.example.mercadolibreprueba.model.search.Results
import com.example.mercadolibreprueba.utils.Utils
import com.example.mercadolibreprueba.viewmodel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListProdsFragment : Fragment(), OnItemClickListener {

    private var _binding : FragmentListProdsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProductsViewModel by viewModels()
    private val adapter = AdapterProducts(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkInternet()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListProdsBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.itemsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.itemsRecyclerView.adapter = adapter

        binding.imgSearch.setOnClickListener {
            val text = binding.edtSearch.editableText.toString()

            searchItem(text)

        }

        showData()
        showProgress()

        return view
    }

    private fun sendData(text:String) {
        lifecycleScope.launchWhenCreated {
            viewModel.showProgress(true)
            viewModel.searchProducts(text)
        }
    }

    private fun showData() {
        lifecycleScope.launchWhenCreated {
            viewModel.getProducts().observe(viewLifecycleOwner, {
                it?.let {
                    adapter.setData(it.results)
                    binding.group2.visibility = if(it.results.isEmpty()) View.VISIBLE else View.GONE

                    if(it.results.isEmpty())
                    setMsgMain(getString(R.string.not_found_error))
                }
            })
        }
    }

    private fun showProgress() {
        viewModel.getIsLoading().observe(viewLifecycleOwner, {
            binding.progressCircular.visibility =  if(it) View.VISIBLE else View.GONE
        })

        viewModel.getError().observe(viewLifecycleOwner, {
            it?.let {
                showMessage(it)
            }
        })
    }

    private fun showMessage(message:String ) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun setMsgMain(msg : String) {
        binding.textMsg.text = msg
    }

    override fun onItemClick(result: Results) {
        val dataResult = Bundle().apply {
            putSerializable(getString(R.string.intent_product), result)
        }
        view?.findNavController()?.navigate(R.id.fragmentlist_to_detail,dataResult)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkInternet() {
        if (!Utils.isInternetAvailable(requireContext()))
            showMessage(getString(R.string.no_conection))
    }

    private fun searchItem(text: String) {

        if (Utils.isInternetAvailable(requireContext())) {
            if (text.isBlank()) {
                showMessage(getString(R.string.field_empty))
            }else {
                sendData(text)
            }
        } else {
            showMessage(getString(R.string.no_conection))
        }
    }
}