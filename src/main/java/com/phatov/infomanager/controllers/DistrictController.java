package com.phatov.infomanager.controllers;

import com.phatov.infomanager.models.CityModel;
import com.phatov.infomanager.models.DistrictModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{cityId}/list")
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

    @GetMapping("/{cityId}/create")
    public String showCreatePage() {
        return "district/create";
    }

    @PostMapping("/{cityId}/create")
    public String saveDistrict(@PathVariable Integer cityId, @RequestParam("district") String districtCreated, @RequestParam("districtCode") String districtCode, Model model) {
        if (districtCreated == null || districtCode == null || districtCreated.isEmpty() || districtCode.isEmpty()) {
            model.addAttribute("errormessage", "Content couldn't be empty!!!");
        } else {
            DistrictModel districtModel = new DistrictModel(districtCreated, districtCode, ++autoId, cityId);
            districtModelList.add(districtModel);
            for (DistrictModel validate : validateList) {
                if (validate.getDistrictName().equals(districtCreated) && validate.getDistrictCode().equals(districtCode) && validate.getCityId().equals(cityId)) {
                    model.addAttribute("validatemessage", "District name and district code already exist!!");
                    districtModelList.remove(districtModel);
                }
            }
            if (districtModelList.contains(districtModel)) {
                model.addAttribute("successmassage", "Successfully created!");
                validateList.add(districtModel);
            }
        }
        return "district/create";
    }

    @GetMapping("/{districtId}/edit")
    public String showEditPage() {
        return "district/edit";
    }

    @PostMapping("/{districtId}/edit")
    public String saveDistrictEdited(@PathVariable Integer districtId,
                                     @RequestParam("editedDistrict") String editedDistrict,
                                     @RequestParam("editedCode") String editedCode, Model model) {
        if (editedDistrict == null || editedCode == null || editedDistrict.isEmpty() || editedCode.isEmpty()) {
            model.addAttribute("errormessage", "Content couldn't be empty!!!");
        } else {
            for (DistrictModel districtEdit : districtModelList) {
                if (districtEdit.getId().equals(districtId))
                    districtEdit.setDistrictName(editedDistrict);
                districtEdit.setDistrictCode(editedCode);
                model.addAttribute("successmassage", "Successfully edited!");
            }
        }
        return "district/edit";
    }
}
