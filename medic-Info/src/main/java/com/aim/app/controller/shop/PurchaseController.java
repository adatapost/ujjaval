package com.aim.app.controller.shop;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.aim.app.dao.PurchaseDao;
import com.aim.app.dao.SupplierDao;
import com.aim.app.model.Purchase;
import com.aim.app.model.Supplier;
import com.aim.app.util.Util;


@Controller
public class PurchaseController {


	private PurchaseDao purchaseDao;
	private SupplierDao supplierDao;

	public PurchaseController(PurchaseDao purchaseDao, SupplierDao supplierDao) {
		this.purchaseDao = purchaseDao;
		this.supplierDao = supplierDao;
	}
	
	@RequestMapping("/shop/purchase")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("shop/purchase/index");
		mv.addObject("purchases", purchaseDao.gets());
		return mv;
	}

	@GetMapping("/shop/purchase/create")
	public ModelAndView create() {
		ModelAndView mv = new ModelAndView("shop/purchase/create");
		mv.addObject("purchase", new Purchase());
		mv.addObject("suppliers", supplierDao.gets());
		return mv;
	}

	@PostMapping("/shop/purchase/create")
	public ModelAndView create(Purchase model,HttpServletRequest request) {
		model.getUser().setId(1);
		ModelAndView mv = new ModelAndView("shop/purchase/create");
		try {
			String supplierId = request.getParameter("SupplierId");
			System.out.println(supplierId);
			model.setPurDate(Util.utilToSql(model.getPurDate()));
			model.getSupplier().setId(Util.toInt(supplierId));
			model.getUser().setId(model.getUser().getId());
			System.out.println(model.getUser().getId()+" "+supplierId);
			mv.addObject("message", purchaseDao.add(model) ? "Added" : "Can't add");
		} catch (Exception ex) {
			mv.addObject("message", "Error.. " + ex);
		}
		mv.addObject("purchase", model);
		return mv;
	}

	@GetMapping("/shop/purchase/display/{id}")
	public ModelAndView display(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("shop/purchase/display");
		Purchase purchase = new Purchase(id);
		purchase = purchaseDao.get(purchase);
		if (purchase != null)
			mv.addObject("purchase", purchase);
		return mv;
	}

	@GetMapping("/shop/purchase/edit/{id}")
	public ModelAndView edit(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("shop/purchase/edit");
		Purchase purchase = new Purchase(id);
		purchase = purchaseDao.get(purchase);
		if (purchase == null)
			mv.setView(new RedirectView("/shop/purchase"));
		else {
			mv.addObject("purchase", purchaseDao.get(new Purchase(id)));
			mv.addObject("suppliers", supplierDao.gets());
			
		}
		return mv;
	}

	@PostMapping("/shop/purchase/edit")
	public ModelAndView edit(Purchase model, HttpServletRequest request) {
		model.getUser().setId(1);
		ModelAndView mv = new ModelAndView("shop/purchase/edit");
		try {
			String supplierId = request.getParameter("SupplierId");
			model.setPurDate(Util.utilToSql(model.getPurDate()));
			model.getSupplier().setId(Util.toInt(supplierId));
			model.getUser().setId(model.getUser().getId());
			System.out.println(model.getPurDate());
			mv.addObject("message", purchaseDao.update(model) ? "Update" : "Can't update");
		} catch (Exception ex) {
			mv.addObject("message", "Error.. " + ex);
		}
		mv.addObject("purchase", model);
		return mv;
	}

	@GetMapping("/shop/purchase/delete/{id}")
	public ModelAndView delete(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("shop/purchase/delete");
		Purchase purchase = new Purchase(id);
		purchase = purchaseDao.get(purchase);
		if (purchase == null)
			mv.setView(new RedirectView("/shop/purchase"));
		else
			mv.addObject("purchase", purchaseDao.get(new Purchase(id)));
		return mv;
	}

	@PostMapping("/shop/purchase/delete")
	public ModelAndView delete(Purchase model) {
		ModelAndView mv = new ModelAndView("shop/purchase/delete");
		try {
			if (purchaseDao.delete(model))
				mv.setView(new RedirectView("/shop/purchase"));
			else
				mv.addObject("message", "Please remove child record first");
		} catch (Exception ex) {
			mv.addObject("message", "Error.. " + ex);
		}
		mv.addObject("purchase", model);
		return mv;
	}
}
