package com.example.deliveryapp.presentation.common

import android.text.Editable
import androidx.fragment.app.Fragment

const val NES = "Not enough symbols"
const val INCORRECT = "Incorrect email"

fun Fragment.emailValid(email: Editable?): String? {
    val emailPattern = "^[a-z0-9]+@[a-z]+\\.[a-z]{1,3}\$".toRegex()
    if (email!!.isEmpty())
        return NES
    if (!emailPattern.matches(email))
        return INCORRECT
    return null
}

fun Fragment.passwordValid(password: Editable?): String? {
    return if (password!!.isEmpty())
        NES
    else null
}
