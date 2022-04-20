package com.example.cocinamama.usecases.menuFragments;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cocinamama.R;
import com.example.cocinamama.usecases.order.Order;
import com.example.cocinamama.usecases.order.OrderAdapter;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment {

    private List<Order> orderList = new ArrayList<>();
    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_order, container, false);
//        Bundle bundle = this.getArguments();
//        String tst = "no";
//        if (bundle!=null){
//            tst = bundle.getString("test");
//        }
//        Toast.makeText(view.getContext(), "t: " + tst, Toast.LENGTH_SHORT).show();

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_order);
        orderAdapter = new OrderAdapter(orderList, new OrderAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Order order) {
                //showToast(order.getPrice());
                findNavController(view).navigate(R.id.action_orderFragment_to_orderDetailsFragment);
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 0));
        recyclerView.setAdapter(orderAdapter);
        GetData();

        return view;
    }

    private void showToast(String message){
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }

    private void GetData() {
        Order order = new Order(1,R.drawable.delivery, "Delivery","S/. 100.00");
        orderList.add(order);
        order = new Order(2,R.drawable.cook, "Cook","S/. 200.00");
        orderList.add(order);
        order = new Order(3,R.drawable.finish, "Finish","S/. 300.00");
        orderList.add(order);
        order = new Order(4,R.drawable.finish, "Finish","S/. 400.00");
        orderList.add(order);

        orderAdapter.notifyDataSetChanged();
    }
}