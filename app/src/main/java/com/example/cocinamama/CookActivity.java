package com.example.cocinamama;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cocinamama.orderdetails.CustomAdapter;
import com.example.cocinamama.orderdetails.OrderDetails;

import java.util.ArrayList;
import java.util.List;

public class CookActivity extends AppCompatActivity {

    ListView ListViewOrderDetails;
    List<OrderDetails> lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook);

        ListViewOrderDetails = findViewById(R.id.listViewOrderDetails);

        CustomAdapter adapter = new CustomAdapter(this, GetData());
        ListViewOrderDetails.setAdapter(adapter);

        ListViewOrderDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                OrderDetails o = lst.get(i);
                Toast.makeText(getBaseContext(), o.nombre, Toast.LENGTH_SHORT).show();
            }
        });

        ListViewOrderDetails = findViewById(R.id.listViewOrderDetails);
    }

    private List<OrderDetails> GetData() {
        lst = new ArrayList<>();
        lst.add(new OrderDetails(1,R.drawable.shopping, "Arroz con pollo","3x1","S/. 100.00"));
        lst.add(new OrderDetails(1,R.drawable.shopping, "Lomo Saltado","3x1","S/. 200.00"));
        lst.add(new OrderDetails(1,R.drawable.shopping, "Sopa Seca","3x1","S/. 300.00"));
        lst.add(new OrderDetails(1,R.drawable.shopping, "Cau Cau","3x1","S/. 400.00"));

        return lst;
    }

}