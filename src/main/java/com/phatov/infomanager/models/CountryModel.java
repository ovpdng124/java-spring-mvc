package com.phatov.infomanager.models;


public class CountryModel {
    private String countryName;
    private String countryCode;
    private Integer id;

    public CountryModel(String countryName, String countryCode, Integer id) {
        this.countryName = countryName;
        this.countryCode = countryCode;
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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}

