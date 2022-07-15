package com.appcrafters.sportresults.base.functional

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object ViewModelFactoryUtil {
    fun <VM : ViewModel> viewModelFactory(f: () -> VM) =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(aClass: Class<T>): T = f() as T
        }
}