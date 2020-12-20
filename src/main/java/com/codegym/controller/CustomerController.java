package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.service.CustomerService;
import com.codegym.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ModelAndView get(@RequestParam("s") Optional<String> s, Pageable pageable) {
        Page<Customer> customers;
        if (s.isPresent()) {
            customers = customerService.findAllByNameContaining(s.get(), pageable);
        } else {
            customers = customerService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("customers/index");
        modelAndView.addObject("customers", customers);
        modelAndView.addObject("title", "List Customers");
        modelAndView.addObject("search", s);
        return modelAndView;
    }

    @GetMapping("create")
    public ModelAndView getNewForm() {
        ModelAndView modelAndView = new ModelAndView("customers/create");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("title", "Create Customer");
        return modelAndView;
    }

    @PostMapping("create")
    public String addCustomer(@ModelAttribute("customer") Customer customer, RedirectAttributes redirectAttributes) {
        customerService.save(customer);
        redirectAttributes.addFlashAttribute("message", "New record created successfully.");
        return "redirect:/customers/create";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView showEditForm(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        if (customer != null) {
            modelAndView.setViewName("customers/edit");
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("provinces", provinces());
            modelAndView.addObject("title", "Edit Customer");
        } else {
            modelAndView.setViewName("error");
            modelAndView.addObject("message", "Customer not found.");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editCustomer(@ModelAttribute("customer") Customer customer, RedirectAttributes redirectAttributes) {
        customerService.save(customer);
        redirectAttributes.addFlashAttribute("message", "Record updated successfully.");
        return "redirect:/customers/edit/" + customer.getId();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView showDeleteForm(@PathVariable(name = "id") Long id) {
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        if (customer != null) {
            modelAndView.setViewName("customers/delete");
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("title", "Delete Customer");
        } else {
            modelAndView.setViewName("error");
            modelAndView.addObject("message", "Customer not found.");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteCustomer(@ModelAttribute("customer") Customer customer, RedirectAttributes redirectAttributes) {
        customerService.remove(customer.getId());
        redirectAttributes.addFlashAttribute("message", "Record deleted successfully.");
        return "redirect:/customers";
    }
}
