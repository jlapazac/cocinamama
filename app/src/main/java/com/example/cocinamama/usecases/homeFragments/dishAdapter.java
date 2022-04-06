package com.example.cocinamama.usecases.homeFragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocinamama.R;
import com.example.cocinamama.usecases.order.Order;
import com.example.cocinamama.usecases.order.OrderAdapter;

import java.util.List;

public class dishAdapter extends RecyclerView.Adapter<dishAdapter.ViewHolder> {
    private List<dishModel> listDishes;
    private dishAdapter.ItemClickListener itemClickListener;

    public dishAdapter(List<dishModel> listDishes, ItemClickListener itemClickListener) {

        this.listDishes = listDishes;
        this.itemClickListener = itemClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_dish,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        String dishName = listDishes.get(position).getName();
        String dishDescription = listDishes.get(position).getDescription();

        holder.name.setText(dishName);
        holder.description.setText(dishDescription);

        if (dishName.equals("Carapulcra y Sopa Seca"))
            holder.img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_carapu));
        else if(dishName.equals("Pachamanca"))
            holder.img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_pacha));
        else if(dishName.equals("Lomo Saltado"))
            holder.img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_lomo));

        holder.itemView.setOnClickListener(view -> {
            itemClickListener.onItemClick(listDishes.get(position));
        });
    }

    @Override
    public int getItemCount() {

        return listDishes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView name, description;
        private final ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtName);
            description = itemView.findViewById(R.id.txtDescription);
            img = itemView.findViewById(R.id.imgDish);
        }
    }

    public interface ItemClickListener {
        void onItemClick(dishModel dishModel);
    }
}
