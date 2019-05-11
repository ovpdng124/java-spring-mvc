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
    public static List<CountryModel> validateList = new ArrayList<CountryModel>();
    public static Integer autoId = 0;

    @GetMapping("/list")
    public String showListPage(Model listModel) {
        listModel.addAttribute("countryList", countryModelList);
        return "country/list";
    }

    @GetMapping("/create")
    public String showCreatePage() {
        return "country/create";
    }

    @PostMapping("/create")
    public String saveCountry(@RequestParam("country") String countryName, @RequestParam("countryCode") String countryCode, Model model) {
        if (countryName == null || countryCode == null || countryName.isEmpty() || countryCode.isEmpty()) {
            model.addAttribute("errormessage", "Content couldn't be empty!!!");
        } else {
            CountryModel countrySubmit = new CountryModel(countryName, countryCode, ++autoId);
            Boolean existed = false;
            for (CountryModel validate : countryModelList) {
                if (validate.getCountryName().equals(countryName) && validate.getCountryCode().equals(countryCode)) {
                    model.addAttribute("validatemessage", "Country name and country code already exist!!");
                    existed = true;
                    break;
                }
                if (validate.getCountryName().equals(countryName)) {
                    model.addAttribute("validatemessage", "Country name already exist!!");
                    existed = true;
                    break;
                }
                if (validate.getCountryCode().equals(countryCode)) {
                    model.addAttribute("validatemessage", "Country code already exist!!");
                    existed = true;
                    break;
                }
            }
            if (!existed) {
                countryModelList.add(countrySubmit);
                model.addAttribute("successmassage", "Successfully created!");
            }
        }
        return "country/create";
    }

    @GetMapping("/edit/{id}")
    public String showEditPage(@PathVariable("id") Integer idEdit) {
        return "country/edit";
    }

    @PostMapping("/edit/{id}")
    public String editName(@RequestParam("editedName") String editedName, @RequestParam("editedCode") String editedCode,
                           @PathVariable("id") Integer idEdit, Model model) {
        if (editedName == null || editedCode == null || editedName.isEmpty() || editedCode.isEmpty()) {
            model.addAttribute("errormessage", "Content couldn't be empty!!!");
        } else {
            Boolean existed = false;
            for (CountryModel validate : countryModelList) {
                if (validate.getCountryName().equals(editedName) && validate.getCountryCode().equals(editedCode)) {
                    model.addAttribute("validatemessage", "Country name and country code already existed!!");
                    existed = true;
                    break;
                }
                if (validate.getCountryName().equals(editedName)) {
                    model.addAttribute("validatemessage", "Country name already existed!!");
                    existed = true;
                    break;
                }
                if (validate.getCountryCode().equals(editedCode)) {
                    model.addAttribute("validatemessage", "Country code already existed!!");
                    existed = true;
                    break;
                }
            }
            if (!existed) {
                for (CountryModel edited : countryModelList) {
                    if (edited.getId().equals(idEdit)) {
                        edited.setCountryName(editedName);
                        edited.setCountryCode(editedCode);
                        model.addAttribute("successmassage", "Successfully edited!");
                    }
                }
            }
        }
        return "country/edit";
    }

    @GetMapping("/delete/{id}")
    public String showDeletePage() {
        return "country/delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteCountry(@PathVariable("id") Integer countryId) {
        List<CountryModel> deleteObject = new ArrayList<CountryModel>();
        for (CountryModel delete : countryModelList) {
            if (delete.getId().equals(countryId)) {
                deleteObject.add(delete);
            }
        }
        countryModelList.removeAll(deleteObject);
        return "redirect:/country/list";
    }
}
