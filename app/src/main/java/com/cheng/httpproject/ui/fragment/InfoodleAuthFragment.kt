package com.cheng.httpproject.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheng.httpproject.R
import com.cheng.httpproject.applySchedulers
import com.cheng.httpproject.service.InfoodleApiService
import com.cheng.httpproject.ui.activity.InfoodleActivity
import kotlinx.android.synthetic.main.fragment_infoodle_auth.*

class InfoodleAuthFragment : Fragment(), View.OnClickListener {

    companion object {
        @JvmStatic
        fun newInstance() = InfoodleAuthFragment()

        private const val TAG = "InfoodleAuth"
    }

    private var activity: InfoodleActivity? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_infoodle_auth, container, false)

        return view
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
                val username = et_infoodle_user_name.text.toString()
                val password = et_infoodle_password.text.toString()
                val observable = InfoodleApiService.getInstance().fetchClientSecret(username, password)
                val disposable = observable.applySchedulers()
                        .subscribe({detail ->
                            Log.i(TAG, detail.clientSecret)
                        }, {error ->
                            Log.w(TAG, error.localizedMessage)
                        })
                activity?.addDisposable(disposable)
            }
        }
    }

    private fun initResources() {
        btn_infoodle_login.setOnClickListener(this)
    }

}
