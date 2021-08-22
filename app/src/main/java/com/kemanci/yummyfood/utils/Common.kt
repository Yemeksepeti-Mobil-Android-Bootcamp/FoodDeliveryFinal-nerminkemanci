package com.kemanci.yummyfood.utils

import android.view.View

class Common {
    companion object {
        fun isEmailValid(email: String): Boolean {
            return email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
        fun alphaAnim(view: View){
            view.alpha = 0.4f
            view.animate()!!.alpha(1f).setDuration(500).start()
        }
    }
}