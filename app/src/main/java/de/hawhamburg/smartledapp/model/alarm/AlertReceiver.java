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

    ProfileViewModel profileViewModel;
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        notificationHelper.getManager().notify(1,nb.build());

        MyApplication myApp = new MyApplication();
        Profile profile = myApp.getActiveProfile();
        profile.setActive();
        myApp.setActiveProfile(profile);
    }
}
