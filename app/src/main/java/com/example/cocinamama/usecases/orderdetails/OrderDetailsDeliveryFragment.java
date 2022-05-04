package com.example.cocinamama.usecases.orderdetails;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.List;

public class OrderDetailsDeliveryFragment extends Fragment {

    public static final String TAG = "===========>";
    RequestQueue QUEUE;

    // Maps
    Double latitude;
    Double longitude;
    String address;


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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_details_delivery, container, false);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findNavController(getView()).navigate(R.id.action_orderDetailsDeliveryFragment_to_orderFragment);
            }
        });

        // Recuperar datos de la Orden
        dataOrder = getArguments();
        TextView txtOrder = view.findViewById(R.id.txtOrder);
        TextView txtOrderPriceTotal = view.findViewById(R.id.txtOrderPriceTotal);

        String id = String.format("P%07d",dataOrder.getInt("id"));
        String price = String.format("S/.%s",dataOrder.getString("price"));

        txtOrder.setText(id);
        txtOrderPriceTotal.setText(price);

        listView = view.findViewById(R.id.listViewOrderDetails);
        orderDetailsAdapter = new OrderDetailsAdapter(getContext(), orderDetailsList);
        String urlHttps = getResources().getString(R.string.urlOrderDetail).concat(Integer.toString(dataOrder.getInt("id")));
        httpGET(urlHttps);

        String urlAddress = getResources().getString(R.string.urlAddress).concat(Integer.toString(dataOrder.getInt("address_id")));
        getAddress(urlAddress);

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

    public void getAddress(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i(TAG,response);
                        parsingDataAddress(response);
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

    public void parsingDataAddress(String json){
        try{
            Log.i(TAG,json);
            JSONObject data = new JSONObject(json);

            latitude = data.getDouble("latitude");
            longitude = data.getDouble("longitude");
            address = data.getString("address");

            // Crear Mapa
            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapView);

            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(@NonNull GoogleMap googleMap) {
                    googleMap.getUiSettings().setZoomControlsEnabled(true);
                    googleMap.setTrafficEnabled(true);

                    googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(latitude, longitude))
                            .title(address));

                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 15));

                }
            });

        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}