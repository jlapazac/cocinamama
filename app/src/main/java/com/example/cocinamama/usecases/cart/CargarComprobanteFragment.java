package com.example.cocinamama.usecases.cart;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.cocinamama.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CargarComprobanteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CargarComprobanteFragment extends Fragment {
    private Button btnCargarImg;
    private ImageView myImageViewCargar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CargarComprobanteFragment() {
        // Required empty public constructor
    }

    public static CargarComprobanteFragment newInstance(String param1, String param2) {
        CargarComprobanteFragment fragment = new CargarComprobanteFragment();
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
        View view= inflater.inflate(R.layout.fragment_cargar_comprobante, container, false);
        myImageViewCargar=view.findViewById(R.id.myImageViewCargar);
        btnCargarImg=view.findViewById(R.id.btnCargarImg);
        btnCargarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarComprobante();
            }
        });
        return view;
    }

    private void cargarComprobante() {
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione la app"),10);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Uri path=data.getData();
            myImageViewCargar.setImageURI(path);
        }
    }
}