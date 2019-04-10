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
    public String createName(@RequestParam("country") String countryCreated, Model model){
        if(countryCreated == null || countryCreated.isEmpty()){
            return "country/create";
        }
        else {
            CountryModel countrySubmit = new CountryModel(countryCreated, ++autoId);
            countryModelList.add(countrySubmit);
        }
        return "redirect:/country/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditPage(@PathVariable("id") Integer idEdit){
        return "country/edit";
    }

    @PostMapping("/edit/{id}")
    public String editName(@RequestParam("edited") String countryEdited, @PathVariable("id") Integer idEdit, Model modelEdit ){
        if(countryEdited == null || countryEdited.isEmpty()){
            return "country/edit";
        }
        else {
            for (CountryModel countryModel : countryModelList){
                if(countryModel.getId().equals(idEdit)){
                    countryModel.setCountryName(countryEdited);
                }
            }
        }
        return "redirect:/country/list";
    }

}
