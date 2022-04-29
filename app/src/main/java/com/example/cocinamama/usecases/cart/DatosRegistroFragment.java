package com.example.cocinamama.usecases.cart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cocinamama.R;
import com.example.cocinamama.usecases.cart.recycler.ProductoItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatosRegistroFragment extends Fragment {

    private static final String TAG = "DatosRegistroFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button btnregistrar, btncargar;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Layout
    private TextView txtvdireccion;
    private RadioGroup rGroupAddDireccion, rGroupTipoPago;
    private RadioButton radioDireccionSi, radioDireccionNo, radioBCP, radioYAPE;
    private EditText etNewDireccion;
    private String tempDireccion;
    private String tipoPago;
    private int userId = 1;
    private String totalPedido;
    private ArrayList<ProductoItem> productosList = new ArrayList<>();

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

        // get data
        totalPedido = getArguments().getString("totalPedido");
        productosList = (ArrayList<ProductoItem>) getArguments().getSerializable("key");

        // init layout
        txtvdireccion = view.findViewById(R.id.txtvdireccion);
        etNewDireccion = view.findViewById(R.id.etNewDireccion);
        rGroupAddDireccion = view.findViewById(R.id.rGroupAddDireccion);
        rGroupTipoPago = view.findViewById(R.id.rGroupTipoPago);
        radioDireccionSi = view.findViewById(R.id.radioDireccionSi);
        radioDireccionNo = view.findViewById(R.id.radioDireccionNo);
        radioBCP = view.findViewById(R.id.radioBCP);
        radioYAPE = view.findViewById(R.id.radioYAPE);

        rGroupAddDireccion.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup group, int checkedId){

                if (checkedId==radioDireccionSi.getId()){
                    etNewDireccion.setVisibility(View.VISIBLE);
                } else {
                    etNewDireccion.setVisibility(View.GONE);
                    txtvdireccion.setText(tempDireccion);
                }
            }
        });

        rGroupTipoPago.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if (checkedId==radioBCP.getId()){
                    tipoPago = "BCP";
                } else {
                    tipoPago = "YAPE";
                }
            }
        });

        etNewDireccion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                txtvdireccion.setText(editable.toString());
            }
        });


        loadDireccion(view);
        showConfirmacionPedido(view);
        showCargarComprobante(view);

        return view;
    }

    private void loadDireccion(View view) {
        // Instantiate the RequestQueue.
        Log.e(TAG, "loadDireccion: Init Volley" );
        RequestQueue queue = Volley.newRequestQueue(view.getContext());
        String url ="http://apaza.pe:8000/address/1";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // process your response here
                        Log.e(TAG, "onResponse: " + response );

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //perform operation here after getting error
                Log.e(TAG, "onErrorResponse: " + error.getMessage() );
            }
        });
        queue.add(stringRequest);

        //JsonArrayRequest
        JsonArrayRequest bannerReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                String data = String.valueOf(obj.get("address"));
                                tempDireccion = data;
                                txtvdireccion.setText(data);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });
        queue.add(bannerReq);

    }

    private void sendDataToServer(View view){
        RequestQueue queue = Volley.newRequestQueue(view.getContext());

        final String URL = "http://apaza.pe:8000/order/";
        HashMap<String, Object> params = new HashMap<String, Object>();

        params.put("user_id", userId);
        params.put("registerdate", "2022-04-22T21:15:48.454Z");
        params.put("totalamount", Double.valueOf(totalPedido));
        params.put("address_id", 1);
        params.put("payment", tipoPago);
        params.put("state", "string");
        params.put("descriptionstate", "string");
        params.put("photo", "string");

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e(TAG, "onResponse: " + response );
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });


        final String URL1 = "http://apaza.pe:8000/address/1";
        HashMap<String, Object> paramsDireccion = new HashMap<String, Object>();

        paramsDireccion.put("city", "String");
        paramsDireccion.put("province", "String");
        paramsDireccion.put("district", "String");
        paramsDireccion.put("address", txtvdireccion.getText().toString());
        paramsDireccion.put("latitude", 0.0);
        paramsDireccion.put("longitude", 0.0);
        paramsDireccion.put("user_id", userId);

        JsonObjectRequest req1 = new JsonObjectRequest(Request.Method.PUT, URL1, new JSONObject(paramsDireccion),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e(TAG, "onResponse: " + response );
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

        // update direccion
        queue.add(req);
        queue.add(req1);

    }

    private void showConfirmacionPedido(View view) {
        btnregistrar=view.findViewById(R.id.btnregistrar);
        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(txtvdireccion.getText().toString()) ||
                        TextUtils.isEmpty(tipoPago)){
                    Toast.makeText(view.getContext(), "Selecciona tipo de pago", Toast.LENGTH_SHORT).show();
                } else {
                    sendDataToServer(view);
                    ConfirmacionPedidoFragment confirmacionPedidoFragment = new ConfirmacionPedidoFragment();

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("key", productosList);
                    bundle.putString("totalPedido", totalPedido);
                    bundle.putString("direccion", txtvdireccion.getText().toString());
                    confirmacionPedidoFragment.setArguments(bundle);

                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.navHostContainer, confirmacionPedidoFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
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


    //todo cargar direccion desde volley
    //todo fix los 2 radiogroups and set data, primer RG abro EditText para asignar nueva direccion
    //todo volley envio nueva direccion
    //todo volley envio pedido

    // siguiente fragmento cargar la informacion

}