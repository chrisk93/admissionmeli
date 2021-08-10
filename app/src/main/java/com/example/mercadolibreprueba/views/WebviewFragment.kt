package com.example.mercadolibreprueba.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.example.mercadolibreprueba.R
import com.example.mercadolibreprueba.databinding.FragmentDetailBinding
import com.example.mercadolibreprueba.databinding.FragmentListProdsBinding
import com.example.mercadolibreprueba.databinding.FragmentWebviewBinding
import com.example.mercadolibreprueba.model.search.Results

class WebviewFragment : Fragment() {

    private var _binding : FragmentWebviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWebviewBinding.inflate(inflater, container, false)
        val view = binding.root

        val url = arguments?.getString(getString(R.string.intent_url)) ?: "https://mercadolibre.com.co"

        binding.webMeli.also {
            it.clearCache(true)
            it.webViewClient = WebViewClient()
            it.webChromeClient = WebChromeClient()
            it.settings.domStorageEnabled = true
            it.settings.allowFileAccess = true
            it.loadUrl(url)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}