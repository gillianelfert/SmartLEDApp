package de.hawhamburg.smartledapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import de.hawhamburg.smartledapp.R;

public class ProfileActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.light){
                    startActivity(new Intent(getApplicationContext(), LightActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                }
                if(item.getItemId() == R.id.alarm){
                    startActivity(new Intent(getApplicationContext(), AlarmActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                }
                return false;
            }
        });
    }
}