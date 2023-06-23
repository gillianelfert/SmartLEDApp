package de.hawhamburg.smartledapp.view;


import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import de.hawhamburg.smartledapp.R;

public class BottomNavigationHelper {
    public static void setupBottomNavigation(final Activity activity, BottomNavigationView bottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.light) {
                    if (!(activity instanceof LightActivity)) {
                        Intent lightIntent = new Intent(activity, LightActivity.class);
                        activity.startActivity(lightIntent);
                        activity.overridePendingTransition(0, 0);
                        activity.finish();
                    }
                    return true;
                } else if (itemId == R.id.profile) {
                    if (!(activity instanceof ProfileActivity)) {
                        Intent profileIntent = new Intent(activity, ProfileActivity.class);
                        activity.startActivity(profileIntent);
                        activity.overridePendingTransition(0, 0);
                        activity.finish();
                    }
                    return true;
                } else if (itemId == R.id.alarm) {
                    if (!(activity instanceof AlarmActivity)) {
                        Intent alarmIntent = new Intent(activity, AlarmActivity.class);
                        activity.startActivity(alarmIntent);
                        activity.overridePendingTransition(0, 0);
                        activity.finish();
                    }
                    return true;
                }

                return false;
            }
        });
    }
    public static void setSelectedMenuItem(BottomNavigationView bottomNavigationView, int menuItemId) {
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.findItem(menuItemId);
        menuItem.setChecked(true);
    }
}
