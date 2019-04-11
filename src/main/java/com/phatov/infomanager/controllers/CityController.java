package com.phatov.infomanager.controllers;

import com.phatov.infomanager.models.CityModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/city")
public class CityController {
    public static List<CityModel> cityModelList = new ArrayList<CityModel>();
    public static Integer autoId = 0;

    @GetMapping("/list")
    public String showList(Model model) {
        model.addAttribute("cityList", cityModelList);
        return "city/list";
    }

    @GetMapping("/{country}/{countryId}/{countryCode}/list")
    public String showCityList(@PathVariable Integer countryId, @PathVariable String country, @PathVariable String countryCode, Model model) {
        List<CityModel> cityCountryList = new ArrayList<CityModel>();
        for(CityModel scan : cityModelList){
            if(scan.getCountryId().equals(countryId) && scan.getCountry().equals(country) || scan.getCountryCode().equals(countryCode)){
                cityCountryList.add(scan);
                model.addAttribute("cityList", cityCountryList);
            }
        }
        return "city/list";
    }

    @GetMapping("/{country}/{countryId}/{countryCode}/create")
    public String showCreatePage() {
        return "city/create";
    }

    @PostMapping("/{country}/{countryId}/{countryCode}/create")
    public String saveCity(@PathVariable String country, @PathVariable String countryCode, @PathVariable Integer countryId, @RequestParam("city") String cityCreated, @RequestParam("cityCode") String cityCode) {
        if (country == null || country.isEmpty()) {
            return "city/list";
        } else {
            if (cityCreated == null || cityCode == null || cityCreated.isEmpty() || cityCode.isEmpty()) {
                return "city/create";
            } else {
                CityModel cityModelSubmit = new CityModel(cityCreated, cityCode, country, countryCode, countryId, ++autoId);
                cityModelList.add(cityModelSubmit);
            }
        }
        return "redirect:/city/{country}/{countryId}/{countryCode}/list";
    }

    @GetMapping("/{cityId}/edit")
    public String showEditPage(){
        return "city/edit";
    }

    @PostMapping("/{cityId}/edit")
    public String saveCityEdited(@PathVariable Integer cityId,
                                 @RequestParam("editedCity") String editedCity,
                                 @RequestParam("editedCode") String editedCode){
        if(editedCity == null || editedCode == null || editedCity.isEmpty() || editedCode.isEmpty()){
            return "city/list";
        }else {
            for(CityModel edit : cityModelList)
                if(edit.getId().equals(cityId)){
                    edit.setCityName(editedCity);
                    edit.setCityCode(editedCode);
                }
        }
        return "redirect:/city/list";
    }

}

