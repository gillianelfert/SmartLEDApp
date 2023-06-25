package de.hawhamburg.smartledapp.model.alarm;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.ViewModelProvider;

import de.hawhamburg.smartledapp.MyApplication;
import de.hawhamburg.smartledapp.model.profile.Profile;
import de.hawhamburg.smartledapp.view.ProfileActivity;
import de.hawhamburg.smartledapp.viewmodel.ProfileViewModel;

public class AlertReceiver extends BroadcastReceiver {

    MyApplication myApplication;
    @Override
    public void onReceive(Context context, Intent intent) {
        myApplication = (MyApplication) context.getApplicationContext();
        Profile profile = myApplication.getProfileRepository().getActiveProfile();
        profile.setLightBrightness(0);
        myApplication.getProfileRepository().update(profile);
    }
}
