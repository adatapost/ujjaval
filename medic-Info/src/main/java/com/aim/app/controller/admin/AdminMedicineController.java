package com.aim.app.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.aim.app.dao.CategoryDao;
import com.aim.app.dao.CompanyDao;
import com.aim.app.dao.MedicineDao;
import com.aim.app.model.Category;
import com.aim.app.model.Company;
import com.aim.app.model.Medicine;
import com.aim.app.util.Util;

@Controller
public class AdminMedicineController {
	
	private MedicineDao medicineDao;
	private CompanyDao companyDao;
	private CategoryDao categoryDao;

	public AdminMedicineController(MedicineDao medicineDao,CompanyDao companyDao,CategoryDao categoryDao) {
		this.medicineDao = medicineDao;
		this.companyDao  = companyDao;
		this.categoryDao = categoryDao;
	}
	
	@RequestMapping("/admin/medicine")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin/medicine/index");
		mv.addObject("medicines", medicineDao.gets());
		return mv;
	}

	@GetMapping("/admin/medicine/create")
	public ModelAndView create() {
		ModelAndView mv = new ModelAndView("admin/medicine/create");
		mv.addObject("medicine", new Medicine());
		mv.addObject("companys", companyDao.gets());
		mv.addObject("categorys", categoryDao.gets());
		
		return mv;
	}

	@PostMapping("/admin/medicine/create")
	public ModelAndView create(Medicine model,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin/medicine/create");
		try {
			String companyId = request.getParameter("CompanyId");
			String userId = request.getParameter("UserId");
			String categoryId = request.getParameter("CategoryId");
			model.setName(model.getName());
			model.setAvailable(model.isAvailable());
			model.setPrescribed(model.isPrescribed());
			model.setUnit(model.getUnit());
			model.setReorderLevel(model.getReorderLevel());
			model.getUser().setId(Util.toInt(userId));
			model.getCategory().setId(Util.toInt(categoryId));
			model.getCompany().setId(Util.toInt(companyId));
			System.out.println("Company"+model.getCompany().getId());
			System.out.println("User:"+model.getUser().getId());
			mv.addObject("message", medicineDao.add(model) ? "Added" : "Can't add");
		} catch (Exception ex) {
			mv.addObject("message", "Error.. " + ex);
		}
		mv.addObject("medicine", model);
		return mv;
	}

	@GetMapping("/admin/medicine/display/{id}")
	public ModelAndView display(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("shop/medicine/display");
		Medicine medicine = new Medicine(id);
		medicine = medicineDao.get(medicine);
		if (medicine != null){
			mv.addObject("medicine", medicine);
			mv.addObject("company", companyDao.get(new Company(medicine.getCompany().getId())));
			mv.addObject("category", categoryDao.get(new Category(medicine.getCategory().getId())));
		}
		return mv;
	}

	@GetMapping("/admin/medicine/edit/{id}")
	public ModelAndView edit(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("admin/medicine/edit");
		Medicine medicine = new Medicine(id);
		medicine = medicineDao.get(medicine);
		if (medicine == null)
			mv.setView(new RedirectView("/admin/medicine"));
		else{
			mv.addObject("medicine", medicine);
			mv.addObject("companys", companyDao.gets());
			mv.addObject("categorys", categoryDao.gets());
		}
		return mv;
	}

	@PostMapping("/admin/medicine/edit")
	public ModelAndView edit(Medicine model,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin/medicine/edit");
		try {
			String companyId = request.getParameter("CompanyId");
			String userId = request.getParameter("UserId");
			String categoryId = request.getParameter("CategoryId");
			model.setName(model.getName());
			model.setAvailable(model.isAvailable());
			model.setPrescribed(model.isPrescribed());
			model.setUnit(model.getUnit());
			model.setReorderLevel(model.getReorderLevel());
			model.getUser().setId(Util.toInt(userId));
			model.getCategory().setId(Util.toInt(categoryId));
			model.getCompany().setId(Util.toInt(companyId));
			mv.addObject("message", medicineDao.update(model) ? "Update" : "Can't update");
		} catch (Exception ex) {
			mv.addObject("message", "Error.. " + ex);
		}
		mv.addObject("medicine", model);
		return mv;
	}

	@GetMapping("/admin/medicine/delete/{id}")
	public ModelAndView delete(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("admin/medicine/delete");
		Medicine medicine = new Medicine(id);
		medicine = medicineDao.get(medicine);
		if (medicine == null)
			mv.setView(new RedirectView("/admin/medicine"));
		else {
			mv.addObject("medicine", medicineDao.get(new Medicine(id)));
			mv.addObject("company", companyDao.get(new Company(medicine.getCompany().getId())));
			mv.addObject("category", categoryDao.get(new Category(medicine.getCategory().getId())));
		}
		return mv;
	}

	@PostMapping("/admin/medicine/delete")
	public ModelAndView delete(Medicine model) {
		ModelAndView mv = new ModelAndView("admin/medicine/delete");
		try {
			if (medicineDao.delete(model))
				mv.setView(new RedirectView("/admin/medicine"));
			else
				mv.addObject("message", "Please remove child record first");
		} catch (Exception ex) {
			mv.addObject("message", "Error.. " + ex);
		}
		mv.addObject("medicine", model);
		return mv;
	}

}
