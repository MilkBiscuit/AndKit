package com.cheng.httpproject.ui.fragment.base

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.view.View


open class BaseDialogFragment : DialogFragment(), DialogInterface.OnClickListener {

    companion object {
        val TAG = BaseDialogFragment::class.java.simpleName
    }

    var title: String? = null
    var message: String? = null
    open var positiveText: String? = null
    open var negativeText: String? = null
    open var positiveAction: (() -> Unit)? = null
    open var negativeAction: (() -> Unit)? = null

    protected lateinit var ok: String
    protected lateinit var cancel: String
    protected lateinit var attention: String

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        if (!::ok.isInitialized) {
            ok = activity!!.getString(android.R.string.ok)
        }
        if (!::cancel.isInitialized) {
            cancel = activity!!.getString(android.R.string.cancel)
        }
        if (!::attention.isInitialized) {
            attention = activity!!.getString(android.R.string.dialog_alert_title)
        }

        val builder = AlertDialog.Builder(activity)
                .setTitle(title?:attention)
                .setMessage(message)
                .setPositiveButton(positiveText?:ok, this)
        if (negativeText != null || negativeAction != null) {
            builder.setNegativeButton(negativeText ?: cancel, this)
        }
        if (getLayoutResId() != 0) {
            builder.setView(setupCustomView())
        }

        return builder.create()
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        when(which) {
            DialogInterface.BUTTON_POSITIVE -> positiveAction?.invoke()
            DialogInterface.BUTTON_NEGATIVE -> negativeAction?.invoke()
        }
    }

    open fun getLayoutResId(): Int {
        return 0
    }

    open fun setupCustomView(): View {
        val view = activity!!.layoutInflater.inflate(getLayoutResId(), null)

        return view
    }

}