package com.example.cocinamama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VerPedidosPendientes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pedidos_pendientes);
    }

    public void verpedidosconfirmados (View v){

        startActivity(new Intent(this,VerPedidosConfirmados.class));
    }

    public void verdetalledepedido(View v){

        startActivity(new Intent(this,VerDetalledePedido.class));
    }
}