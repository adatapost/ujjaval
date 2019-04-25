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
import com.aim.app.dao.PurchaseDao;
import com.aim.app.dao.PurchaseDetailDao;
import com.aim.app.model.PurchaseDetail;
import com.aim.app.util.Util;

@Controller
public class PurchaseDetailController {
	private PurchaseDetailDao purchaseDetailDao;
	private PurchaseDao purchaseDao;
	private MedicineDao medicineDao;

	public PurchaseDetailController(PurchaseDetailDao purchaseDetailDao, PurchaseDao purchaseDao, MedicineDao medicineDao) {
		this.purchaseDetailDao = purchaseDetailDao;
		this.purchaseDao = purchaseDao;
		this.medicineDao = medicineDao;
	}
	
	@RequestMapping("/shop/purchase-detail")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("shop/purchase-detail/index");
		mv.addObject("purchaseDetails", purchaseDetailDao.gets());
		return mv;
	}

	@GetMapping("/shop/purchase-detail/create")
	public ModelAndView create() {
		ModelAndView mv = new ModelAndView("shop/purchase-detail/create");
		mv.addObject("purchaseDetail", new PurchaseDetail());
		mv.addObject("purchases", purchaseDao.gets());
		mv.addObject("medicines", medicineDao.gets());
		return mv;
	}

	@PostMapping("/shop/purchase-detail/create")
	public ModelAndView create(PurchaseDetail model,HttpServletRequest request) {
		model.getUser().setId(1);
		ModelAndView mv = new ModelAndView("shop/purchase-detail/create");
		try {
			String medId = request.getParameter("MedicineId");
			String purchaseID = request.getParameter("PurchaseId");
			model.setDetId(model.getDetId());
			model.setQty(model.getQty());
			model.setRate(model.getRate());
			model.setBatchNo(model.getBatchNo());
			model.setExpireDate(Util.utilToSql(model.getExpireDate()));
			model.getPurchase().setId(Util.toInt(purchaseID));
			model.getMedicine().setId(Util.toInt(medId));
			model.getUser().setId(model.getUser().getId());
			System.out.println(model.getUser().getId());
			mv.addObject("message", purchaseDetailDao.add(model) ? "Added" : "Can't add");
		} catch (Exception ex) {
			mv.addObject("message", "Error.. " + ex);
		}
		mv.addObject("purchaseDetail", model);
		return mv;
	}

	@GetMapping("/shop/purchase-detail/display/{id}")
	public ModelAndView display(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("shop/purchase-detail/display");
		PurchaseDetail purcahseDetail = new PurchaseDetail(id);
		purcahseDetail = purchaseDetailDao.get(purcahseDetail);
		if (purcahseDetail != null)
			mv.addObject("purchaseDetail", purcahseDetail);
		return mv;
	}

	@GetMapping("/shop/purchase-detail/edit/{id}")
	public ModelAndView edit(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("shop/purchase-detail/edit");
		PurchaseDetail purcahseDetail = new PurchaseDetail(id);
		purcahseDetail = purchaseDetailDao.get(purcahseDetail);
		if (purcahseDetail == null)
			mv.setView(new RedirectView("/shop/purchase-detail"));
		else {
			mv.addObject("purchaseDetail", purchaseDetailDao.get(new PurchaseDetail(id)));
			mv.addObject("purchases", purchaseDao.gets());
			mv.addObject("medicines", medicineDao.gets());
			
		}
		return mv;
	}

	@PostMapping("/shop/purchase-detail/edit")
	public ModelAndView edit(PurchaseDetail model, HttpServletRequest request) {
		model.getUser().setId(1);
		ModelAndView mv = new ModelAndView("shop/purchase-detail/edit");
		try {
			String medId = request.getParameter("MedicineId");
			String purchaseID = request.getParameter("PurchaseId");
			model.setDetId(model.getDetId());
			model.setQty(model.getQty());
			model.setRate(model.getRate());
			model.setBatchNo(model.getBatchNo());
			model.setExpireDate(Util.utilToSql(model.getExpireDate()));
			model.getPurchase().setId(Util.toInt(purchaseID));
			model.getMedicine().setId(Util.toInt(medId));
			model.getUser().setId(model.getUser().getId());
			System.out.println(model.getUser().getId());
			mv.addObject("message", purchaseDetailDao.update(model) ? "Update" : "Can't update");
		} catch (Exception ex) {
			mv.addObject("message", "Error.. " + ex);
		}
		mv.addObject("purchaseDetail", model);
		return mv;
	}

	@GetMapping("/shop/purchase-detail/delete/{id}")
	public ModelAndView delete(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("shop/purchase-detail/delete");
		PurchaseDetail purcahseDetail = new PurchaseDetail(id);
		purcahseDetail = purchaseDetailDao.get(purcahseDetail);
		if (purcahseDetail == null)
			mv.setView(new RedirectView("/shop/purchase-detail"));
		else
			mv.addObject("purchaseDetail", purchaseDetailDao.get(new PurchaseDetail(id)));
			mv.addObject("purchase", purchaseDao.gets());
			mv.addObject("medicine", medicineDao.gets());
		return mv;
	}

	@PostMapping("/purchase-detail/delete")
	public ModelAndView delete(PurchaseDetail model) {
		ModelAndView mv = new ModelAndView("purchase-detail/delete");
		try {
			if (purchaseDetailDao.delete(model))
				mv.setView(new RedirectView("/purchase-detail"));
			else
				mv.addObject("message", "Please remove child record first");
		} catch (Exception ex) {
			mv.addObject("message", "Error.. " + ex);
		}
		mv.addObject("purchaseDetail", model);
		return mv;
	}


}
