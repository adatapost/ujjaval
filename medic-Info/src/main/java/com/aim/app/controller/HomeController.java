package com.aim.app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.aim.app.dao.MedicineDao;
import com.aim.app.dao.RoleDao;
import com.aim.app.dao.UserDao;
import com.aim.app.model.Medicine;
import com.aim.app.model.Supplier;
import com.aim.app.model.User;
import com.aim.app.util.Util;
@Controller
public class HomeController {
	private UserDao userDao;
	private RoleDao roleDao;
	private JdbcTemplate jdbc;
	private MedicineDao medicineDao;
	
	public HomeController(UserDao userDao, RoleDao roleDao,JdbcTemplate jdbc,MedicineDao medicineDao) {
		this.userDao = userDao;
		this.roleDao = roleDao;
		this.medicineDao = medicineDao;
		this.jdbc = jdbc;
	}
	
	
	@RequestMapping("/")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/home/index");
		mv.addObject("title","Home");
		return mv;
	}
	@RequestMapping("/about")
	public String index() {
		return "/home/about";
	}
	@RequestMapping("/contact")
	public String contact() {
		return "/home/contact";
	}

	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("/home/login");
		mv.addObject("title","login");
		return mv;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout() {
		ModelAndView mv = new ModelAndView("/home/login");
		mv.addObject("title","login");
		return mv;
	}
	@GetMapping("/registration")
	public ModelAndView registration() {
		ModelAndView mv = new ModelAndView("/home/registration");
		mv.addObject("user", new User());
		
		return mv;
	}
	@PostMapping("/registration")
	public ModelAndView registration(User model, MultipartFile file) {
		ModelAndView mv = new ModelAndView("/home/registration");
		try {
			if(file!=null)
			model.setPhoto(file.getBytes());
			model.getRole().setId(model.getRoleId());
			model.setActive(true);
			if(userDao.add(model))
			mv.addObject("message", "User has been created successfully!");
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			mv.addObject("message", "Can't create user account! Please verify the email and other input");

		}
		mv.addObject("user", model);
		return mv;
	}
	
	@GetMapping("/recover")
	public ModelAndView recover() {
		ModelAndView mv = new ModelAndView("/home/recover");
		mv.addObject("user", new User());
		
		return mv;
	}
	@PostMapping("/recover")
	public ModelAndView recover(User model) {
		ModelAndView mv = new ModelAndView("/home/recover");
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("user", model);
		return mv;
	}

}
