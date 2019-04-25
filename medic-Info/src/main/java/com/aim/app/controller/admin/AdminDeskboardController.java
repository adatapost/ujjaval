package com.aim.app.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.aim.app.SessionWrapper;
import com.aim.app.dao.CategoryDao;
import com.aim.app.model.Category;

@Controller
public class AdminDeskboardController {
	@RequestMapping("/admin")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin/deskboard/index");
		mv.addObject("user", SessionWrapper.user(request.getSession()));
		return mv;
	}
}
