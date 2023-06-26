package de.hawhamburg.smartledapp.viewmodel;

import android.graphics.Color;
import android.widget.Switch;
import android.widget.TextView;

import de.hawhamburg.smartledapp.R;

public class AddEditProfileViewModel {

    public void setupTextViewColors(TextView lightModeTextView, TextView clapModeTextView, Switch modeSwitch){
        String checkedColor = "#FFBA55";
        String uncheckedColor = "#757575";
        if (modeSwitch.isChecked()){
            clapModeTextView.setTextColor(Color.parseColor(checkedColor));
            lightModeTextView.setTextColor(Color.parseColor(uncheckedColor));
        }
        else{
            lightModeTextView.setTextColor(Color.parseColor(checkedColor));
            clapModeTextView.setTextColor(Color.parseColor(uncheckedColor));
        }
    }
}
