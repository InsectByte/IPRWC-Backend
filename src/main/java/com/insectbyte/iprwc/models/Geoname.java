package com.insectbyte.iprwc.models;

public class Geoname {
    private String postalcode;
    private String name;
    private String countrycode;
    private String AdminName1;

    public Geoname(String postalcode, String name, String countrycode, String adminName1) {
        this.postalcode = postalcode;
        this.name = name;
        this.countrycode = countrycode;
        AdminName1 = adminName1;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getAdminName1() {
        return AdminName1;
    }

    public void setAdminName1(String adminName1) {
        AdminName1 = adminName1;
    }
}
