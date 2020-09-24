package com.lambdaschool.usermodel.controllers;


import com.lambdaschool.usermodel.models.Listing;
import com.lambdaschool.usermodel.services.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/listings")
public class ListingController {
    @Autowired
    private ListingService listingService;
    @GetMapping(value="/listings", produces = "application/json")
    public ResponseEntity<?> listAllListings(){
        List<Listing> myList = listingService.findAll();
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }
    @GetMapping(value = "/list/id/{listingid}", produces = "application/json")
    public ResponseEntity<?> findListingById(@PathVariable long listingid){
        Listing l = listingService.findListingById(listingid);
        return new ResponseEntity<>(l,HttpStatus.OK);
    }
    @DeleteMapping(value = "/listing/{listingid}")
    public ResponseEntity<?> deleteByListingId(
            @PathVariable
                    long listingid)
    {
        listingService.delete(listingid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping(value="/listing/deleteall")
    public ResponseEntity<?> deleteAll(){
        listingService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping(value = "/user",
            consumes = "application/json")
    public ResponseEntity<?> addNewListing(
            @Valid
            @RequestBody
                    Listing newlisting) throws
            URISyntaxException
    {
        newlisting.setListingid(0);
        newlisting = listingService.save(newlisting);
        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newlisting.getListingid())
                .toUri();
        responseHeaders.setLocation(newUserURI);
        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }
}