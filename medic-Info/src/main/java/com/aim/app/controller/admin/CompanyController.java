package com.aim.app.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.aim.app.dao.CompanyDao;
import com.aim.app.model.Company;

@Controller
public class CompanyController {

	private CompanyDao companyDao;

	public CompanyController(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	@RequestMapping("/admin/company")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin/company/index");
		mv.addObject("companys", companyDao.gets());
		mv.addObject("title","company");
		return mv;
	}

	@GetMapping("/admin/company/create")
	public ModelAndView create() {
		ModelAndView mv = new ModelAndView("admin/company/create");
		mv.addObject("company", new Company());
		mv.addObject("title","company");
		return mv;
	}

	@PostMapping("/admin/company/create")
	public ModelAndView create(Company model) {
		ModelAndView mv = new ModelAndView("admin/company/create");
		try {

			model.setName(model.getName());
			model.setAddress(model.getAddress());
			model.setCity(model.getCity());
			model.setWebsite(model.getWebsite());
			model.setPhone(model.getPhone());
			mv.addObject("message", companyDao.add(model) ? "Added" : "Can't add");
		} catch (Exception ex) {
			mv.addObject("message", "Error.. " + ex);
		}
		mv.addObject("title","company");
		mv.addObject("company", model);
		return mv;
	}

	@GetMapping("/admin/company/display/{id}")
	public ModelAndView display(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("admin/company/display");
		Company company = new Company(id);
		company = companyDao.get(company);
		mv.addObject("title","company");
		if (company != null)
			mv.addObject("company", company);
		return mv;
	}

	@GetMapping("/admin/company/edit/{id}")
	public ModelAndView edit(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("admin/company/edit");
		Company company = new Company(id);
		company = companyDao.get(company);
		mv.addObject("title","company");
		if (company == null)
			mv.setView(new RedirectView("/admin/company"));
		else
			mv.addObject("company", companyDao.get(new Company(id)));
		return mv;
	}

	@PostMapping("/admin/company/edit")
	public ModelAndView edit(Company model) {
		ModelAndView mv = new ModelAndView("admin/company/edit");
		try {
			model.setName(model.getName());
			model.setAddress(model.getAddress());
			model.setCity(model.getCity());
			model.setWebsite(model.getWebsite());
			model.setPhone(model.getPhone());
			mv.addObject("message", companyDao.update(model) ? "Update" : "Can't update");
		} catch (Exception ex) {
			mv.addObject("message", "Error.. " + ex);
		}
		mv.addObject("title","company");
		mv.addObject("company", model);
		return mv;
	}

	@GetMapping("/admin/company/delete/{id}")
	public ModelAndView delete(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("admin/company/delete");
		Company company = new Company(id);
		company = companyDao.get(company);
		mv.addObject("title","company");
		if (company == null)
			mv.setView(new RedirectView("/admin/company"));
		else
			mv.addObject("company", companyDao.get(new Company(id)));
		return mv;
	}

	@PostMapping("admin/company/delete")
	public ModelAndView delete(Company model) {
		ModelAndView mv = new ModelAndView("/admin/company/delete");
		mv.addObject("title","company");
		try {
			if (companyDao.delete(model))
				mv.setView(new RedirectView("/admin/company"));
			else
				mv.addObject("message", "Please remove child record first");
		} catch (Exception ex) {
			mv.addObject("message", "Error.. " + ex);
		}
		mv.addObject("company", model);
		return mv;
	}

}
