package com.example.cocinamama.usecases.orderdetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cocinamama.R;
import com.example.cocinamama.model.OrderDetails;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    Context context;
    List<OrderDetails> lst;

    public CustomAdapter(Context context, List<OrderDetails> lst) {
        this.context = context;
        this.lst = lst;
    }

    @Override
    public int getCount() {
        return lst.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView ImageViewProduct;
        TextView TextViewProductName;
        TextView TextViewProductDesc;
        TextView TextViewProductPrice;

        OrderDetails o = lst.get(i);

        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.listview_orderdetails, null);

        ImageViewProduct = view.findViewById(R.id.imageViewProduct);
        TextViewProductName = view.findViewById(R.id.textViewProductName);
        TextViewProductDesc = view.findViewById(R.id.textViewProductDesc);
        TextViewProductPrice = view.findViewById(R.id.textViewProductPrice);

        ImageViewProduct.setImageResource(o.imagen);
        TextViewProductName.setText(o.nombre);
        TextViewProductDesc.setText(o.desc);
        TextViewProductPrice.setText(o.precio);

        return view;
    }
}
