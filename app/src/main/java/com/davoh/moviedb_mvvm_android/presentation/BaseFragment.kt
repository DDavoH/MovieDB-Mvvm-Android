package com.davoh.moviedb_mvvm_android.presentation

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.davoh.moviedb_mvvm_android.data.messageAlert.MessageAlert
import com.davoh.moviedb_mvvm_android.data.messageAlert.MessageAlertTypeError
import com.davoh.moviedb_mvvm_android.presentation.utils.alertUtils.Alert
import com.davoh.moviedb_mvvm_android.presentation.utils.alertUtils.AlertUtils
import com.davoh.moviedb_mvvm_android.presentation.utils.alertUtils.DefaultAlertListener
import timber.log.Timber


open class BaseFragment : Fragment() {
    
    fun configToolbar(toolbar: Toolbar) {
        val appBarConfiguration = AppBarConfiguration(findNavController().graph)
       toolbar.setupWithNavController(findNavController(), appBarConfiguration)
    }
    
    fun titleFragment(title:String, tvTitle:TextView){
        tvTitle.text = title
    }
    
    fun showLoader(show: Boolean, loader:View){
        when(show){
            true -> {
                loader.visibility = View.VISIBLE
            }
            false -> {
                loader.visibility = View.INVISIBLE
            }
        }
    }
    
    fun showMessageAlert(messageAlert: MessageAlert){
        Timber.e("Mensaje de alerta recibido")
        when(messageAlert.typeMessage){
            MessageAlertTypeError.SUCCESS.id ->{
                showDialogSuccess(messageAlert)
            }
            MessageAlertTypeError.ERROR.id -> {
                showDialogError(messageAlert)
            }
            MessageAlertTypeError.WARNING.id -> {
            
            }
        }
    }
    
    fun showDialogSuccess(messageAlert: MessageAlert){
        AlertUtils.getAlert(requireContext(),
           "test",
            "test",
            "test",
            "test",
            object : DefaultAlertListener() {
                override fun onClickPositive(item: Alert?) {
                    super.onClickPositive(item)
                }
            }).show(parentFragmentManager, "confirm")
    }
    
    fun showDialogError(messageAlert: MessageAlert){
        AlertUtils.getAlert(requireContext(),
            "test",
            "test",
            "test",
            "test",
            object : DefaultAlertListener() {
                override fun onClickPositive(item: Alert?) {
                    super.onClickPositive(item)
                }
            }).show(parentFragmentManager, "confirm")
    }

}