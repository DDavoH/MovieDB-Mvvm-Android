package com.davoh.moviedb_mvvm_android.presentation.utils.alertUtils;

import static com.davoh.moviedb_mvvm_android.presentation.utils.alertUtils.AlertEvent.CANCEL;
import static com.davoh.moviedb_mvvm_android.presentation.utils.alertUtils.AlertEvent.CREATE;
import static com.davoh.moviedb_mvvm_android.presentation.utils.alertUtils.AlertEvent.DISMISS;
import static com.davoh.moviedb_mvvm_android.presentation.utils.alertUtils.AlertEvent.NEGATIVE;
import static com.davoh.moviedb_mvvm_android.presentation.utils.alertUtils.AlertEvent.POSITIVE;

import javax.annotation.Nonnull;

public class DefaultAlertListener implements AlertListener<Alert> {

    /** Listener que atiende los eventos de una alerta. */
    private final OnAlertEventListener listener;

    /** Constructor que define el listener por defecto. */
    public DefaultAlertListener() {
        listener = (event, alert) -> {
            if (POSITIVE.equals(event) || NEGATIVE.equals(event))
                alert.dismiss();
        };
    }

    /**
     * Constructor que deine el listener que maneja los eventos de una alerta.
     * @param listener Listener que maneja los eventos de una alerta.
     */
    public DefaultAlertListener(@Nonnull OnAlertEventListener listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(Alert item) {
        listener.onEvent(CREATE, item);
    }

    @Override
    public void onCancel(Alert item) {
        listener.onEvent(CANCEL, item);
    }

    @Override
    public void onClickPositive(Alert item) {
        listener.onEvent(POSITIVE, item);
    }

    @Override
    public void onClickNegative(Alert item) {
        listener.onEvent(NEGATIVE, item);
    }

    @Override
    public void onDismiss(Alert item) {
        listener.onEvent(DISMISS, item);
    }

    /**
     * Interfaz que maneja los eventos de una alerta con eventos.
     */
    public interface OnAlertEventListener {
        /**
         * Maneja un evento sobre una alerta.
         * @param event Evento ocurrido.
         * @param item Alerta sobre la que ocurre un evento.
         */
        void onEvent(AlertEvent event, Alert item);
    }
}
