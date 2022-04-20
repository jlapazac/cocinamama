package com.example.cocinamama.usecases.menuFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cocinamama.R;
import com.example.cocinamama.usecases.cart.DatosRegistroFragment;

public class CartFragment extends Fragment {
    private TextView tv_pedidos;
    private ListView lv_pedidos;
    private Button btnSiguiente;


    private String pedidos[]={"Carapulcra y Sopa Seca S/.240" , "ssssss S/3.55"};



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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
        View view= inflater.inflate(R.layout.fragment_cart, container, false);
        showDatosRegistro(view);

        tv_pedidos=view.findViewById(R.id.tv_pedidos);
        lv_pedidos=view.findViewById(R.id.lv_pedidos);
        ArrayAdapter <String> adapter=new ArrayAdapter<String>(getActivity(),R.layout.list_pedidos_, pedidos);
        lv_pedidos.setAdapter(adapter);
        return view;
    }

    private void showDatosRegistro(View view) {
        btnSiguiente=view.findViewById(R.id.btnSiguiente);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatosRegistroFragment datosRegistroFragment = new DatosRegistroFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.navHostContainer, datosRegistroFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

}