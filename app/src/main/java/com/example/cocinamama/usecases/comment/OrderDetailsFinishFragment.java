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

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        fragmentOrderDetailsFinishBinding = FragmentOrderDetailsFinishBinding.bind(view);

    }


}