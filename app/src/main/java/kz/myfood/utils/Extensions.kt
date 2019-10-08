package kz.myfood.utils

import androidx.fragment.app.Fragment

fun Fragment?.isFragmentAddedAndVisible(): Boolean {
    return this != null && this.isAdded && this.isVisible
}