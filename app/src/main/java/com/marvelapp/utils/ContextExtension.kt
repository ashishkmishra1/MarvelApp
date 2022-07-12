package com.marvelapp.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.marvelapp.R

fun Context.showAlertDialog(
    errorMessage: String,
    cancelable: Boolean = true,
    listener: (dialog: DialogInterface, which: Int) -> Unit = { _, _ -> }
) {
    val dialog: AlertDialog = AlertDialog.Builder(this)
        .setCancelable(cancelable)
        .setMessage(errorMessage)
        .setPositiveButton(getString(R.string.title_ok), listener)
        .create()

    dialog.show()

    dialog.getButton(AlertDialog.BUTTON_POSITIVE)
}