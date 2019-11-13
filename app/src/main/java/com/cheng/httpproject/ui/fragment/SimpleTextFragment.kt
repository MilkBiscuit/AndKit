package com.cheng.httpproject.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cheng.httpproject.R
import kotlinx.android.synthetic.main.fragment_simple_text.*

class SimpleTextFragment : Fragment() {

    companion object {
        private const val ARG_TEXT = "text"
        @JvmStatic
        fun newInstance(text: String) = SimpleTextFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_TEXT, text)
            }
        }
    }

    private var text: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            text = it.getString(ARG_TEXT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_simple_text, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_text.text = text
    }

}
