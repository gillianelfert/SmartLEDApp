package de.hawhamburg.smartledapp.viewmodel;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hawhamburg.smartledapp.R;
import de.hawhamburg.smartledapp.model.alarm.Alarm;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {
    private List<Alarm> alarms;
    Context context;

    public AlarmAdapter(Context context, List<Alarm> alarms) {
        this.alarms = alarms;
    }

    @NonNull
    @Override
    public AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.alarms_list_item, parent, false);
        return new AlarmAdapter.AlarmViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmAdapter.AlarmViewHolder holder, int position) {
        Alarm alarm = alarms.get(position);
        holder.timeTextView.setText(alarm.getTime().toString());
        holder.alarmSwitch.setChecked(alarm.isActive());
    }

    @Override
    public int getItemCount() {
        return alarms.size();
    }

    public static class AlarmViewHolder extends RecyclerView.ViewHolder {
        public TextView timeTextView;
        public Switch alarmSwitch;

        public AlarmViewHolder(View itemView) {
            super(itemView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            alarmSwitch = itemView.findViewById(R.id.alarmSwitch);
        }
    }
}
