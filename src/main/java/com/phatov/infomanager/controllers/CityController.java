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

    @GetMapping("/{countryId}/list")
    public String showCityList(@PathVariable Integer countryId, Model model) {
        List<CityModel> cityCountryList = new ArrayList<CityModel>();
        for (CityModel scan : cityModelList) {
            if (scan.getCountryId().equals(countryId)) {
                cityCountryList.add(scan);
                model.addAttribute("cityList", cityCountryList);
            }
        }
        return "city/list";
    }

    @GetMapping("/{countryId}/create")
    public String showCreatePage() {
        return "city/create";
    }

    @PostMapping("/{countryId}/create")
    public String saveCity(@PathVariable Integer countryId,
                           @RequestParam("city") String cityCreated, @RequestParam("cityCode") String cityCode, Model model) {
        if (countryId == null || countryId < 0) {
            return "city/list";
        } else {
            if (cityCreated == null || cityCode == null || cityCreated.isEmpty() || cityCode.isEmpty()) {
                model.addAttribute("errormessage", "Content couldn't be empty!!!");
            } else {
                CityModel cityModelSubmit = new CityModel(cityCreated, cityCode, countryId, ++autoId);
                cityModelList.add(cityModelSubmit);
                model.addAttribute("successmassage", "Successfully created!");
            }
        }
        return "city/create";
    }

    @GetMapping("/{cityId}/edit")
    public String showEditPage() {
        return "city/edit";
    }

    @PostMapping("/{cityId}/edit")
    public String saveCityEdited(@PathVariable Integer cityId,
                                 @RequestParam("editedCity") String editedCity,
                                 @RequestParam("editedCode") String editedCode, Model model) {
        if (editedCity == null || editedCode == null || editedCity.isEmpty() || editedCode.isEmpty()) {
            model.addAttribute("errormessage", "Content couldn't be empty!!!");
        } else {
            for (CityModel edit : cityModelList)
                if (edit.getId().equals(cityId)) {
                    edit.setCityName(editedCity);
                    edit.setCityCode(editedCode);
                    model.addAttribute("successmassage", "Successfully edited!");
                }
        }
        return "city/edit";
    }
}

