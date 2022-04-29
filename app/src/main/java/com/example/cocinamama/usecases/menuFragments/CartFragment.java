package com.example.cocinamama.usecases.menuFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.cocinamama.R;
import com.example.cocinamama.usecases.cart.DatosRegistroFragment;
import com.example.cocinamama.usecases.cart.recycler.ProductoAdapter;
import com.example.cocinamama.usecases.cart.recycler.ProductoItem;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    private TextView tv_pedidos;
    private Button btnSiguiente;


    private String pedidos[]={"Carapulcra y Sopa Seca S/.240" , "ssssss S/3.55"};


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Recycler
    private RecyclerView recyclerView;
    private ProductoAdapter productoAdapter;
    private ArrayList<ProductoItem> productosList = new ArrayList<>();
    private TextView totalPedido;
    private int intTotalPedido = 0;

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

        totalPedido = view.findViewById(R.id.textView6);
        tv_pedidos=view.findViewById(R.id.tv_pedidos);
        recyclerView =view.findViewById(R.id.lv_pedidos);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        initProductos();
//        ArrayAdapter <String> adapter=new ArrayAdapter<String>(getActivity(),R.layout.list_pedidos_, pedidos);
        productoAdapter = new ProductoAdapter(productosList, view.getContext());
        recyclerView.setAdapter(productoAdapter);
        return view;

    }

    private void initProductos() {
        productosList.add(new ProductoItem("Carapulcra con Sopa Seca", 50));
        productosList.add(new ProductoItem("Aji de Gallina", 95));
        productosList.add(new ProductoItem("Pachamanca", 65));
        productosList.add(new ProductoItem("Arroz con Pato", 80));

        int totalProd = 0;

        for (ProductoItem productoItem : productosList){
            totalProd+=productoItem.getPrecio();
        }
        intTotalPedido = totalProd;
        String total = "S/. " + totalProd;
        totalPedido.setText(total);


    }

    private void showDatosRegistro(View view) {
        btnSiguiente=view.findViewById(R.id.btnSiguiente);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatosRegistroFragment datosRegistroFragment = new DatosRegistroFragment();

                Bundle bundle = new Bundle();
                bundle.putString("totalPedido", String.valueOf(intTotalPedido));
                bundle.putSerializable("key", productosList);

                datosRegistroFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.navHostContainer, datosRegistroFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

}