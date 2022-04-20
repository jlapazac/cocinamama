package com.example.cocinamama.usecases.homeFragments;


import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cocinamama.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListDishFragment extends Fragment {

    //private List<dishModel> listDishes = new ArrayList<>();
    //private RecyclerView recyclerView;
    //private dishAdapter dishAdapter;
    //private Boolean flag;
    Bundle bundle;

    public ListDishFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_dish, container, false);
        //View view = inflater.inflate(R.layout.fragment_list_dish, container, false);
        //recyclerView = view.findViewById(R.id.recyclerViewDish);
        //if (flag) {
        //    listDishes = getData();
        //    flag = false;
        // }
        //dishAdapter = new dishAdapter(listDishes, new dishAdapter.ItemClickListener() {
        //        public void onItemClick(dishModel dishModel) {
        //          findNavController(view).navigate(R.id.action_listDishFragment_to_addDishToOrderFragment);
        //      }
        //});
        //    //RecyclerView.Adapter<dishAdapter.ViewHolder> adapter = new dishAdapter(listDishes);
        //recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        //recyclerView.setAdapter(dishAdapter);
        //return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //RecyclerView recyclerView = view.findViewById(R.id.recyclerViewDish);
        //List<dishModel> listDishes = getData(view);
        //RecyclerView.Adapter<dishAdapter.ViewHolder> dishAdapter = new dishAdapter(listDishes);
        //recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        //recyclerView.setAdapter(dishAdapter);
        bundle = getArguments();
        String url = "http://3.141.134.92:8000/publicationdetail/publication/" + bundle.getString("id");
        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    Log.i("======>", jsonArray.toString());

                    List<dishModel> listDish = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);

                        String title = object.getString("title");
                        String description = object.getString("description");
                        String photo = object.getString("photo");
                        listDish.add(new dishModel(title,description,photo));
                    }

                    RecyclerView recyclerView = view.findViewById(R.id.recyclerViewDish);
                    RecyclerView.Adapter<dishAdapter.ViewHolder> dishAdapter = new dishAdapter(listDish);
                    recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                    recyclerView.setAdapter(dishAdapter);

                } catch (JSONException e) {
                    Log.i("======>", e.getMessage());
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("======>", error.toString());
                    }
                }
        );
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }


    private List<dishModel> getData(View v){
        List<dishModel> listDishes = new ArrayList<>();
        //listDishes.add(new dishModel("Carapulcra y Sopa Seca","La especialidad de la casa. Carapulcra Chinchana a base de papa huayro fresca sancochada, acompañada de la rica sopa seca y 150gr de chicharrón de chancho tierno. Para chuparse los dedos."));
        //listDishes.add(new dishModel("Pachamanca","Deliciosa Pachamanca a la olla con 300 gr de Panceta de Cerdo, papa yungay, camote jonathan, choclo de dientes tiernos y su incomparable queso ahumado. Combinación de sabores adictivos."));
        //listDishes.add(new dishModel("Lomo Saltado","Incomparable plato típico peruano preparado con Lomo Fino, cebolladas y tomates frescos en su punto de crujiente y una cama de papás nativas fritas. Majestuoso por donde se le mire."));
        return listDishes;
    }
}