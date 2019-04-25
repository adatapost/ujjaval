package com.aim.app.controller.shop;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.aim.app.dao.DoctorDao;
import com.aim.app.model.Doctor;

@Controller
public class DoctorController {
	private DoctorDao doctorDao;

	public DoctorController(DoctorDao doctorDao) {
		this.doctorDao = doctorDao;
	}
	
	@RequestMapping("/shop/doctor")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("shop/doctor/index");
		mv.addObject("doctors", doctorDao.gets());
		return mv;
	}

	@GetMapping("/shop/doctor/create")
	public ModelAndView create() {
		ModelAndView mv = new ModelAndView("shop/doctor/create");
		mv.addObject("doctor", new Doctor());
		return mv;
	}

	@PostMapping("/shop/doctor/create")
	public ModelAndView create(Doctor model) {
		ModelAndView mv = new ModelAndView("shop/doctor/create");
		try {
			model.setName(model.getName());
			model.setAddress(model.getAddress());
			model.setPhone(model.getPhone());
			mv.addObject("message", doctorDao.add(model) ? "Added" : "Can't add");
		} catch (Exception ex) {
			mv.addObject("message", "Error.. " + ex);
		}
		mv.addObject("doctor", model);
		return mv;
	}

	@GetMapping("/shop/doctor/display/{id}")
	public ModelAndView display(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("shop/doctor/display");
		Doctor doctor = new Doctor(id);
		doctor = doctorDao.get(doctor);
		if (doctor != null)
			mv.addObject("doctor", doctor);
		return mv;
	}

	@GetMapping("/shop/doctor/edit/{id}")
	public ModelAndView edit(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("shop/doctor/edit");
		Doctor doctor = new Doctor(id);
		doctor = doctorDao.get(doctor);
		if (doctor == null)
			mv.setView(new RedirectView("/shop/doctor"));
		else
			mv.addObject("doctor", doctorDao.get(new Doctor(id)));
		return mv;
	}

	@PostMapping("/shop/doctor/edit")
	public ModelAndView edit(Doctor model) {
		ModelAndView mv = new ModelAndView("shop/doctor/edit");
		try {
			model.setName(model.getName());
			model.setAddress(model.getAddress());
			model.setPhone(model.getPhone());
			mv.addObject("message", doctorDao.update(model) ? "Update" : "Can't update");
		} catch (Exception ex) {
			mv.addObject("message", "Error.. " + ex);
		}
		mv.addObject("doctor", model);
		return mv;
	}

	@GetMapping("/shop/doctor/delete/{id}")
	public ModelAndView delete(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("shop/doctor/delete");
		Doctor doctor = new Doctor(id);
		doctor = doctorDao.get(doctor);
		if (doctor == null)
			mv.setView(new RedirectView("/shop/doctor"));
		else
			mv.addObject("doctor", doctorDao.get(new Doctor(id)));
		return mv;
	}

	@PostMapping("/shop/doctor/delete")
	public ModelAndView delete(Doctor model) {
		ModelAndView mv = new ModelAndView("shop/doctor/delete");
		try {
			if (doctorDao.delete(model))
				mv.setView(new RedirectView("/shop/doctor"));
			else
				mv.addObject("message", "Please remove child record first");
		} catch (Exception ex) {
			mv.addObject("message", "Error.. " + ex);
		}
		mv.addObject("doctor", model);
		return mv;
	}

}
