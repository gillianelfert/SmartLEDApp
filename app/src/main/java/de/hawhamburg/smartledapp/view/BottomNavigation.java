package de.hawhamburg.smartledapp.view;

import android.app.Activity;
import android.content.Context;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

import de.hawhamburg.smartledapp.OnNavigationItemSelectedListener;
import de.hawhamburg.smartledapp.R;

public class BottomNavigation {
    BottomNavigationView bottomNavigationView;
    private OnNavigationItemSelectedListener listener;

    public BottomNavigation(Activity activity, OnNavigationItemSelectedListener listener) {
        this.listener = listener;

        bottomNavigationView = activity.findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (listener != null) {
                    listener.onNavigationItemSelected(item);
                }
                return true;
            }
        });
    }
}
