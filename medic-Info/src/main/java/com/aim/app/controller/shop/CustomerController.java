package com.aim.app.controller.shop;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.aim.app.dao.CustomerDao;
import com.aim.app.model.Customer;

@Controller
public class CustomerController {
	
	private CustomerDao customerDao;

	public CustomerController(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	@RequestMapping("/shop/customer")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("shop/customer/index");
		mv.addObject("customers", customerDao.gets());
		return mv;
	}

	@GetMapping("/shop/customer/create")
	public ModelAndView create() {
		ModelAndView mv = new ModelAndView("shop/customer/create");
		mv.addObject("customer", new Customer());
		return mv;
	}

	@PostMapping("/shop/customer/create")
	public ModelAndView create(Customer model) {
		ModelAndView mv = new ModelAndView("shop/customer/create");
		try {
			model.setName(model.getName());
			model.setAddress(model.getAddress());
			model.setPhone(model.getPhone());
			model.setUser(model.getUser());
			mv.addObject("message", customerDao.add(model) ? "Added" : "Can't add");
		} catch (Exception ex) {
			mv.addObject("message", "Error.. " + ex);
		}
		mv.addObject("customer", model);
		return mv;
	}

	@GetMapping("/shop/customer/display/{id}")
	public ModelAndView display(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("shop/customer/display");
		Customer customer = new Customer(id);
		customer = customerDao.get(customer);
		if (customer != null)
			mv.addObject("customer", customer);
		return mv;
	}

	@GetMapping("/shop/customer/edit/{id}")
	public ModelAndView edit(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("shop/customer/edit");
		Customer customer = new Customer(id);
		customer = customerDao.get(customer);
		if (customer == null)
			mv.setView(new RedirectView("/customer"));
		else
			mv.addObject("customer", customerDao.get(new Customer(id)));
		return mv;
	}

	@PostMapping("/shop/customer/edit")
	public ModelAndView edit(Customer model) {
		ModelAndView mv = new ModelAndView("shop/customer/edit");
		try {
			model.setName(model.getName());
			model.setAddress(model.getAddress());
			model.setPhone(model.getPhone());
			model.setUser(model.getUser());
			mv.addObject("message", customerDao.update(model) ? "Update" : "Can't update");
		} catch (Exception ex) {
			mv.addObject("message", "Error.. " + ex);
		}
		mv.addObject("customer", model);
		return mv;
	}

	@GetMapping("/shop/customer/delete/{id}")
	public ModelAndView delete(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("shop/customer/delete");
		Customer customer = new Customer(id);
		customer = customerDao.get(customer);
		if (customer == null)
			mv.setView(new RedirectView("/shop/customer"));
		else
			mv.addObject("customer", customerDao.get(new Customer(id)));
		return mv;
	}

	@PostMapping("/shop/customer/delete")
	public ModelAndView delete(Customer model) {
		ModelAndView mv = new ModelAndView("shop/customer/delete");
		try {
			if (customerDao.delete(model))
				mv.setView(new RedirectView("/shop/customer"));
			else
				mv.addObject("message", "Please remove child record first");
		} catch (Exception ex) {
			mv.addObject("message", "Error.. " + ex);
		}
		mv.addObject("customer", model);
		return mv;
	}

}
