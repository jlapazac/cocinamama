package com.example.cocinamama.usecases.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cocinamama.R;

public class AddAddressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        final String[] city = new String[] { "Seleccione una ciudad","Lima" };
        ArrayAdapter<String> adaptadorCity = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, city);
        adaptadorCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner comboCity = (Spinner) findViewById(R.id.spCity); comboCity.setAdapter(adaptadorCity);
        //comboCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        //public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
        //        Toast.makeText(AddAddressActivity.this, "Seleccionado: " + city[position], Toast.LENGTH_LONG).show();
        //   }
        //  public void onNothingSelected(AdapterView<?> parent) {
        //      Toast.makeText(AddAddressActivity.this, "No ha seleccionado", Toast.LENGTH_LONG).show();
        //  }
        //});
        final String[] province = new String[] { "Seleccione una provincia","Lima" };
        ArrayAdapter<String> adaptadorProvince = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, city);
        adaptadorProvince.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner comboProvince = (Spinner) findViewById(R.id.spProvince); comboProvince.setAdapter(adaptadorProvince);

        final String[] distric = new String[] { "Seleccione un distrito","Barranco","Chorrillos","Miraflores","San Borja", "Santiago de Surco" };
        ArrayAdapter<String> adaptadorDistric = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, distric);
        adaptadorDistric.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner comboDistric = (Spinner) findViewById(R.id.spDistric); comboDistric.setAdapter(adaptadorDistric);
    }

    public void goRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}