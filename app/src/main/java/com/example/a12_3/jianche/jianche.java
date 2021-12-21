package com.example.a12_3.jianche;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.a12_3.R;
import com.example.a12_3.util.BaseActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class jianche extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jianche);
        BottomNavigationView nav= findViewById(R.id.jianche_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_jianche_2, R.id.navigation_jianche_3,R.id.navigation_jianche_4)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.jianche_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(nav, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
