package de.hawhamburg.smartledapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import de.hawhamburg.smartledapp.R;
import de.hawhamburg.smartledapp.model.alarm.Alarms;
import de.hawhamburg.smartledapp.viewmodel.AlarmAdapter;
import de.hawhamburg.smartledapp.viewmodel.AlarmViewModel;

public class AlarmActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    AlarmViewModel alarmViewModel;
    Alarms alarms;

    AlarmAdapter alarmAdapter;

    RecyclerView alarmsRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        getSupportActionBar().hide();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.alarm);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.profile){
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                }
                if(item.getItemId() == R.id.light){
                    startActivity(new Intent(getApplicationContext(), LightActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                }
                return false;
            }
        });

        alarmViewModel = new AlarmViewModel();

        alarms = new Alarms();

        alarmsRecView = findViewById(R.id.alarmsRecView);
        alarmsRecView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        alarmAdapter = new AlarmAdapter(getApplicationContext(), alarms.getAlarms());

        alarmsRecView.setAdapter(alarmAdapter);

    }
}