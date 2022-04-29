package com.example.cocinamama.usecases.orderdetails;

import static androidx.navigation.Navigation.findNavController;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cocinamama.R;
import com.example.cocinamama.model.OrderDetails;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDetailsFragment extends Fragment {

    public static final String TAG = "===========>";
    RequestQueue QUEUE;
    String URLHTTP;

    Bundle dataOrder;
    private List<OrderDetails> orderDetailsList = new ArrayList<>();
    private ListView listView;
    private OrderDetailsAdapter orderDetailsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_order_details, container, false);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findNavController(getView()).navigate(R.id.action_orderDetailsFragment_to_orderFragment);
            }
        });

        // Recuperar datos de la Orden
        dataOrder = getArguments();
        TextView txtOrder = view.findViewById(R.id.txtOrder);
        TextView txtOrderPriceTotal = view.findViewById(R.id.txtOrderPriceTotal);

        // Cancelar
        Button btnCancel = view.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayAlertDialog();
            }
        });


        String id = String.format("P%07d",dataOrder.getInt("id"));
        String price = String.format("S/.%s",dataOrder.getString("price"));

        txtOrder.setText(id);
        txtOrderPriceTotal.setText(price);

        // Crear Mapa
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapView);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                googleMap.getUiSettings().setZoomControlsEnabled(true);
                googleMap.setTrafficEnabled(true);

                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(-12.04592, -77.030565))
                        .title("Centro de Lima")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(-12.044956, -77.029831))
                        .title("Palacio de Gobierno"));

                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(-12.046661, -77.029544))
                        .title("Catedral"));

                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-12.04592, -77.030565), 15));

            }
        });


        listView = view.findViewById(R.id.listViewOrderDetails);
        orderDetailsAdapter = new OrderDetailsAdapter(getContext(), orderDetailsList);

        URLHTTP = getResources().getString(R.string.urlOrderDetail).concat(Integer.toString(dataOrder.getInt("id")));
        httpGET(URLHTTP);

        return view;
    }



    public void httpGET(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parsingData(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,error.toString());
            }
        }
        );
        QUEUE = Volley.newRequestQueue(getContext());
        QUEUE.add(stringRequest);
    }

    public void httpPUT(String url){
        final JSONObject body = new JSONObject();
        try {
            body.put("state", "cancel");
            body.put("descriptionstate", "Cancelado");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        StringRequest putRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG,response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,error.toString());
            }
        }
        ){
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                return body.toString().getBytes();
            }
        };
        QUEUE = Volley.newRequestQueue(getContext());
        QUEUE.add(putRequest);
    }

    public void parsingData(String json){
        try{
            JSONArray array = new JSONArray(json);

            orderDetailsList.clear();
            for(int i = 0; i < array.length(); i++){
                JSONObject inside = array.getJSONObject(i);
                int id = inside.getInt("id");
                int order_id = inside.getInt("order_id");
                int publicationdetail_id = inside.getInt("publicationdetail_id");
                String typeOrder = inside.getString("typeorder");
                String price = inside.getString("price");
                int amount = inside.getInt("amount");

                OrderDetails orderDetails = new OrderDetails(id,0,"comida",typeOrder,price,amount);
                orderDetailsList.add(orderDetails);
            }
            listView.setAdapter(orderDetailsAdapter);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    private void displayAlertDialog() {

        Context context = getContext();
        String title = "Cancelar pedido";
        String message = "¿Esta seguro de cancelar el pedido?";
        String buttonYes = "Si";
        String buttonNo = "No";

        AlertDialog.Builder ad = new AlertDialog.Builder(context);
        ad.setTitle(title);
        ad.setMessage(message);

        ad.setPositiveButton(
                buttonYes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        String url = getResources().getString(R.string.urlOrderState).concat(Integer.toString(dataOrder.getInt("id")));
                        Log.d(TAG,url);
                        httpPUT(url);
                        findNavController(getView()).navigate(R.id.action_orderDetailsFragment_to_orderDetailsCancelFragment);
                    }
                }
        );

        ad.setNegativeButton(
                buttonNo,
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int arg1) {
                        Log.d(TAG,"CANCEL");
                    }
                }
        );

        //
        ad.show();
    }

}