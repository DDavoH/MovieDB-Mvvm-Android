package com.davoh.moviedb_mvvm_android.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davoh.moviedb_mvvm_android.data.messageAlert.MessageAlert

open class BaseViewModel : ViewModel() {
    
    val isLoading = MutableLiveData<Boolean>()
    val messageAlert = MutableLiveData<MessageAlert>()
    
    
}