package com.phatov.infomanager.models;


public class CountryModel {
    private String countryName;
    private Integer id;

    public CountryModel(String countryName) {
        this.countryName = countryName;
    }

    public CountryModel(String countryName, Integer id) {
        this.countryName = countryName;
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

