package com.cheng.httpproject.ui.fragment.base

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.cheng.httpproject.R
import com.cheng.httpproject.oauth2.OAuth2Constants
import kotlinx.android.synthetic.main.fragment_web_view.*

class WebViewFragment : BaseFragment() {

    companion object {
        private const val ARG_URL = "url"

        fun newInstance(url: String) = WebViewFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_URL, url)
            }
        }
    }

    inner class CustomWebViewClient: WebViewClient() {
        override fun onPageFinished(view: WebView, url: String) {
            hideLoading()
        }

        override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
            if (url.contains(OAuth2Constants.REDIRECT_URI_ROOT)) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                activity?.startActivity(intent)
                activity?.finish()

                return true
            }

            return super.shouldOverrideUrlLoading(view, url)
        }
    }

    override val TAG = "WebView"
    private lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(ARG_URL, "")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoading()
        web_view.loadUrl(url)
        web_view.webViewClient = CustomWebViewClient()
    }

}
