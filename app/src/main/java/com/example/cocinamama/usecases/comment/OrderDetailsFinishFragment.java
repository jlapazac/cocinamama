package com.example.cocinamama.usecases.comment;

import static androidx.navigation.Navigation.findNavController;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
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
import com.example.cocinamama.databinding.FragmentOrderDetailsFinishBinding;
import com.example.cocinamama.model.OrderDetails;
import com.example.cocinamama.usecases.orderdetails.OrderDetailsAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsFinishFragment extends Fragment {

    public static final String TAG = "===========>";
    RequestQueue QUEUE;
    Bundle dataOrder;

    // Maps
    Double latitude;
    Double longitude;
    String address;

    // Objects
    TextView txtOrder;
    TextView txtOrderPriceTotal;
    RatingBar ratingBar;
    ImageButton btnComment;
    EditText txtComment;

    private List<OrderDetails> orderDetailsList = new ArrayList<>();
    private ListView listView;
    private OrderDetailsAdapter orderDetailsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_details_finish, container, false);

        // Back to order
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findNavController(getView()).navigate(R.id.action_orderDetailsFinishFragment_to_orderFragment);
            }
        });

        // Get Argument - Id of order
        dataOrder = getArguments();

        // Get order
        String urlHttps = getResources().getString(R.string.urlOrder).concat(Integer.toString(dataOrder.getInt("id")));
        getOrder(urlHttps);

        // Get Object of layout
        txtOrder = view.findViewById(R.id.txtOrder);
        txtOrderPriceTotal = view.findViewById(R.id.txtOrderPriceTotal);
        ratingBar = view.findViewById(R.id.ratingBar);
        btnComment = view.findViewById(R.id.btnComment);

        // Update Start
        ratingBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    String url = getResources().getString(R.string.urlOrder).concat(Integer.toString(dataOrder.getInt("id")));
                    putOrder(url, "star");
                }
                return false;
            }
        });

        // Update Comment
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayAlertDialog();
            }
        });

        listView = view.findViewById(R.id.listViewOrderDetails);
        orderDetailsAdapter = new OrderDetailsAdapter(getContext(), orderDetailsList);
        String urlOrderDetail = getResources().getString(R.string.urlOrderDetail).concat(Integer.toString(dataOrder.getInt("id")));
        getOrderDetail(urlOrderDetail);

        String urlAddress = getResources().getString(R.string.urlAddress).concat(Integer.toString(dataOrder.getInt("address_id")));
        getAddress(urlAddress);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
    }

    public void putOrder(String url, String update){
        final JSONObject body = new JSONObject();
        try {
            if (update == "star"){
                body.put("star", ratingBar.getRating());
            }else if(update == "comment"){
                body.put("comment", txtComment.getText());
            }
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

    public void getOrder(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parsingDataOrder(response);
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

    public void getOrderDetail(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parsingDataOrderDetail(response);
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

    public void parsingDataOrder(String json){
        try{
            Log.i(TAG,json);
            JSONObject data = new JSONObject(json);

            String id = String.format("P%07d",data.getInt("id"));
            String price = String.format("S/.%s",data.getDouble("totalamount"));
            int star = data.getInt("star");

            txtOrder.setText(id);
            txtOrderPriceTotal.setText(price);
            ratingBar.setRating(star);

        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    public void parsingDataOrderDetail(String json){
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
        String title = "Comentario";
        String buttonYes = "Si";
        String buttonNo = "No";

        AlertDialog.Builder ad = new AlertDialog.Builder(context);
        ad.setTitle(title);

        txtComment = new EditText(getContext());
        ad.setView(txtComment);

        ad.setPositiveButton(
                buttonYes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        String url = getResources().getString(R.string.urlOrder).concat(Integer.toString(dataOrder.getInt("id")));
                        Log.d(TAG,url);
                        putOrder(url,"comment");
                        //findNavController(getView()).navigate(R.id.action_orderDetailsFragment_to_orderDetailsCancelFragment);
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