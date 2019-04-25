package com.aim.app.controller.shop;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.aim.app.dao.SaleDao;
import com.aim.app.dao.CustomerDao;
import com.aim.app.dao.DoctorDao;
import com.aim.app.model.Sale;
import com.aim.app.util.Util;
@Controller
public class SaleController {


	private SaleDao saleDao;
	private CustomerDao customerDao;
	private DoctorDao doctorDao;

	public SaleController(SaleDao saleDao, CustomerDao customerDao, DoctorDao doctorDao) {
		this.saleDao = saleDao;
		this.customerDao = customerDao;
		this.doctorDao = doctorDao;
	}
	
	@RequestMapping("/shop/sale")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("shop/sale/index");
		mv.addObject("sales", saleDao.gets());
		return mv;
	}

	@GetMapping("/shop/sale/create")
	public ModelAndView create() {
		ModelAndView mv = new ModelAndView("shop/sale/create");
		mv.addObject("sale", new Sale());
		mv.addObject("customers", customerDao.gets());
		mv.addObject("doctors", doctorDao.gets());
		return mv;
	}

	@PostMapping("/shop/sale/create")
	public ModelAndView create(Sale model,HttpServletRequest request) {
		model.getUser().setId(1);
		ModelAndView mv = new ModelAndView("shop/sale/create");
		try {
			String customerId = request.getParameter("CustomerId");
			String doctorId = request.getParameter("DoctorId");
			System.out.println(customerId);
			model.setSaleDate(Util.utilToSql(model.getSaleDate()));
			model.getCustomer().setId(Util.toInt(customerId));
			model.getDoctor().setId(Util.toInt(doctorId));
			model.getUser().setId(model.getUser().getId());
			System.out.println(model.getUser().getId()+" "+customerId);
			mv.addObject("message", saleDao.add(model) ? "Added" : "Can't add");
		} catch (Exception ex) {
			mv.addObject("message", "Error.. " + ex);
		}
		mv.addObject("sale", model);
		return mv;
	}

	@GetMapping("/shop/sale/display/{id}")
	public ModelAndView display(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("shop/sale/display");
		Sale sale = new Sale(id);
		sale = saleDao.get(sale);
		if (sale != null)
			mv.addObject("sale", sale);
		return mv;
	}

	@GetMapping("/shop/sale/edit/{id}")
	public ModelAndView edit(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("shop/sale/edit");
		Sale sale = new Sale(id);
		sale = saleDao.get(sale);
		if (sale == null)
			mv.setView(new RedirectView("/shop/sale"));
		else {
			mv.addObject("sale", saleDao.get(new Sale(id)));
			mv.addObject("customers", customerDao.gets());
			mv.addObject("doctors", doctorDao.gets());
		}
		return mv;
	}

	@PostMapping("/shop/sale/edit")
	public ModelAndView edit(Sale model, HttpServletRequest request) {
		model.getUser().setId(1);
		ModelAndView mv = new ModelAndView("shop/sale/edit");
		try {
			String customerId = request.getParameter("CustomerId");
			String doctorId = request.getParameter("DoctorId");
			System.out.println(customerId);
			model.setSaleDate(Util.utilToSql(model.getSaleDate()));
			model.getCustomer().setId(Util.toInt(customerId));
			model.getDoctor().setId(Util.toInt(doctorId));
			model.getUser().setId(model.getUser().getId());
			System.out.println(model.getUser().getId()+" "+customerId);
			mv.addObject("message", saleDao.update(model) ? "Update" : "Can't update");
		} catch (Exception ex) {
			mv.addObject("message", "Error.. " + ex);
		}
		mv.addObject("sale", model);
		return mv;
	}

	@GetMapping("/shop/sale/delete/{id}")
	public ModelAndView delete(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("shop/sale/delete");
		Sale sale = new Sale(id);
		sale = saleDao.get(sale);
		if (sale == null)
			mv.setView(new RedirectView("/shop/sale"));
		else
			mv.addObject("sale", saleDao.get(new Sale(id)));
		return mv;
	}

	@PostMapping("/shop/sale/delete")
	public ModelAndView delete(Sale model) {
		ModelAndView mv = new ModelAndView("shop/sale/delete");
		try {
			if (saleDao.delete(model))
				mv.setView(new RedirectView("/shop/sale"));
			else
				mv.addObject("message", "Please remove child record first");
		} catch (Exception ex) {
			mv.addObject("message", "Error.. " + ex);
		}
		mv.addObject("sale", model);
		return mv;
	}

	

}
