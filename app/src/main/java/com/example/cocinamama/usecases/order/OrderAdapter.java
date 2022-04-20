package com.example.cocinamama.usecases.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cocinamama.R;
import com.example.cocinamama.model.Order;
import com.example.cocinamama.usecases.menuFragments.OrderFragment;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Order> orderList;
    private final Context context;
    OrderFragment orderFragment;

    public OrderAdapter(Context context, List<Order> orderList, OrderFragment orderFragment){
        this.context = context;
        this.orderList = orderList;
        this.orderFragment = orderFragment;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_order_row, null);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        final Order order = (Order) orderList.get(position);

        itemViewHolder.id.setText(String.format("P%07d",order.getId()));
        itemViewHolder.status.setText(order.getStatus());
        itemViewHolder.price.setText(order.getPrice());
        itemViewHolder.images.setImageResource(order.getImage());

        itemViewHolder.status.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View v){
                orderFragment.clickData(order);
            }
        });

    }

    @Override
    public int getItemCount(){
        return orderList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        public ImageView images;
        public TextView id;
        public TextView status;
        public TextView price;
        public LinearLayout linearLayout;

        ItemViewHolder(View itemView){
            super(itemView);

            images = (ImageView) itemView.findViewById(R.id.image_order);
            id = (TextView) itemView.findViewById(R.id.txtOrder);
            status = (TextView) itemView.findViewById(R.id.txtStatus);
            price = (TextView) itemView.findViewById(R.id.txtPrice);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.orderLayout);
        }
    }
}
