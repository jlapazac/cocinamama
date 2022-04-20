package com.example.cocinamama.usecases.homeFragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.cocinamama.R;
import com.example.cocinamama.util.Constants;

public class AddDishToOrderFragment extends Fragment {
    private dishModel pDish;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pDish = getArguments().getParcelable(Constants.obj_dishes);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_dish_to_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = view.getContext();
        ImageView imgDish = view.findViewById(R.id.imgDishSelect);
        TextView txtTitle = view.findViewById(R.id.textTitle);
        TextView txtDescription = view.findViewById(R.id.textDescription);

        if (pDish.getTitle().equals("Carapulcra y Sopa Seca")) {
            imgDish.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_carapu));
        }
        else if (pDish.getTitle().equals("Pachamanca")){
            imgDish.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_pacha));
        }
        else {
            imgDish.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_lomo));
        }

        txtTitle.setText(pDish.getTitle());
        txtDescription.setText(pDish.getDescription());
    }
}
