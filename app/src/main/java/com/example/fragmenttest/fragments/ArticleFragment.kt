package com.example.fragmenttest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import com.example.fragmenttest.R

class ArticleFragment : Fragment() {

    var inputPos: Int? = null
    var inputName: String = ""
    var inputImage: String = ""
    var inputUrl: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_article, container, false)

        val myWebView: WebView = view.findViewById(R.id.articleWebView)
        val webSettings = myWebView.settings

        inputPos = arguments?.getInt("input_pos")
        inputName = arguments?.getString("input_name").toString()
        inputImage = arguments?.getString("input_image").toString()
        inputUrl = arguments?.getString("input_url").toString() // полученные значения, которые можно присваивать

        webSettings.javaScriptEnabled = true
        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true
        webSettings.allowFileAccess = true
        webSettings.domStorageEnabled = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.setSupportMultipleWindows(false)
        webSettings.displayZoomControls = false
        webSettings.builtInZoomControls = true
        webSettings.setSupportZoom(true)
        webSettings.pluginState = WebSettings.PluginState.ON
        webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        webSettings.setAppCacheEnabled(true)
        webSettings.allowContentAccess = true

        myWebView.loadUrl(inputUrl)

        return view

    }

}