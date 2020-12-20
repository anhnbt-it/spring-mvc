package com.codegym.controller;

import com.codegym.model.Province;
import com.codegym.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("provinces")
public class ProvinceController {

    private final ProvinceService provinceService;

    @Autowired
    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @GetMapping
    public ModelAndView get() {
        Iterable<Province> provinces = provinceService.findAll();
        ModelAndView modelAndView = new ModelAndView("provinces/index");
        modelAndView.addObject("provinces", provinces);
        modelAndView.addObject("title", "List Provinces");
        return modelAndView;
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView getNewForm() {
        ModelAndView modelAndView = new ModelAndView("provinces/create");
        modelAndView.addObject("province", new Province());
        modelAndView.addObject("title", "Add New Province");
        return modelAndView;
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String addProvince(@ModelAttribute Province province, RedirectAttributes redirectAttributes) {
        provinceService.save(province);
        redirectAttributes.addFlashAttribute("message", "New record created successfully.");
        return "redirect:/provinces/create";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public ModelAndView showEditForm(@PathVariable Long id) {
        Province province = provinceService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        if (province!=null) {
            modelAndView.setViewName("provinces/edit");
            modelAndView.addObject("province", province);
            modelAndView.addObject("title", "Edit Province");
        } else {
            modelAndView.setViewName("error");
            modelAndView.addObject("message", "Province not found.");
        }
        return modelAndView;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String editProvince(@ModelAttribute Province province, RedirectAttributes redirectAttributes) {
        provinceService.save(province);
        redirectAttributes.addFlashAttribute("message", "Record updated successfully.");
        return "redirect:/provinces/edit/" + province.getId();
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public ModelAndView showDeleteForm(@PathVariable(name = "id") Long id) {
        Province province = provinceService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        if (province!=null) {
            modelAndView.setViewName("provinces/delete");
            modelAndView.addObject("province", province);
            modelAndView.addObject("title", "Delete Province");
        } else {
            modelAndView.setViewName("error");
            modelAndView.addObject("message", "Province not found.");
        }
        return modelAndView;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String deleteProvince(@ModelAttribute("province") Province province, RedirectAttributes redirectAttributes) {
        provinceService.remove(province.getId());
        redirectAttributes.addFlashAttribute("message", "Record deleted successfully.");
        return "redirect:/provinces";
    }
}
