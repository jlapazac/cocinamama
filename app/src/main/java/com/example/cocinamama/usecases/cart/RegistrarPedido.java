package com.example.cocinamama.usecases.cart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cocinamama.R;

public class RegistrarPedido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_pedido);
    }

    public void registrarpedido (View v){

        startActivity(new Intent(this,ConfirmacionPedido.class));
    }
}