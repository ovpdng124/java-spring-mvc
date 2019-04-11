package com.phatov.infomanager.models;

public class CityModel {
    private String cityName;
    private String cityCode;
    private Integer countryId;
    private Integer id;

    public CityModel(String cityName, String cityCode, Integer countryId, Integer id) {
        this.cityName = cityName;
        this.cityCode = cityCode;
        this.countryId = countryId;
        this.id = id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }
}
