package com.phatov.infomanager.models;

public class CityModel {
    private String cityName;
    private String cityCode;
    private String country;
    private String countryCode;
    private Integer countryId;
    private Integer id;

    public CityModel(String country) {
        this.country = country;
    }

    public CityModel(String cityName, String cityCode, String country, String countryCode, Integer countryId, Integer id) {
        this.cityName = cityName;
        this.cityCode = cityCode;
        this.country = country;
        this.countryCode = countryCode;
        this.countryId = countryId;
        this.id = id;
    }

    public CityModel(String cityName, String cityCode, String country, String countryCode, Integer id) {
        this.cityName = cityName;
        this.cityCode = cityCode;
        this.country = country;
        this.countryCode = countryCode;
        this.id = id;
    }

    public CityModel(String cityName, String cityCode) {
        super();
        this.cityName = cityName;
        this.cityCode = cityCode;
    }

    public CityModel(String cityName, String cityCode, Integer id) {
        super();
        this.cityName = cityName;
        this.cityCode = cityCode;
        this.id = id;
    }

    public CityModel(String cityName, String cityCode, String country, Integer id) {
        super();
        this.cityName = cityName;
        this.cityCode = cityCode;
        this.country = country;
        this.id = id;
    }

    public CityModel(String cityName, String cityCode, String country) {
        super();
        this.cityName = cityName;
        this.cityCode = cityCode;
        this.country = country;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }
}
