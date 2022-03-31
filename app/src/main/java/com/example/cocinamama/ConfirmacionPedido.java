package com.example.cocinamama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ConfirmacionPedido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion_pedido);
    }

    public void verpedidospendientes (View v){

        startActivity(new Intent(this,VerPedidosPendientes.class));
    }
}