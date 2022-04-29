package com.example.cocinamama.usecases.cart.recycler;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocinamama.R;

import java.util.ArrayList;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolderRptas>{

    private static final String TAG = "TemasAdpater";

    private ArrayList<ProductoItem> arrayList;
    private Context context;

    public ProductoAdapter(ArrayList<ProductoItem> arrayList, Context context){
        this.arrayList = arrayList;
        this.context = context;

    }

    @NonNull
    @Override
    public ProductoAdapter.ViewHolderRptas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pedido,parent,false);
        return new ProductoAdapter.ViewHolderRptas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoAdapter.ViewHolderRptas holder, int position) {
        String desc = arrayList.get(position).getDesc();
        String precio = "S/. " + String.valueOf(arrayList.get(position).getPrecio());
        holder.tvDesc.setText(desc);
        holder.tvPrecio.setText(precio);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolderRptas extends RecyclerView.ViewHolder {

        TextView tvDesc, tvPrecio;

        public ViewHolderRptas(@NonNull View itemView) {
            super(itemView);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);

        }

    }

    public ArrayList<ProductoItem> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<ProductoItem> arrayList) {
        this.arrayList = arrayList;
    }

}