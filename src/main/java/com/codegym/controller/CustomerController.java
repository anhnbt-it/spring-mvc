package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.service.CustomerService;
import com.codegym.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProvinceService provinceService;

    @ModelAttribute("provinces")
    public Iterable<Province> provinces() {
        return provinceService.findAll();
    }

    @GetMapping
    public ModelAndView get() {
        Iterable<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("customers/index");
        modelAndView.addObject("customers", customers);
        modelAndView.addObject("title", "List Customers");
        return modelAndView;
    }

    @GetMapping("create")
    public ModelAndView getNewForm() {
        ModelAndView modelAndView = new ModelAndView("customers/create2");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("create-customer")
    public ModelAndView create(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("customers/create2");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }
    @PostMapping("create")
    public ModelAndView addCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("customers/create");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("message", "New record created successfully");
        return modelAndView;
    }

//    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
//    public ModelAndView showEditForm(@PathVariable Long id) {
//        Optional<Customer> customer = customerService.findById(id);
//        ModelAndView modelAndView = new ModelAndView();
//        if (customer.isPresent()) {
//            modelAndView.setViewName("customers/edit");
//            modelAndView.addObject("customer", customer.get());
//            modelAndView.addObject("provinces", provinces());
//            modelAndView.addObject("title", "Edit Customer");
//        } else {
//            modelAndView.setViewName("error");
//            modelAndView.addObject("message", "Customer not found.");
//        }
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/edit", method = RequestMethod.POST)
//    public String editCustomer(@ModelAttribute("customer") Customer customer, RedirectAttributes redirectAttributes) {
//        customerService.save(customer);
//        redirectAttributes.addFlashAttribute("message", "Record updated successfully.");
//        return "redirect:/customers";
//    }
//
//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
//    public ModelAndView showDeleteForm(@PathVariable(name = "id") Long id) {
//        Optional<Customer> customer = customerService.findById(id);
//        ModelAndView modelAndView = new ModelAndView();
//        if (customer.isPresent()) {
//            modelAndView.setViewName("customers/delete");
//            modelAndView.addObject("customer", customer.get());
//            modelAndView.addObject("title", "Delete Customer");
//        } else {
//            modelAndView.setViewName("error");
//            modelAndView.addObject("message", "Customer not found.");
//        }
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/delete", method = RequestMethod.POST)
//    public String deleteCustomer(@ModelAttribute("customer") Customer customer, RedirectAttributes redirectAttributes) {
//        customerService.remove(customer.getId());
//        redirectAttributes.addFlashAttribute("message", "Record deleted successfully.");
//        return "redirect:/customers";
//    }
}
