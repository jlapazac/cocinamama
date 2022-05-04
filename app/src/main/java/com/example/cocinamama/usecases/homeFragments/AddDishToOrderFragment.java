package com.example.cocinamama.usecases.homeFragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.cocinamama.R;
import com.example.cocinamama.usecases.homeFragments.DAO.CartDAO;
import com.example.cocinamama.usecases.homeFragments.DAO.DAOException;
import com.example.cocinamama.usecases.register.RegisterActivity;
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

        if (pDish.getPhoto().equals("ic_carapu")) {
            imgDish.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_carapu));
        }
        else if (pDish.getPhoto().equals("ic_pacha")){
            imgDish.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_pacha));
        }
        else if (pDish.getPhoto().equals("ic_lomo")){
            imgDish.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_lomo));
        }

        txtTitle.setText(pDish.getTitle());
        txtDescription.setText(pDish.getDescription());
    }

    public void addCart(View view){
        EditText amountTxt = (EditText) view.findViewById(R.id.editTextQuantity);
        RadioButton rb_taypa = (RadioButton) view.findViewById(R.id.rb_taypa);

        Integer publicationdetail_id = pDish.getId();
        String typeorder = null;
        Number price = pDish.getPrice();
        if (rb_taypa.isChecked()){
            price = price.doubleValue() * 1.25;
            typeorder = "taypa";
        } else {
            typeorder = "estandar";
        }

        Integer amount = Integer.parseInt(amountTxt.toString());
        CartDAO cartDAO = new CartDAO(getContext());
        try {
        cartDAO.insertar(1,publicationdetail_id,typeorder,price,amount);
            Toast toast = Toast.makeText(getActivity(),"Se agregó lo indicado al carrito de compra", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } catch (DAOException e) {
            e.printStackTrace();
            Log.i("addCart", "====> " + e.getMessage());
        }
    }
}
