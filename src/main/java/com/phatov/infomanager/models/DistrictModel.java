package com.phatov.infomanager.models;

public class DistrictModel {
    private String districtName;
    private String districtCode;
    private Integer id;
    private Integer cityId;

    public DistrictModel(String districtName, String districtCode, Integer id, Integer cityId) {
        this.districtName = districtName;
        this.districtCode = districtCode;
        this.id = id;
        this.cityId = cityId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
