package com.davoh.moviedb_mvvm_android.presentation.utils.alertUtils;

import android.view.View;

public abstract class SingleClickListener implements View.OnClickListener {
    /** Tiempo del último click. */
    private long timestamp = System.currentTimeMillis();
    /** Retardo. */
    private long timeout = 1000L;
    /** Indica si es el primer click. */
    private boolean first = true;

    /**
     * Constructor por defecto.
     */
    public SingleClickListener() { }

    /**
     * Constructor con retardo.
     * @param timeout Retardo.
     */
    public SingleClickListener(long timeout) {
        this.timeout = timeout;
    }

    /**
     * Método escucha de los click.
     * @param view Vista.
     */
    public void onClick(View view) {
        if (first || this.isSingleClick(System.currentTimeMillis())) {
            this.onSingleClick(view);
            first = false;
        }
    }

    /**
     * Indica si es un click aceptado.
     * @param now Tiempo.
     * @return <code>true</code> si se acepta o <code>false</code> en caso contrario.
     */
    private boolean isSingleClick(long now) {
        if (now - this.timestamp >= this.timeout) {
            this.timestamp = now;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para los clicks.
     * @param view Vista escucha.
     */
    protected abstract void onSingleClick(View view);
}

