package com.visualstudioex3.logincompose.services

import android.content.Context
import android.widget.Toast

class ToastService(private val context: Context): IToastService {
    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT)
            .show()
    }
}
