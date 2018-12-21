package com.cheng.httpproject.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheng.httpproject.R
import com.cheng.httpproject.applySchedulers
import com.cheng.httpproject.helper.SharedPrefHelper
import com.cheng.httpproject.oauth2.OAuth2Constants
import com.cheng.httpproject.service.InfoodleApiService
import com.cheng.httpproject.ui.activity.InfoodleActivity
import com.cheng.httpproject.ui.fragment.base.BaseFragment
import com.cheng.httpproject.ui.fragment.base.WebViewFragment
import kotlinx.android.synthetic.main.fragment_infoodle_auth.*
import okhttp3.HttpUrl

class InfoodleAuthFragment : BaseFragment(), View.OnClickListener {

    override val TAG = "InfoodleAuth"

    private lateinit var activity: InfoodleActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_infoodle_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initResources()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity = getActivity() as InfoodleActivity
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_infoodle_login -> {
                val clientId = et_infoodle_user_name.text.toString()
                val password = et_infoodle_password.text.toString()
                val service = InfoodleApiService.getInstance(activity).getService()
                val observable = service.fetchClientSecret(clientId, password)
                val disposable = observable.applySchedulers()
                        .subscribe({detail ->
                            val sharedPrefHelper = SharedPrefHelper.getInstance(activity)
                            sharedPrefHelper.saveOAuth2LoginDetail(clientId, detail.clientSecret)

                            val builder = HttpUrl.parse(OAuth2Constants.AUTHORIZATION_URL)!!.newBuilder()
                            val authUrl = builder!!.addQueryParameter(OAuth2Constants.CLIENT_ID, clientId)
                                    .addQueryParameter(OAuth2Constants.CLIENT_SECRET, detail.clientSecret)
                                    .addQueryParameter(OAuth2Constants.REDIRECT_URI, OAuth2Constants.REDIRECT_URI_VALUE)
                                    .addQueryParameter(OAuth2Constants.RESPONSE_TYPE, OAuth2Constants.CODE)
                                    .build()
                            val url = authUrl.toString()

                            val webViewFragment = WebViewFragment.newInstance(url);
                            activity.replaceFragment(webViewFragment)
                        }, {error ->
                            activity.showToast(error.localizedMessage)
                        })
                activity.addDisposable(disposable)
            }
        }
    }

    private fun initResources() {
        btn_infoodle_login.setOnClickListener(this)
    }

}
