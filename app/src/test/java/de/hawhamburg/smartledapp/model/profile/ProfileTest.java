package de.hawhamburg.smartledapp.model.profile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    public void testSetId(){
        int expectedId = 5;

        profile.setId(expectedId);
        int actualId = profile.getId();

        assertEquals(expectedId, actualId);
    }

    @Test
    public void testGetId(){
        Profile profile = new Profile("Profile", true, true,true, 100);
        int expectedId = 5;
        profile.setId(expectedId);

        int actualId = profile.getId();

        assertEquals(expectedId, actualId);
    }

    @Test
    public void testGetName() {
        String expectedName = "Test Profile";
        Profile profile = new Profile(expectedName, true, true, true, 100);
        String actualName = profile.getName();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void testIsLightIsOn() {
        Profile profile = new Profile("Test Profile", true, true, true, 100);
        boolean lightIsOn = profile.isLightIsOn();
        assertTrue(lightIsOn);
    }

    @Test
    public void testSetLightIsOn() {
        Profile profile = new Profile("Test Profile", true, true, true, 100);
        profile.setLightIsOn(false);
        boolean lightIsOn = profile.isLightIsOn();
        assertFalse(lightIsOn);
    }

    @Test
    public void testIsReactsToClap() {
        Profile profile = new Profile("Test Profile", true, true, true, 100);
        boolean reactsToClap = profile.isReactsToClap();
        assertTrue(reactsToClap);
    }

    @Test
    public void testIsStatus() {
        Profile profile = new Profile("Test Profile", true, true, true, 100);
        boolean status = profile.isStatus();
        assertTrue(status);
    }

    @Test
    public void testGetModeString_WhenReactsToClap_ReturnsClapMode() {
        Profile profile = new Profile("Test Profile", true, true, true, 100);
        String modeString = profile.getModeString();
        assertEquals("Clap Mode", modeString);
    }

    @Test
    public void testGetModeString_WhenNotReactsToClap_ReturnsLightMode() {
        Profile profile = new Profile("Test Profile", false, true, true, 100);
        String modeString = profile.getModeString();
        assertEquals("Light Mode", modeString);
    }

    @Test
    public void testGetLightBrightness() {
        int expectedBrightness = 100;
        Profile profile = new Profile("Test Profile", true, true, true, expectedBrightness);
        int actualBrightness = profile.getLightBrightness();
        assertEquals(expectedBrightness, actualBrightness);
    }

    @Test
    public void testSetLightBrightness() {
        int newBrightness = 50;
        Profile profile = new Profile("Test Profile", true, true, true, 100);
        profile.setLightBrightness(newBrightness);
        int updatedBrightness = profile.getLightBrightness();
        assertEquals(newBrightness, updatedBrightness);
    }

    @Test
    public void testGetPreviousLightBrightness() {
        int expectedPreviousBrightness = 100;
        Profile profile = new Profile("Test Profile", true, true, true, 100);
        int previousBrightness = profile.getPreviousLightBrightness();
        assertEquals(expectedPreviousBrightness, previousBrightness);
    }

    @Test
    public void testToggleMode_WhenReactsToClap_TogglesToFalse() {
        Profile profile = new Profile("Test Profile", true, true, true, 100);
        profile.toggleMode();
        assertFalse(profile.isReactsToClap());
    }

    @Test
    public void testToggleMode_WhenNotReactsToClap_TogglesToTrue() {
        Profile profile = new Profile("Test Profile", false, true, true, 100);
        profile.toggleMode();
        assertTrue(profile.isReactsToClap());
    }

    @Test
    public void testSetActive_SetsStatusToTrue() {
        Profile profile = new Profile("Test Profile", true, false, true, 100);
        profile.setActive();
        assertTrue(profile.isStatus());
    }

    @Test
    public void testSetInactive_SetsStatusToFalse() {
        Profile profile = new Profile("Test Profile", true, true, true, 100);
        profile.setInactive();
        assertFalse(profile.isStatus());
    }

    @Test
    public void testToggleLight_WhenLightIsOn_TogglesToFalse() {
        Profile profile = new Profile("Test Profile", true, true, true, 100);
        profile.toggleLight();
        assertFalse(profile.isLightIsOn());
    }

    @Test
    public void testToggleLight_WhenLightIsOff_TogglesToTrue() {
        Profile profile = new Profile("Test Profile", true, true, false, 100);
        profile.toggleLight();
        assertTrue(profile.isLightIsOn());
    }
}
