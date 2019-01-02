package com.cheng.httpproject.ui.fragment.base

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment

open class BaseDialogFragment : DialogFragment(), DialogInterface.OnClickListener {
    val TAG = "BaseDialog"
    var title: String? = null
    var message: String? = null
    var positiveText: String? = null
    var negativeText: String? = null
    var positiveAction: (() -> Unit)? = null
    var negativeAction: (() -> Unit)? = null

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
                .setNegativeButton(negativeText?:cancel, this)
        if (getLayoutResId() != 0) {
            builder.setView(getLayoutResId())
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

}