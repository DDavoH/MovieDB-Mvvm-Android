package com.davoh.moviedb_mvvm_android.presentation.utils.alertUtils

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.davoh.moviedb_mvvm_android.R

object AlertUtils {
    
    /**
     * Genera un alerta personalizada.
     * @param context Contexto con el que se crea.
     * @param title Título de la alerta.
     * @param message Mensaje.
     * @param positive Botón positivo.
     * @param negative Botón negativo.
     * @param listener Listener.
     * @return Builder de la alerta.
     */
    private fun getAlert(context: Context, title: Int, message: Int, positive: Int, negative: Int, listener: AlertListener<Alert>): Alert.Builder {
        return Alert.Builder(context)
            .setMessage(message)
            .setTitle(title)
            .setPositiveButton(positive)
            .setNegativeButton(negative)
            .setCancelable(false)
            .setListener(listener)
    }
    
    /**
     * Genera un alerta personalizada.
     * @param context Contexto con el que se crea.
     * @param title Título de la alerta.
     * @param message Mensaje.
     * @param positive Botón positivo.
     * @param negative Botón negativo.
     * @param listener Listener.
     * @return Builder de la alerta.
     */
    fun getAlert(context: Context?, title: String?, message: String?, positive: String?, negative: String?, listener: AlertListener<Alert?>?): Alert.Builder {
        return Alert.Builder(context)
            .setMessage(message)
            .setTitle(title)
            .setPositiveButton(positive)
            .setNegativeButton(negative)
            .setCancelable(false)
            .setListener(listener)
    }
    
    /**
     * Construye un alert de error.
     * @param message Mensaje.
     * @param context Contexto.
     * @param manager Manejador.
     * @param listener Escucha.
     */
    fun showAlertError(message: String?, context: Context, manager: FragmentManager?, listener: DefaultAlertListener?) {
        getAlert(context, context.getString(R.string.message_error), message, context.getString(R.string.dialog_ok), null, listener).show(manager, "error")
    }
}