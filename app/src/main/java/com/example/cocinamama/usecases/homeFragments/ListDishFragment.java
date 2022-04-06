package com.example.cocinamama.usecases.homeFragments;


import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cocinamama.R;

import java.util.ArrayList;
import java.util.List;

public class ListDishFragment extends Fragment {

    private List<dishModel> listDishes = new ArrayList<>();
    private RecyclerView recyclerView;
    private dishAdapter dishAdapter;

    public ListDishFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_dish, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerViewDish);
        List<dishModel> listDishes = getData();
        dishAdapter = new dishAdapter(listDishes, new dishAdapter.ItemClickListener(){
            public void onItemClick(dishModel dishModel){
                findNavController(view).navigate(R.id.action_listDishFragment_to_addDishToOrderFragment);
            }
        });
        //RecyclerView.Adapter<dishAdapter.ViewHolder> adapter = new dishAdapter(listDishes);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(dishAdapter);

    }


    private List<dishModel> getData(){
        listDishes.add(new dishModel("Carapulcra y Sopa Seca","La especialidad de la casa. Carapulcra Chinchana a base de papa huayro fresca sancochada, acompañada de la rica sopa seca y 150gr de chicharrón de chancho tierno. Para chuparse los dedos."));
        listDishes.add(new dishModel("Pachamanca","Deliciosa Pachamanca a la olla con 300 gr de Panceta de Cerdo, papa yungay, camote jonathan, choclo de dientes tiernos y su incomparable queso ahumado. Combinación de sabores adictivos."));
        listDishes.add(new dishModel("Lomo Saltado","Incomparable plato típico peruano preparado con Lomo Fino, cebolladas y tomates frescos en su punto de crujiente y una cama de papás nativas fritas. Majestuoso por donde se le mire."));
        return listDishes;
    }
}