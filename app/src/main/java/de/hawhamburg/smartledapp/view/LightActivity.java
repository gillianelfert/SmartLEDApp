package de.hawhamburg.smartledapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import de.hawhamburg.smartledapp.R;
import de.hawhamburg.smartledapp.model.mqtt.CalculationClass;
import de.hawhamburg.smartledapp.viewmodel.LightViewModel;

public class LightActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    CalculationClass calculationClass = new CalculationClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //connectToMQTT();

        setContentView(R.layout.activity_light);

        getSupportActionBar().hide();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.light);
        BottomNavigationHelper.setupBottomNavigation(this, bottomNavigationView);
    }

    private void connectToMQTT() {
        try {
            calculationClass.mqttConnect();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}