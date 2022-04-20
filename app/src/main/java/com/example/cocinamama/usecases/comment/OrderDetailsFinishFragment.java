package com.example.cocinamama.usecases.comment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cocinamama.R;
import com.example.cocinamama.databinding.FragmentOrderDetailsFinishBinding;
import com.example.cocinamama.model.OrderDetails;
import com.example.cocinamama.usecases.orderdetails.CustomAdapter;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsFinishFragment extends Fragment {

    private FragmentOrderDetailsFinishBinding fragmentOrderDetailsFinishBinding;
    private ListView listView;
    private List<OrderDetails> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_details_finish, container, false);

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
        fragmentOrderDetailsFinishBinding = FragmentOrderDetailsFinishBinding.bind(view);

    }

    private List<OrderDetails> GetData() {
        list = new ArrayList<>();
        list.add(new OrderDetails(1,R.drawable.shopping, "Arroz con pollo","3x1","S/. 100.00"));
        list.add(new OrderDetails(1,R.drawable.shopping, "Lomo Saltado","3x1","S/. 200.00"));
        list.add(new OrderDetails(1,R.drawable.shopping, "Sopa Seca","3x1","S/. 300.00"));
        list.add(new OrderDetails(1,R.drawable.shopping, "Cau Cau","3x1","S/. 400.00"));

        return list;
    }
}