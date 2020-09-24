package com.lambdaschool.usermodel.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="listings")
public class Listing extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long listingid;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value="listings", allowSetters = true)
    private User user;

    public Listing() {
    }

    public Listing( String title, String description, User user) {

        this.title = title;
        this.description = description;
        this.user = user;
    }

    public long getListingid() {
        return listingid;
    }

    public void setListingid(long listingid) {
        this.listingid = listingid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
