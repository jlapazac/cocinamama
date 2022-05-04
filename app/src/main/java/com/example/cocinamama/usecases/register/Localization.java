package com.example.cocinamama.usecases.register;

import android.location.Location;
import android.location.LocationListener;

import androidx.annotation.NonNull;

public class Localization implements LocationListener {
    AddAddressActivity addAddressActivity;
    public AddAddressActivity getMainActivity() {
        return addAddressActivity;
    }
    public void setAddAddressActivity(AddAddressActivity addAddressActivity) {
        this.addAddressActivity = addAddressActivity;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        location.getLatitude();
        location.getLongitude();

        String Text = "Mi ubicacion actual es: " + "\n Lat = "
                + location.getLatitude() + "\n Long = " + location.getLongitude();
        //mensaje1.setText(Text);
        this.addAddressActivity.setLocation(location);
    }
}
