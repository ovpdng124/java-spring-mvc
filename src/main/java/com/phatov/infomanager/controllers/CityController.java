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
    //    public static List<CityModel> validateList = new ArrayList<CityModel>();
    public static Integer autoId = 0;

    @GetMapping("/list")
    public String showList(Model model) {
        model.addAttribute("cityList", cityModelList);
        return "city/list";
    }

    @GetMapping("/list/{countryId}")
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

    @GetMapping("/create/{countryId}")
    public String showCreatePage() {
        return "city/create";
    }

    @PostMapping("/create/{countryId}")
    public String saveCity(@PathVariable Integer countryId,
                           @RequestParam("city") String cityCreated, @RequestParam("cityCode") String cityCode, Model model) {
        if (countryId == null || countryId < 0) {
            return "city/list";
        } else {
            if (cityCreated == null || cityCode == null || cityCreated.isEmpty() || cityCode.isEmpty()) {
                model.addAttribute("errormessage", "Contents couldn't be empty!!!");
            } else {
                CityModel cityModelSubmit = new CityModel(cityCreated, cityCode, countryId, ++autoId);
                Boolean existed = false;
                for (CityModel created : cityModelList) {
                    if (created.getCityName().equals(cityCreated) && created.getCityCode().equals(cityCode)) {
                        model.addAttribute("validatemessage", "City name and city code already exist!!");
                        existed = true;
                        break;
                    }
                    if (created.getCityName().equals(cityCreated)) {
                        model.addAttribute("validatemessage", "City name already exist!!");
                        existed = true;
                        break;
                    }
                    if (created.getCityCode().equals(cityCode)) {
                        model.addAttribute("validatemessage", "City code already exist!!");
                        existed = true;
                        break;
                    }
                }
                if (!existed) {
                    cityModelList.add(cityModelSubmit);
                    model.addAttribute("successmassage", "Successfully created!");
                }

            }
        }
        return "city/create";
    }

    @GetMapping("/edit/{cityId}")
    public String showEditPage() {
        return "city/edit";
    }

    @PostMapping("/edit/{cityId}")
    public String saveCityEdited(@PathVariable Integer cityId,
                                 @RequestParam("editedCity") String editedCity,
                                 @RequestParam("editedCode") String editedCode, Model model) {
        if (editedCity == null || editedCode == null || editedCity.isEmpty() || editedCode.isEmpty()) {
            model.addAttribute("errormessage", "Content couldn't be empty!!!");
        } else {
            Boolean existed = false;
            for (CityModel edit : cityModelList) {
                if (edit.getCityName().equals(editedCity) && edit.getCityCode().equals(editedCode)) {
                    model.addAttribute("validatemessage", "City name and city code already exist!!");
                    existed = true;
                    break;
                }
                if (edit.getCityName().equals(editedCity)) {
                    model.addAttribute("validatemessage", "City name already exist!!");
                    existed = true;
                    break;
                }
                if (edit.getCityCode().equals(editedCode)) {
                    model.addAttribute("validatemessage", "City code already exist!!");
                    existed = true;
                    break;
                }
            }
            if (!existed) {

                for (CityModel edit : cityModelList) {
                    if (edit.getId().equals(cityId)) {
                        edit.setCityName(editedCity);
                        edit.setCityCode(editedCode);
                        model.addAttribute("successmassage", "Successfully edited!");
                    }
                }
            }
        }
        return "city/edit";
    }

    @GetMapping("/delete/{id}")
    public String showDeletePage() {
        return "city/delete";
    }

    @PostMapping("delete/{id}")
    public String deleteCity(@PathVariable("id") Integer cityId) {
        List<CityModel> deleteOject = new ArrayList<CityModel>();
        for (CityModel delete : cityModelList) {
            if (delete.getId().equals(cityId)) {
                deleteOject.add(delete);
            }
        }
        cityModelList.removeAll(deleteOject);
        return "redirect:/city/list";
    }
}

