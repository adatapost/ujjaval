package com.aim.app.controller.shop;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.aim.app.dao.SupplierDao;
import com.aim.app.model.Supplier;

@Controller
public class SupplierController {
	

	private SupplierDao supplierDao;

	public SupplierController(SupplierDao supplierDao) {
		this.supplierDao = supplierDao;
	}
	
	@RequestMapping("/shop/supplier")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("shop/supplier/index");
		mv.addObject("suppliers", supplierDao.gets());
		return mv;
	}

	@GetMapping("/shop/supplier/create")
	public ModelAndView create() {
		ModelAndView mv = new ModelAndView("shop/supplier/create");
		mv.addObject("supplier", new Supplier());
		return mv;
	}

	@PostMapping("/shop/supplier/create")
	public ModelAndView create(Supplier model) {
		model.getUser().setId(1);
		ModelAndView mv = new ModelAndView("shop/supplier/create");
		try {
			model.setName(model.getName());
			model.setAddress(model.getAddress());
			model.setCity(model.getCity());
			model.setPhone(model.getPhone());
			model.getUser().setId(model.getUser().getId());
			mv.addObject("message", supplierDao.add(model) ? "Added" : "Can't add");
		} catch (Exception ex) {
			mv.addObject("message", "Error.. " + ex);
		}
		mv.addObject("supplier", model);
		return mv;
	}

	@GetMapping("/shop/supplier/display/{id}")
	public ModelAndView display(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("shop/supplier/display");
		Supplier supplier = new Supplier(id);
		supplier = supplierDao.get(supplier);
		if (supplier != null)
			mv.addObject("supplier", supplier);
		return mv;
	}

	@GetMapping("/shop/supplier/edit/{id}")
	public ModelAndView edit(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("shop/supplier/edit");
		Supplier supplier = new Supplier(id);
		supplier = supplierDao.get(supplier);
		if (supplier == null)
			mv.setView(new RedirectView("/shop/supplier"));
		else
			mv.addObject("supplier", supplierDao.get(new Supplier(id)));
		return mv;
	}

	@PostMapping("/shop/supplier/edit")
	public ModelAndView edit(Supplier model) {
		model.getUser().setId(1);
		ModelAndView mv = new ModelAndView("shop/supplier/edit");
		try {
			model.setName(model.getName());
			model.setAddress(model.getAddress());
			model.setCity(model.getCity());
			model.setPhone(model.getPhone());
			model.getUser().setId(model.getUser().getId());
			mv.addObject("message", supplierDao.update(model) ? "Update" : "Can't update");
		} catch (Exception ex) {
			mv.addObject("message", "Error.. " + ex);
		}
		mv.addObject("supplier", model);
		return mv;
	}

	@GetMapping("/shop/supplier/delete/{id}")
	public ModelAndView delete(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("shop/supplier/delete");
		Supplier supplier = new Supplier(id);
		supplier = supplierDao.get(supplier);
		if (supplier == null)
			mv.setView(new RedirectView("/shop/supplier"));
		else
			mv.addObject("supplier", supplierDao.get(new Supplier(id)));
		return mv;
	}

	@PostMapping("/shop/supplier/delete")
	public ModelAndView delete(Supplier model) {
		ModelAndView mv = new ModelAndView("shop/supplier/delete");
		try {
			if (supplierDao.delete(model))
				mv.setView(new RedirectView("/shop/supplier"));
			else
				mv.addObject("message", "Please remove child record first");
		} catch (Exception ex) {
			mv.addObject("message", "Error.. " + ex);
		}
		mv.addObject("supplier", model);
		return mv;
	}

}
