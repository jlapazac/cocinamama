package com.example.cocinamama.usecases.orderdetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.cocinamama.R;

public class OrderDetailsCancelPopupFragment extends DialogFragment {
    private static final String TAG = "DialogCancel";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_order_details_cancel_popup, container,  false);

        return view;
    }
}