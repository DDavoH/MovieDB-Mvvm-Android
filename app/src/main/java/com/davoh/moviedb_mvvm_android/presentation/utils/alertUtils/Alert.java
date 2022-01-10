package com.davoh.moviedb_mvvm_android.presentation.utils.alertUtils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.davoh.moviedb_mvvm_android.databinding.DialogMessageAlertBinding;

public class Alert extends DialogFragment {

    /** Listener de la alerta. */
    private AlertListener<Alert> listener;

    /** Llave para guardar y cargar el tema de la alerta. */
    private static final String ARG_THEME = "theme";
    /** Llave para cargar y guardar el título de la alerta. */
    private static final String ARG_TITLE = "title";
    /** Llave para cargar y guardar el mensaje mostrado. */
    private static final String ARG_MESSAGE = "message";
    /** Llave para cargar y guardar el texto del botón negativo. */
    private static final String ARG_NEGATIVE_BUTTON = "negative";
    /** Llave para cargar y guardar el texto del botón positivo. */
    private static final String ARG_POSITIVE_BUTTON = "positive";

    /**
     * Clase que permite la configuración de una alerta.
     */
    public static class Builder {
        /** Constexto con el que se crea la alerta. */
        private final Context mContext;
        /** Argumentos que se envían a la alerta. */
        private final Bundle mArguments = new Bundle();
        /** Bandera que indica si la alerta es cancelable. */
        private Boolean mCancelable;
        /** Listener de comunicación con la alerta. */
        private AlertListener<Alert> listener;

        /**
         * Constructor del builder con un contexto.
         * @param context Contexto con el que se crea la alerta.
         */
        public Builder(Context context) {
            this(context, 0);
        }

        /**
         * Constructor que define el contexto y el tema.
         * @param context Contexto de la alerta.
         * @param theme Tema de la alerta.
         */
        public Builder(Context context, int theme) {
            mContext = context.getApplicationContext();
            mArguments.putInt(ARG_THEME, theme);
        }

        /**
         * Crea una alerta con los parámetros configurados.
         * @return Devuelve una alerta lista para ser mostrada.
         */
        public Alert create() {
            Alert f = new Alert();
            f.setArguments(mArguments);
            f.setListener(listener);
            f.setCancelable(mCancelable == null || mCancelable);
            return f;
        }

        /**
         * Muestra una alerta con un manejador de fragmentos.
         * @param manager Manejador de fragmentos.
         * @param tag Etiqueta con la que se muestra el fragmento.
         * @return Devuelve la alerta mostrada.
         */
        public Alert show(FragmentManager manager, String tag) {
            clear(manager, tag);
            Alert c = create();
            c.show(manager, tag);
            return c;
        }

        /**
         * limpia los fragmentos existentes con la misma etiqueta.
         * @param manager Manejador de fragmentos mostrados.
         * @param tag Etiqueta que se muestra.
         */
        public void clear(FragmentManager manager, String tag) {
            try {
                Fragment found = manager.findFragmentByTag(tag);
                if (found instanceof Alert)
                    ((Alert) found).dismiss();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

        /**
         * Muestra una alerta con una transición de fragmentos.
         * @param transaction Transacción de fragmentos.
         * @param tag Etiqueta con la que se muestra el fragmento.
         * @return Devuelve la alerta mostrada.
         */
        public Alert show(FragmentTransaction transaction, String tag) {
            Alert c = create();
            c.show(transaction, tag);
            return c;
        }

        public Builder setCancelable(boolean cancelable) {
            mCancelable = cancelable;
            return this;
        }

        public Builder setTitle(CharSequence title) {
            mArguments.putCharSequence(ARG_TITLE, title);
            return this;
        }

        public Builder setTitle(int resId) {
            mArguments.putCharSequence(ARG_TITLE, mContext.getText(resId));
            return this;
        }

        public Builder setMessage(CharSequence message) {
            mArguments.putCharSequence(ARG_MESSAGE, message);
            return this;
        }

        public Builder setMessage(int resId) {
            return setMessage(mContext.getText(resId));
        }

        public Builder setNegativeButton(CharSequence text) {
            mArguments.putCharSequence(ARG_NEGATIVE_BUTTON, text);
            return this;
        }

        public Builder setNegativeButton(int resId) {
            return setNegativeButton(mContext.getText(resId));
        }

        public Builder setPositiveButton(CharSequence text) {
            mArguments.putCharSequence(ARG_POSITIVE_BUTTON, text);
            return this;
        }

        public Builder setPositiveButton(int resId) {
            return setPositiveButton(mContext.getText(resId));
        }

        public Builder setListener(AlertListener<Alert> listener) {
            this.listener = listener;
            return this;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Bundle args = requireArguments();
        // Configuración del alert nativo.
        AlertDialog.Builder builder = getDialogBuilder(args.getInt(ARG_THEME));
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        DialogMessageAlertBinding binding = DialogMessageAlertBinding.inflate(inflater);
        builder.setView(binding.getRoot());
        // Configuración del título y mensaje de la alerta.
        binding.title.setText(args.getCharSequence(ARG_TITLE, ""));
        binding.message.setText(args.getCharSequence(ARG_MESSAGE, ""));
        // Configuración de botones.
        setPositiveButton(binding.positive, args);
        setNegativeButton(binding.negative, args);
        // Creación de la alerta.
        AlertDialog a = builder.create();
        a.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getListener().onCreate(this);
        return a;
    }

    /**
     * Crea un builder de alerta nativo con un tema dado.
     * @param theme Tema que se aplica al builder.
     * @return Devuelve un builder de alerta nativo.
     */
    private AlertDialog.Builder getDialogBuilder(int theme) {
        return theme == 0?
                new AlertDialog.Builder(getActivity()):
                new AlertDialog.Builder(getActivity(), theme);
    }

    /**
     * Obtiene el listener de forma segura.
     * @return Devuelve un listener no nulo de la alerta.
     */
    private AlertListener<Alert> getListener() {
        if (listener == null)
            listener = new DefaultAlertListener();
        return listener;
    }

    /**
     * Configura el botón positivo.
     * @param btn Botón positivo.
     * @param args Argumentos que contienen la configuración.
     */
    private void setPositiveButton(Button btn, Bundle args) {
        final CharSequence positiveButtonText = args.getCharSequence(ARG_POSITIVE_BUTTON);
        if (positiveButtonText == null)
            return;
        btn.setText(positiveButtonText);
        btn.setOnClickListener(new SingleClickListener() {
            @Override
            protected void onSingleClick(View view) {
                getListener().onClickPositive(Alert.this);
            }
        });
    }

    /**
     * Configura el botón negativo.
     * @param btn Botón que se configura.
     * @param args Argumentos que contienen la configuración.
     */
    private void setNegativeButton(Button btn, Bundle args) {
        final CharSequence negativeButtonText = args.getCharSequence(ARG_NEGATIVE_BUTTON);
        if (negativeButtonText == null)
            return;
        btn.setVisibility(View.VISIBLE);
        btn.setText(negativeButtonText);
        btn.setOnClickListener(new SingleClickListener() {
            @Override
            protected void onSingleClick(View view) {
                getListener().onClickNegative(Alert.this);
            }
        });
    }

    public void setListener(AlertListener<Alert> onDismissListener) {
        this.listener = onDismissListener;
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
        getListener().onCancel(this);
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        getListener().onDismiss(this);
    }
}