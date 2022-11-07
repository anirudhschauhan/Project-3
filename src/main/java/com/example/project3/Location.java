package com.example.project3;
/**
 * Class that stores and retrieves
 * information about a location
 * @author Anirudh Chauhan, Matthew Calora
 */

public enum Location {
    BRIDGEWATER("08807", "SOMERSET"),
    EDISON("08837", "MIDDLESEX"),
    FRANKLIN("08873", "SOMERSET"),
    PISCATAWAY("08854", "MIDDLESEX"),
    SOMERVILLE("08876", "SOMERSET");
    private final String zipcode;
    private final String county;

    /**
     * constructor for the location
     * @param zipcode
     * @param county
     */
    Location(String zipcode, String county) {
        this.zipcode = zipcode;
        this.county = county;
    }

    /**
     * returns location, zipcode and county
     * @return location, zipocde and county as string
     */
    public String toString(){
        return this.name() + ", " + zipcode + ", " + county;
    }

    /**
     * returns location's zipcode
     * @return zipcode as string
     */
    public String getZipCode() {
        return this.zipcode;
    }

    /**
     * returns location's county
     * @return county as string
     */
    public String getCounty() {
        return this.county;
    }
}