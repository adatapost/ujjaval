package com.aim.app.controller.customer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.aim.app.SessionWrapper;
import com.aim.app.dao.CustomerDao;
import com.aim.app.model.Customer;

@Controller
public class CustomerDeskboardController {
	@RequestMapping("/customer")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("customer/deskboard/index");
		mv.addObject("user", SessionWrapper.user(request.getSession()));
		return mv;
	}
	
	@RequestMapping("/customer/search-med")
	public ModelAndView searchMed() {
		ModelAndView mv = new ModelAndView("customer/deskboard/search-med");
		return mv;
	}
	
	@RequestMapping("/customer/search-shop")
	public ModelAndView searchShop() {
		ModelAndView mv = new ModelAndView("customer/deskboard/search-shop");
		return mv;
	}
	@RequestMapping("/customer/order")
	public ModelAndView myOrder() {
		ModelAndView mv = new ModelAndView("customer/deskboard/order");
		return mv;
	}
	 
}
