package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ModelAndView get() {
        Iterable<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("customers/index");
        modelAndView.addObject("customers", customers);
        modelAndView.addObject("title", "List Customers");
        return modelAndView;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getNewForm() {
        return "customers/create";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String add(BindingResult result) {
        if (result.hasErrors()) {
            return "customers/create";
        }
        // Call Service save()
        return "redirect:/customers";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String findCustomer(@PathVariable Long id) {
        // Call service findOne(id);
        // Model addAttribute
        return "customers/display";
    }
}
