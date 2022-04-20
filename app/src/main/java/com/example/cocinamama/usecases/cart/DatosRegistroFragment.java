package com.example.cocinamama.usecases.cart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cocinamama.R;

public class DatosRegistroFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button btnregistrar, btncargar;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DatosRegistroFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DatosRegistroFragment newInstance(String param1, String param2) {
        DatosRegistroFragment fragment = new DatosRegistroFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_datos_registro, container, false);
        showConfirmacionPedido(view);
        showCargarComprobante(view);

        return view;
    }

    private void showConfirmacionPedido(View view) {
        btnregistrar=view.findViewById(R.id.btnregistrar);
        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConfirmacionPedidoFragment confirmacionPedidoFragment = new ConfirmacionPedidoFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.navHostContainer, confirmacionPedidoFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    private void showCargarComprobante(View view) {
        btncargar=view.findViewById(R.id.btncargar);
        btncargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CargarComprobanteFragment cargarComprobanteFragment = new CargarComprobanteFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.navHostContainer, cargarComprobanteFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }
}