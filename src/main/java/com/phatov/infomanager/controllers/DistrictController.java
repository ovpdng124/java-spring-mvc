package com.phatov.infomanager.controllers;

import com.phatov.infomanager.models.CityModel;
import com.phatov.infomanager.models.DistrictModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/district")
public class DistrictController {
    public static List<DistrictModel> districtModelList = new ArrayList<DistrictModel>();
    public static List<DistrictModel> validateList = new ArrayList<DistrictModel>();
    public static Integer autoId = 0;

    @GetMapping("/list")
    public String showListPage(Model model) {
        model.addAttribute("districtList", districtModelList);
        return "district/list";
    }

    @GetMapping("/list/{cityId}")
    public String showDistrictList(@PathVariable Integer cityId, Model model) {
        List<DistrictModel> districtCityList = new ArrayList<DistrictModel>();
        for (DistrictModel scan : districtModelList) {
            if (scan.getCityId().equals(cityId)) {
                districtCityList.add(scan);
                model.addAttribute("districtList", districtCityList);
            }
        }
        return "district/list";
    }

    @GetMapping("/create/{cityId}")
    public String showCreatePage() {
        return "district/create";
    }

    @PostMapping("/create/{cityId}")
    public String saveDistrict(@PathVariable Integer cityId, @RequestParam("district") String districtName, @RequestParam("districtCode") String districtCode, Model model) {
        if (districtName == null || districtCode == null || districtName.isEmpty() || districtCode.isEmpty()) {
            model.addAttribute("errormessage", "Content couldn't be empty!!!");
        } else {
            DistrictModel districtModel = new DistrictModel(districtName, districtCode, ++autoId, cityId);
            Boolean existed = false;
            for (DistrictModel validate : districtModelList) {
                if (validate.getDistrictName().equals(districtName) && validate.getDistrictCode().equals(districtCode)) {
                    model.addAttribute("validatemessage", "District name and district code already exist!!");
                    existed = true;
                    break;
                }
                if (validate.getDistrictName().equals(districtName)) {
                    model.addAttribute("validatemessage", "District name already exist!!");
                    existed = true;
                    break;
                }
                if (validate.getDistrictCode().equals(districtCode)) {
                    model.addAttribute("validatemessage", "District code already exist!!");
                    existed = true;
                    break;
                }
            }
            if (!existed) {
                districtModelList.add(districtModel);
                model.addAttribute("successmassage", "Successfully created!");
            }
        }
        return "district/create";
    }

    @GetMapping("/edit/{districtId}")
    public String showEditPage() {
        return "district/edit";
    }

    @PostMapping("/edit/{districtId}")
    public String saveDistrictEdited(@PathVariable Integer districtId,
                                     @RequestParam("editedDistrict") String editedName,
                                     @RequestParam("editedCode") String editedCode, Model model) {
        if (editedName == null || editedCode == null || editedName.isEmpty() || editedCode.isEmpty()) {
            model.addAttribute("errormessage", "Content couldn't be empty!!!");
        } else {
            Boolean existed = false;
            for (DistrictModel validate : districtModelList) {
                if (validate.getDistrictName().equals(editedName) && validate.getDistrictCode().equals(editedCode)) {
                    model.addAttribute("validatemessage", "District name and district code already exist!!");
                    existed = true;
                    break;
                }
                if (validate.getDistrictName().equals(editedName)) {
                    model.addAttribute("validatemessage", "District name already exist!!");
                    existed = true;
                    break;
                }
                if (validate.getDistrictCode().equals(editedCode)) {
                    model.addAttribute("validatemessage", "District code already exist!!");
                    existed = true;
                    break;
                }
            }
            if (!existed) {
                for (DistrictModel edited : districtModelList) {
                    if (edited.getId().equals(districtId)) {
                        edited.setDistrictName(editedName);
                        edited.setDistrictCode(editedCode);
                        model.addAttribute("successmassage", "Successfully edited!");
                    }
                }
            }
        }
        return "district/edit";
    }

    @GetMapping("/delete/{id}")
    public String showDeletePage() {
        return "district/delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteDistrict(@PathVariable("id") Integer districtId) {
        List<DistrictModel> deleteObject = new ArrayList<DistrictModel>();
        for (DistrictModel delete : districtModelList) {
            if (delete.getId().equals(districtId)) {
                deleteObject.add(delete);
            }
        }
        districtModelList.removeAll(deleteObject);
        return "redirect:/district/list";

    }
}
