package com.example.cocinamama.usecases.orderdetails;

import static androidx.navigation.Navigation.findNavController;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cocinamama.R;
import com.example.cocinamama.databinding.FragmentOrderDetailsCancelPopupBinding;

public class OrderDetailsCancelPopupFragment extends DialogFragment {

    private FragmentOrderDetailsCancelPopupBinding fragmentOrderDetailsCancelPopupBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_details_cancel_popup, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        fragmentOrderDetailsCancelPopupBinding = FragmentOrderDetailsCancelPopupBinding.bind(view);

        fragmentOrderDetailsCancelPopupBinding.btnPopupCancelSi.setOnClickListener(v ->{
            findNavController(view).navigate(R.id.action_orderDetailsCancelPopupFragment_to_orderDetailsCancelFragment);
        });

        fragmentOrderDetailsCancelPopupBinding.btnPopupCancelNo.setOnClickListener(v ->{
            findNavController(view).navigate(R.id.action_orderDetailsCancelPopupFragment_to_orderDetailsFragment);
        });
    }
}