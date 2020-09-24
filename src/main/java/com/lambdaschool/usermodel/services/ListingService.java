package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.models.Listing;

import java.util.List;

public interface ListingService {

    List<Listing> findAll();
    Listing findListingById(long listingid);
    Listing save(Listing listing);
    void delete(long listingid);
    void deleteAll();
}
