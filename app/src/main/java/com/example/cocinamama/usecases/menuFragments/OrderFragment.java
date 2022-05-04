package com.example.cocinamama.usecases.menuFragments;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cocinamama.R;
import com.example.cocinamama.model.Order;
import com.example.cocinamama.usecases.order.OrderAdapter;
import com.example.cocinamama.usecases.orderdetails.OrderDetailsFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment {

    public static final String TAG = "===========>";
    RequestQueue QUEUE;

    Bundle dataOrder = new Bundle();
    private List<Order> orderList = new ArrayList<>();
    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_order, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_order);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 0));

        orderAdapter = new OrderAdapter(getContext(), orderList, this);
        QUEUE = Volley.newRequestQueue(getContext());
        String urlHttps = getResources().getString(R.string.urlOrderByUser).concat("1");
        Log.i(TAG,urlHttps);
        httpGET(urlHttps);

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
        QUEUE.add(stringRequest);
    }

    public void parsingData(String json){
        try{
            JSONArray array = new JSONArray(json);

            orderList.clear();
            for(int i = 0; i < array.length(); i++){
                JSONObject inside = array.getJSONObject(i);

                int id = inside.getInt("id");
                int image = 0;
                String status = inside.getString("state");
                String price = inside.getString("totalamount");
                dataOrder.putInt("address_id",inside.getInt("address_id"));

                switch (status){
                    case "order": image = R.drawable.order;
                        break;
                    case "cook": image = R.drawable.cook;
                        break;
                    case "delivery": image = R.drawable.delivery;
                        break;
                    case "finish": image = R.drawable.finish;
                        break;
                    case "cancel": image = R.drawable.cancel;
                        break;
                }

                Order order = new Order(id, image, status, price);
                orderList.add(order);
            }

            recyclerView.setAdapter(orderAdapter);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    public void clickData(Order data){
        dataOrder.putInt("id",data.getId());
        dataOrder.putInt("image",data.getImage());
        dataOrder.putString("status",data.getStatus());
        dataOrder.putString("price",data.getPrice());

        switch (data.getStatus()){
            case "order": findNavController(getView()).navigate(R.id.action_orderFragment_to_orderDetailsFragment, dataOrder);
                break;
            case "cook": findNavController(getView()).navigate(R.id.action_orderFragment_to_orderDetailsCookFragment, dataOrder);
                break;
            case "delivery": findNavController(getView()).navigate(R.id.action_orderFragment_to_orderDetailsDeliveryFragment, dataOrder);
                break;
            case "finish": findNavController(getView()).navigate(R.id.action_orderFragment_to_orderDetailsFinishFragment, dataOrder);
                break;
            case "cancel": findNavController(getView()).navigate(R.id.action_orderFragment_to_orderDetailsCancelFragment, dataOrder);
                break;
        }
        Toast.makeText(getContext(), data.getStatus(), Toast.LENGTH_SHORT).show();
    }
}