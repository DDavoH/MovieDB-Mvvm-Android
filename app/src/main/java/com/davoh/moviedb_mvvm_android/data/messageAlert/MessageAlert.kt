package com.davoh.moviedb_mvvm_android.data.messageAlert

data class MessageAlert (
    var typeMessage:Int,
    var title:String?="",
    var content:String?=""
        )

const val ERROR = 0
const val MESSAGE_SUCCESS = 1
const val MESSAGE_WARNING = 2