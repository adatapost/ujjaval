package com.aim.app.controller.shop;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.aim.app.dao.MedicineDao;
import com.aim.app.dao.SaleDao;
import com.aim.app.dao.SaleDetailDao;
import com.aim.app.model.SaleDetail;
import com.aim.app.util.Util;

@Controller
public class SaleDetailController {
	private SaleDetailDao saleDetailDao;
	private SaleDao saleDao;
	private MedicineDao medicineDao;

	public SaleDetailController(SaleDetailDao saleDetailDao, SaleDao saleDao, MedicineDao medicineDao) {
		this.saleDetailDao = saleDetailDao;
		this.saleDao = saleDao;
		this.medicineDao = medicineDao;
	}
	
	@RequestMapping("/shop/sale-detail")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("shop/sale-detail/index");
		mv.addObject("saleDetails", saleDetailDao.gets());
		return mv;
	}

	@GetMapping("/shop/sale-detail/create")
	public ModelAndView create() {
		ModelAndView mv = new ModelAndView("shop/sale-detail/create");
		mv.addObject("saleDetail", new SaleDetail());
		mv.addObject("sales", saleDao.gets());
		mv.addObject("medicines", medicineDao.gets());
		return mv;
	}

	@PostMapping("/shop/sale-detail/create")
	public ModelAndView create(SaleDetail model,HttpServletRequest request) {
		model.getUser().setId(1);
		ModelAndView mv = new ModelAndView("shop/sale-detail/create");
		try {
			String medId = request.getParameter("MedicineId");
			String saleId = request.getParameter("SaleId");
			model.setDetId(model.getDetId());
			model.setQty(model.getQty());
			model.setRate(model.getRate());	
			model.setDiscount(model.getDiscount());
			model.getSale().setId(Util.toInt(saleId));
			model.getMedicine().setId(Util.toInt(medId));
			model.getUser().setId(model.getUser().getId());
			System.out.println(model.getUser().getId());
			mv.addObject("message", saleDetailDao.add(model) ? "Added" : "Can't add");
		} catch (Exception ex) {
			mv.addObject("message", "Error.. " + ex);
		}
		mv.addObject("saleDetail", model);
		return mv;
	}

	@GetMapping("/shop/sale-detail/display/{id}")
	public ModelAndView display(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("shop/sale-detail/display");
		SaleDetail purcahseDetail = new SaleDetail(id);
		purcahseDetail = saleDetailDao.get(purcahseDetail);
		if (purcahseDetail != null)
			mv.addObject("saleDetail", purcahseDetail);
		return mv;
	}

	@GetMapping("/shop/sale-detail/edit/{id}")
	public ModelAndView edit(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("shop/sale-detail/edit");
		SaleDetail purcahseDetail = new SaleDetail(id);
		purcahseDetail = saleDetailDao.get(purcahseDetail);
		if (purcahseDetail == null)
			mv.setView(new RedirectView("/shop/sale-detail"));
		else {
			mv.addObject("saleDetail", saleDetailDao.get(new SaleDetail(id)));
			mv.addObject("sales", saleDao.gets());
			mv.addObject("medicines", medicineDao.gets());
			
		}
		return mv;
	}

	@PostMapping("/shop/sale-detail/edit")
	public ModelAndView edit(SaleDetail model, HttpServletRequest request) {
		model.getUser().setId(1);
		ModelAndView mv = new ModelAndView("shop/sale-detail/edit");
		try {
			String medId = request.getParameter("MedicineId");
			String saleID = request.getParameter("SaleId");
			model.setDetId(model.getDetId());
			model.setQty(model.getQty());
			model.setRate(model.getRate());
			model.setDiscount(model.getDiscount());
			model.getSale().setId(Util.toInt(saleID));
			model.getMedicine().setId(Util.toInt(medId));
			model.getUser().setId(model.getUser().getId());
			System.out.println(model.getUser().getId());
			mv.addObject("message", saleDetailDao.update(model) ? "Update" : "Can't update");
		} catch (Exception ex) {
			mv.addObject("message", "Error.. " + ex);
		}
		mv.addObject("saleDetail", model);
		return mv;
	}

	@GetMapping("/shop/sale-detail/delete/{id}")
	public ModelAndView delete(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("shop/sale-detail/delete");
		SaleDetail purcahseDetail = new SaleDetail(id);
		purcahseDetail = saleDetailDao.get(purcahseDetail);
		if (purcahseDetail == null)
			mv.setView(new RedirectView("/shop/sale-detail"));
		else
			mv.addObject("saleDetail", saleDetailDao.get(new SaleDetail(id)));
			mv.addObject("sale", saleDao.gets());
			mv.addObject("medicine", medicineDao.gets());
		return mv;
	}

	@PostMapping("/shop/sale-detail/delete")
	public ModelAndView delete(SaleDetail model) {
		ModelAndView mv = new ModelAndView("shop/sale-detail/delete");
		try {
			if (saleDetailDao.delete(model))
				mv.setView(new RedirectView("/shop/sale-detail"));
			else
				mv.addObject("message", "Please remove child record first");
		} catch (Exception ex) {
			mv.addObject("message", "Error.. " + ex);
		}
		mv.addObject("saleDetail", model);
		return mv;
	}


}
