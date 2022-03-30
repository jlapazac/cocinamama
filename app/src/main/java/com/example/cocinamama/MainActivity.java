package com.example.cocinamama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.cocinamama.menuFragments.CartFragment;
import com.example.cocinamama.menuFragments.HomeFragment;
import com.example.cocinamama.menuFragments.OfferFragment;
import com.example.cocinamama.menuFragments.OrderFragment;
import com.example.cocinamama.menuFragments.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_wrapper, new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = null;

            switch (item.getItemId()){
                case R.id.home:
                    fragment = new HomeFragment();
                    break;
                case R.id.order:
                    fragment = new OrderFragment();
                    break;
                case R.id.user:
                    fragment = new UserFragment();
                    break;
                case R.id.cart:
                    fragment = new CartFragment();
                    break;
                case R.id.offer:
                    fragment = new OfferFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_wrapper,fragment).commit();

            return true;
        }
    };
}