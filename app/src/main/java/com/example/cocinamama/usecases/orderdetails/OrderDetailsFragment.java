package com.example.cocinamama.usecases.orderdetails;

import static androidx.navigation.Navigation.findNavController;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cocinamama.R;
import com.example.cocinamama.databinding.FragmentOrderDetailsBinding;
import com.example.cocinamama.model.OrderDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsFragment extends Fragment {

    Bundle dataOrder;
    public static final String TAG = "===========>";
    private FragmentOrderDetailsBinding fragmentOrderDetailsBinding;
    private ListView listView;
    private List<OrderDetails> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_order_details, container, false);

        dataOrder = getArguments();
        TextView txtOrder = view.findViewById(R.id.txtOrder);
        String id = Integer.toString(dataOrder.getInt("id"));
        txtOrder.setText(id);

        listView = view.findViewById(R.id.listViewOrderDetails);
        CustomAdapter adapter = new CustomAdapter(getActivity(), GetData());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                OrderDetails o = list.get(i);
                Toast.makeText(getActivity(), o.nombre, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        fragmentOrderDetailsBinding = FragmentOrderDetailsBinding.bind(view);

        if(dataOrder == null){
            Log.d(TAG,"SIN DATOS");
            return;
        }
        String status = dataOrder.getString("status");
        Log.d(TAG,status);
        fragmentOrderDetailsBinding.btnCancel.setOnClickListener(v ->{
            DialogFragment dialogFragment = new DialogFragment();
            dialogFragment.show(getFragmentManager(),"OrderDetailsCancelPopupFragment");
        });
    }

    private List<OrderDetails> GetData() {
        list = new ArrayList<>();
        list.add(new OrderDetails(1,R.drawable.shopping, "Arroz con pollo","3x1","S/. 100.00"));
        list.add(new OrderDetails(1,R.drawable.shopping, "Lomo Saltado","3x1","S/. 200.00"));
        list.add(new OrderDetails(1,R.drawable.shopping, "Sopa Seca","3x1","S/. 300.00"));
        list.add(new OrderDetails(1,R.drawable.shopping, "Cau Cau","3x1","S/. 400.00"));

        return list;
    }

    public void httpGET(String url){

    }

    public void parsingData(String json){
        try{
            JSONObject obj = new JSONObject(json);
            JSONArray array = obj.getJSONArray("orderdetail");

            for(int i = 0; i < array.length(); i++){
                JSONObject inside = array.getJSONObject(i);

                String image = inside.getString("");
                String foto = inside.getString("");
                String pedido = inside.getString("");

            }

        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    public void clickData(String data){
        Toast.makeText(getContext(), data, Toast.LENGTH_SHORT).show();
    }
}