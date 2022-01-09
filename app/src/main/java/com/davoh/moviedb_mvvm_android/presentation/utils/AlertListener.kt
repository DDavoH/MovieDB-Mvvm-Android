package com.davoh.moviedb_mvvm_android.presentation.utils

interface AlertListener<T> {

    fun onCreate(item: T)
    fun onCancel(item: T)
    fun onClickPositive(item: T)
    fun onClickNegative(item: T)
    fun onDismiss(item: T)
    
}