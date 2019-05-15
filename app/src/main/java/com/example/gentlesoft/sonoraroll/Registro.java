package com.example.gentlesoft.sonoraroll;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Registro extends AppCompatActivity implements View.OnClickListener{

    List<EditText> cajasTexto = new ArrayList<>();
    List<String> errores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        AnimatorSet as;
        ObjectAnimator animatorLayout;
        ObjectAnimator animatorLayoutAlpha;
        ObjectAnimator animatorButton;
        ObjectAnimator animatorBuAl;

        animatorLayout = ObjectAnimator.ofFloat(findViewById(R.id.layoutLinear), View.TRANSLATION_X, -5f);
        animatorLayout.setDuration(1500);
        animatorLayoutAlpha = ObjectAnimator.ofFloat(findViewById(R.id.layoutLinear), View.ALPHA, 0.0f, 1.0f);
        animatorLayoutAlpha.setDuration(1500);
        animatorButton = ObjectAnimator.ofFloat(findViewById(R.id.btnRegistro), View.TRANSLATION_X, -5f);
        animatorButton.setDuration(1500);
        animatorBuAl = ObjectAnimator.ofFloat(findViewById(R.id.btnRegistro), View.ALPHA, 0.0f, 1.0f);
        animatorBuAl.setDuration(1500);


        as = new AnimatorSet();
        as.playTogether(animatorLayout, animatorLayoutAlpha, animatorBuAl, animatorButton);
        as.start();

        for (int i = 0 ; i < ((LinearLayout)findViewById(R.id.layoutLinear)).getChildCount(); i++){
            cajasTexto.add((EditText)((LinearLayout)findViewById(R.id.layoutLinear)).getChildAt(i));
        }


        findViewById(R.id.btnRegistro).setOnClickListener(this);

    }

    public void onClick(final View v){

        switch (v.getId()){
            case R.id.btnRegistro:{
                for (EditText texto:cajasTexto){
                    if (texto.getText().toString().isEmpty()){
                        errores.add("La caja " + texto.getHint().toString() + " esta vacia, favor de llenarla");
                    }
                }
                if(((EditText)findViewById(R.id.txtTelefono)).getText().toString().length() != 10){
                    errores.add("La caja de telefono debe tener por lo menos 10 caracteres de longitud");
                }
                if (!errores.isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    String erroresCompleto = "";
                    for (String error: errores){
                        if (!error.isEmpty())
                        erroresCompleto = erroresCompleto + "\n" +error;
                        else erroresCompleto = error;
                    }
                    builder.setMessage(erroresCompleto);
                    builder.setTitle("Errores");
                    builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                    errores.clear();
                }else{
                    Intent intent = new Intent(this, SeleccionSucursal.class);
                    startActivity(intent);
                }
                break;
            }
            default:{
                break;
            }
        }

    }
}
