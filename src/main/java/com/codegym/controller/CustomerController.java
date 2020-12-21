package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.service.CustomerService;
import com.codegym.service.DuplicateEmailException;
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
        Page<Customer> customers = null;
        if (s.isPresent()) {
            customers = customerService.findAllByNameContaining(s.get(), pageable);
        } else {
            try {
                customers = customerService.findAll(pageable);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
    public ModelAndView addCustomer(@ModelAttribute("customer") Customer customer, RedirectAttributes redirectAttributes) {
//        customerService.save(customer);
//        return "redirect:/customers/create";
        try {
            redirectAttributes.addFlashAttribute("message", "New record created successfully.");
            customerService.save(customer);
            return new ModelAndView("redirect:/customers");
        } catch (DuplicateEmailException e) {
            return new ModelAndView("customers/inputs-not-acceptable");
        }
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public ModelAndView showEditForm(@PathVariable Long id) {
        Customer customer = null;
        try {
            customer = customerService.findOne(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ModelAndView editCustomer(@ModelAttribute("customer") Customer customer, RedirectAttributes redirectAttributes) throws DuplicateEmailException {
        try {
            redirectAttributes.addFlashAttribute("message", "Record updated successfully.");
            customerService.save(customer);
            return new ModelAndView("redirect:/customers/edit/" + customer.getId());
        } catch (DuplicateEmailException e) {
            return new ModelAndView("customers/inputs-not-acceptable");
        }
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public ModelAndView showDeleteForm(@PathVariable(name = "id") Long id) {
        Customer customer = null;
        try {
            customer = customerService.findOne(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String deleteCustomer(@ModelAttribute("customer") Customer customer, RedirectAttributes redirectAttributes) {
        customerService.delete(customer.getId());
        redirectAttributes.addFlashAttribute("message", "Record deleted successfully.");
        return "redirect:/customers";
    }

    @GetMapping("{id}")
    public ModelAndView showInformation(@PathVariable Long id) {
        try {
            ModelAndView modelAndView = new ModelAndView("customers/edit");
            Customer customer = null;
            customer = customerService.findOne(id);
            modelAndView.addObject("customer", customer);
            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("redirect:/customers");
        }
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ModelAndView showInputNotAcceptable() {
        return new ModelAndView("customers/inputs-not-acceptable");
    }
}
