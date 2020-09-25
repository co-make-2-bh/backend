package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.UserModelApplication;
import com.lambdaschool.usermodel.models.Listing;
import com.lambdaschool.usermodel.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserModelApplication.class)
public class ListingServiceImplTest {

    @Autowired
    ListingService listingService;

    @Autowired
    UserService userService;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findAll() {
        assertEquals(1, listingService.findAll().size());
    }

    @Test
    public void findListingById() {
    }

    @Test
    public void save() {

        User thisUser = new User("Ben",  "password", "ben@lambda.com");
        thisUser = userService.save(thisUser);


        Listing thisListing = new Listing("Test Listing", "Lorem Ipsum testing testing testing .", thisUser);

        Listing newListing = listingService.save(thisListing);
        assertNotNull(newListing);
        assertEquals("Test", newListing.getTitle());
        assertEquals("Ben", newListing.getUser().getUsername());
    }

    @Test
    public void delete() {
    }

    @Test
    public void deleteAll() {
    }
}