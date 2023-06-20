package de.hawhamburg.smartledapp.model.profile;

public class Profile {
    private int id;
    private String name;
    private boolean isActive;
    private boolean reactsToClap;

    public Profile(String name, boolean isActive, boolean reactsToClap) {
        this.name = name;
        this.isActive = isActive;
        this.reactsToClap = reactsToClap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isReactsToClap() {
        return reactsToClap;
    }

    public void setReactsToClap(boolean reactsToClap) {
        this.reactsToClap = reactsToClap;
    }

    public String getMode(){
        if(this.isReactsToClap()){
            return "Clap Mode";
        }
        return "Light Mode";
    }
}
