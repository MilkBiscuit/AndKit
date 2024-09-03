package com.cheng.andkit.sample.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheng.andkit.sample.databinding.FragmentWebViewBinding
import com.cheng.andkit.sample.ui.fragment.base.BaseFragment


class WebViewFragment : BaseFragment() {

    companion object {
        private const val ARG_URL = "url"

        fun newInstance(url: String) = WebViewFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_URL, url)
            }
        }
    }

    override val TAG = "WebView"
    private lateinit var url: String
    private lateinit var binding: FragmentWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(ARG_URL, "")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentWebViewBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoading()
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl(url)
    }

}
