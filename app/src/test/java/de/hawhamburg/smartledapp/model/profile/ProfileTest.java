package de.hawhamburg.smartledapp.model.profile;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProfileTest {

    private Profile profile;

    @Before
    public void setUp() {
        String name = "TestProfile";
        boolean reactsToClap = true;
        boolean status = true;
        boolean lightIsOn = true;
        int lightBrightness = 100;

        profile = new Profile(name, reactsToClap, status, lightIsOn, lightBrightness);
    }

    @Test
    public void testProfileInitialization() {
        String name = "TestProfile";
        Assert.assertEquals(name, profile.getName());
    }

    @Test
    public void testProfileToggleMode() {
        profile.toggleMode();
        Assert.assertFalse(profile.isReactsToClap());
    }

    @Test
    public void testProfileSetActive() {
        profile.setActive();
        Assert.assertTrue(profile.isStatus());
    }

    @Test
    public void testProfileSetInactive() {
        profile.setInactive();
        Assert.assertFalse(profile.isStatus());
    }
}
