package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.exceptions.ResourceNotFoundException;
import com.lambdaschool.usermodel.models.*;
import com.lambdaschool.usermodel.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Transactional
@Service(value = "listingService")
public class ListingServiceImpl implements ListingService {

    @Autowired
    private ListingRepository listingrepos;
    @Autowired

    private HelperFunctions helperFunctions;

    @Override
    public List<Listing> findAll() {
        List<Listing> list = new ArrayList<>();
        listingrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Listing findListingById(long listingid) {
        return listingrepos.findById(listingid).orElseThrow(()-> new ResourceNotFoundException("Listing with id:" + listingid + "not found!"));
    }

    @Override
    public Listing save(Listing listing) {

        Listing newListing = new Listing();
        if(listing.getListingid() != 0 ){
            listingrepos.findById(listing.getListingid()).orElseThrow(()->
                    new ResourceNotFoundException("Listing with id:" + listing.getListingid() +
                            "not found!"));
            newListing.setListingid(listing.getListingid());
        }
        newListing.setDescription(listing.getDescription());
        newListing.setTitle(listing.getTitle());
        newListing.setUser(listing.getUser());
        return listingrepos.save(newListing);
    }

    @Override
    public void delete(long listingid) {
         listingrepos.findById(listingid).orElseThrow(()-> new ResourceNotFoundException("Listing with id:" + listingid + "not found!"));
         listingrepos.deleteById(listingid);
    }

    @Override
    public void deleteAll() {
        listingrepos.deleteAll();
    }
}
