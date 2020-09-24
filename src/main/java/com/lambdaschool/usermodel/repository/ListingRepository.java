package com.lambdaschool.usermodel.repository;

import com.lambdaschool.usermodel.models.Listing;
import org.springframework.data.repository.CrudRepository;

public interface ListingRepository extends CrudRepository<Listing, Long> {
}
