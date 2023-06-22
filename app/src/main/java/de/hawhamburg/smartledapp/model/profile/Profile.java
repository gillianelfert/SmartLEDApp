package de.hawhamburg.smartledapp.model.profile;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import de.hawhamburg.smartledapp.model.light.Light;

@Entity(tableName = "profile_table")
public class Profile {
    @PrimaryKey (autoGenerate = true)
    private int id;
    private String name;
    private boolean reactsToClap;
    private boolean status;
    private boolean lightIsOn;
    private int lightBrightness;

    public boolean isLightIsOn() {
        return lightIsOn;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isReactsToClap() {
        return reactsToClap;
    }

    public Profile(String name, boolean reactsToClap, boolean status, boolean lightIsOn, int lightBrightness) {
        this.name = name;
        this.reactsToClap = reactsToClap;
        this.status = status;
        this.lightIsOn = lightIsOn;
        this.lightBrightness = lightBrightness;
    }

    public boolean isStatus() {
        return status;
    }

    public String getModeString(){
        if(this.isReactsToClap()){return "Clap Mode";}
        else{return "Light Mode";}
    }

    public boolean isLightOn() {
        return lightIsOn;
    }

    public int getLightBrightness() {
        return lightBrightness;
    }

    public void toggleMode(){
        if(this.reactsToClap){reactsToClap = false;}
        else {this.reactsToClap = true;}
    }

    public void setActive(){
        this.status = true;
    }

    public void setInactive(){
        this.status = false;
    }
}
