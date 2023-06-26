package de.hawhamburg.smartledapp.viewmodel;

import android.app.Application;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.hawhamburg.smartledapp.model.profile.Profile;

public class ProfileViewModelTest extends TestCase {

    private Application application;
    private ProfileViewModel profileViewModel;
    @Before
    public void setUp(){
        application = Mockito.mock();
        profileViewModel = new ProfileViewModel(application);

    }

    @Test
    public void testInsert() {
        profileViewModel.insert(new Profile("Test",true,true,true,100));
        Assert.assertEquals(1,profileViewModel.getAllProfiles().getValue().size());
    }
    @Test
    public void testUpdate() {
        Profile profile = new Profile("Test",true,true,true,100);
        profileViewModel.insert(profile);
        profile.setInactive();
        profileViewModel.update(profile);
        assertEquals(profileViewModel.getAllProfiles().getValue().get(1),profile);
    }
    @Test
    public void testDelete() {
        Profile profile = new Profile("Test",true,true,true,100);

        profileViewModel.insert(profile);
        profileViewModel.delete(profile);
        assertEquals(0,profileViewModel.getAllProfiles().getValue().size());
    }
    @Test
    public void testGetAllProfiles() {
        profileViewModel.insert(new Profile("Test",true,true,true,100));
        profileViewModel.insert(new Profile("Test2",true,true,true,100));
        profileViewModel.insert(new Profile("Test3",true,true,true,100));

        assertEquals(3,profileViewModel.getAllProfiles().getValue().size());
        
    }
    @Test
    public void testGetProfileWithID() {
        Profile profile = new Profile("Test",true,true,true,100);

        profileViewModel.insert(profile);
        assertEquals(profile,profileViewModel.getProfileWithID(1));
    }
}