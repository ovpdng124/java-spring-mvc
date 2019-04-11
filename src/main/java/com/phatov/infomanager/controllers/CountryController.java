package com.phatov.infomanager.controllers;

import com.phatov.infomanager.models.CountryModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/country")
public class CountryController {
    public static List<CountryModel> countryModelList = new ArrayList<CountryModel>();
    public static Integer autoId = 0;

    @GetMapping("/list")
    public String showListPage(Model listModel){
        listModel.addAttribute("countryList", countryModelList);
        return "country/list";
    }

    @GetMapping("/create")
    public String showCreatePage(){
        return "country/create";
    }

    @PostMapping("/create")
    public String saveCountry(@RequestParam("country") String countryCreated, @RequestParam("countryCode") String countryCode, Model model){
        if(countryCreated == null || countryCode == null || countryCreated.isEmpty() || countryCode.isEmpty()){
            return "country/create";
        }
        else {
            CountryModel countrySubmit = new CountryModel(countryCreated, countryCode, ++autoId);
            countryModelList.add(countrySubmit);
        }
        return "redirect:/country/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditPage(@PathVariable("id") Integer idEdit){
        return "country/edit";
    }

    @PostMapping("/edit/{id}")
    public String editName(@RequestParam("editedCountry") String editedCountry, @RequestParam("editedCode") String editedCode, @PathVariable("id") Integer idEdit ){
        if(editedCountry == null || editedCode == null || editedCountry.isEmpty()  || editedCode.isEmpty()){
            return "country/edit";
        }
        else {
            for (CountryModel countryModel : countryModelList){
                if(countryModel.getId().equals(idEdit)){
                    countryModel.setCountryName(editedCountry);
                    countryModel.setCountryCode(editedCode);
                }
            }
        }
        return "redirect:/country/list";
    }

}
