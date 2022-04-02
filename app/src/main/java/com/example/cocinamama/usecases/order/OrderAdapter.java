package com.example.cocinamama.usecases.order;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cocinamama.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {

    private List<Order> orderList;
    private ItemClickListener itemClickListener;

    public OrderAdapter(List<Order> orderList, ItemClickListener itemClickListener){
        this.orderList = orderList;
        this.itemClickListener = itemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView image;
        public TextView status;
        public TextView price;
        public MyViewHolder(View view){
            super(view);
            image = (ImageView) view.findViewById(R.id.image_order);
            status = (TextView) view.findViewById(R.id.text_status);
            price = (TextView) view.findViewById(R.id.text_price);
        }
    }

    public interface ItemClickListener{
        void onItemClick(Order order);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_order_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.image.setImageResource(order.getImage());
        holder.status.setText(order.getStatus());
        holder.price.setText(order.getPrice());

        holder.itemView.setOnClickListener(view -> {
            itemClickListener.onItemClick(orderList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
