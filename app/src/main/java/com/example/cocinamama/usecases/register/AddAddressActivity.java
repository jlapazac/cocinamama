package com.example.cocinamama.usecases.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cocinamama.MainActivity;
import com.example.cocinamama.R;
import com.example.cocinamama.databinding.FragmentHomeBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class AddAddressActivity extends AppCompatActivity {
    SupportMapFragment mapFragment;
    double latitude;
    double longitude;
    //Bundle bundle = new Bundle();
    String txtCity;
    String txtProvince;
    String txtDistrict;
    String txtAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                Log.i("======>", "estoy en OnMapReady");
                if (ActivityCompat.checkSelfPermission(AddAddressActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(AddAddressActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    Log.i("======>", "No hay permisos");
                    return;
                }else {
                    Log.i("======>", "Voy a lanzar metodo locationStart");
                    locationStart();
                }

                //googleMap.setMyLocationEnabled(true);

                googleMap.getUiSettings().setZoomControlsEnabled(true);
                googleMap.setTrafficEnabled(true);

                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(latitude, longitude))
                        .title("Mi dirección")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

                /*googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(-12.044956, -77.029831))
                        .title("Palacio de Gobierno"));

                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(-12.046661, -77.029544))
                        .title("Catedral"));

                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-12.04592, -77.030565), 15));*/
            }
        });


        final String[] city = new String[] { "Seleccione una ciudad","Lima" };
        ArrayAdapter<String> adaptadorCity = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, city);
        adaptadorCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner comboCity = (Spinner) findViewById(R.id.spCity); comboCity.setAdapter(adaptadorCity);
        comboCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
        //        Toast.makeText(AddAddressActivity.this, "Seleccionado: " + city[position], Toast.LENGTH_LONG).show();
            txtCity = city[position].toString();
           }
          public void onNothingSelected(AdapterView<?> parent) {
        //      Toast.makeText(AddAddressActivity.this, "No ha seleccionado", Toast.LENGTH_LONG).show();
          }
        });
        final String[] province = new String[] { "Seleccione una provincia","Lima" };
        ArrayAdapter<String> adaptadorProvince = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, province);
        adaptadorProvince.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner comboProvince = (Spinner) findViewById(R.id.spProvince); comboProvince.setAdapter(adaptadorProvince);
        comboProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
                //        Toast.makeText(AddAddressActivity.this, "Seleccionado: " + city[position], Toast.LENGTH_LONG).show();
                txtProvince = province[position].toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
                //      Toast.makeText(AddAddressActivity.this, "No ha seleccionado", Toast.LENGTH_LONG).show();
            }
        });

        final String[] distric = new String[] { "Seleccione un distrito","Barranco","Chorrillos","Miraflores","San Borja", "Santiago de Surco" };
        ArrayAdapter<String> adaptadorDistric = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, distric);
        adaptadorDistric.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner comboDistric = (Spinner) findViewById(R.id.spDistric); comboDistric.setAdapter(adaptadorDistric);
        comboDistric.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
                //        Toast.makeText(AddAddressActivity.this, "Seleccionado: " + city[position], Toast.LENGTH_LONG).show();
                txtDistrict = distric[position].toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
                //      Toast.makeText(AddAddressActivity.this, "No ha seleccionado", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void locationStart() {
        Log.i("======>", "entré metodo locationStart");
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localization Local = new Localization();
        Local.setAddAddressActivity(this);
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) Local);
        /*mensaje1.setText("Localización agregada");
        mensaje2.setText("");*/
    }


    public void goRegister(View view) {
        TextView textViewAddress = AddAddressActivity.this.findViewById(R.id.editTextAddress);
        txtAddress = (String) textViewAddress.getText();
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra("city",txtCity);
        intent.putExtra("province",txtProvince);
        intent.putExtra("district",txtDistrict);
        intent.putExtra("address",txtAddress);
        //startActivity(new Intent(this, RegisterActivity.class));
        startActivity(intent);
    }

    public void setLocation(Location location) {
        //Obtener la direccion de la calle a partir de la latitud y la longitud
        if (location.getLatitude() != 0.0 && location.getLongitude() != 0.0) {
            try {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        location.getLatitude(), location.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);
                    TextView txtAddress = AddAddressActivity.this.findViewById(R.id.editTextAddress);
                    txtAddress.setText(DirCalle.getAddressLine(0).toString());
                    Toast toast = Toast.makeText(this,"Mi dirección es" + DirCalle.getAddressLine(0), Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    /*mensaje2.setText("Mi direccion es: \n"
                            + DirCalle.getAddressLine(0));*/
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}