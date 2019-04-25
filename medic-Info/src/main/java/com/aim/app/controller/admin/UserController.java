package com.aim.app.controller.admin;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.aim.app.dao.RoleDao;
import com.aim.app.dao.UserDao;
import com.aim.app.model.Role;
import com.aim.app.model.User;
import com.aim.app.util.Util;

@Controller
public class UserController {

	private UserDao userDao;
	private RoleDao roleDao;
	private JdbcTemplate jdbc;
	
	public UserController(UserDao userDao, RoleDao roleDao,JdbcTemplate jdbc) {
		this.userDao = userDao;
		this.roleDao = roleDao;
		this.jdbc = jdbc;
	}

	@RequestMapping("/user")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("user/index");
		
		mv.addObject("users", userDao.gets());
		
		return mv;
	}

	@RequestMapping(value = "/user/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView mv = new ModelAndView("user/create");
		mv.addObject("user", new User());
		mv.addObject("roles", roleDao.gets());
		return mv;
	}

//	@PostMapping("/user/create")
	@RequestMapping(value = "/user/create", method = RequestMethod.POST)
	public ModelAndView create(MultipartFile file, User model, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("user/create");
		try {
			String roleId = request.getParameter("RoleId");
			model.setEmail(model.getEmail());
			model.setPass(model.getPass());
			model.getRole().setId(Util.toInt(roleId));
			model.setActive(model.isActive());
			model.setLastName(model.getLastName());
			model.setFirstName(model.getFirstName());
			model.setAddress(model.getAddress());
			model.setCity(model.getCity());
			model.setGender(model.getGender());
			model.setPhone(model.getPhone());
			byte[] byt = file.getBytes();
			System.out.println(byt);
			model.setPhoto(byt);
			model.setLatCor(model.getLatCor());
			model.setLongCor(model.getLongCor());
			mv.addObject("message", userDao.add(model) ? "Added" : "Can't add");
		} catch (Exception ex) {
			mv.addObject("message", "Error.. " + ex);
		}
		mv.addObject("user", model);
		return mv;
	}

	@GetMapping("/user/display/{id}")
	public ModelAndView display(@PathVariable Integer id) throws IOException {
		ModelAndView mv = new ModelAndView("user/display");
		User user = new User(id);
		user = userDao.get(user);
		if (user != null) {
			mv.addObject("user", user);
//			mv.addObject("profile", bImage2);
			mv.addObject("roles", roleDao.gets(user.getRole().getId()));
		}
		return mv;
	}

	@GetMapping("/user/edit/{id}")
	public ModelAndView edit(@PathVariable Integer id) throws IOException {
		ModelAndView mv = new ModelAndView("user/edit");
		User user = new User(id);
		user = userDao.get(user);
		if (user == null)
			mv.setView(new RedirectView("/user"));
		else {
			mv.addObject("user", user);
			
			mv.addObject("roles", roleDao.gets());
		}
		return mv;
	}

//	user_id(pk)	email	pass	role_id	active
// user_id	last_name	first_name	address	city	gender	phone	photo	lat_cor	long_cor
	@PostMapping("/user/edit")
	public ModelAndView edit(User model, HttpServletRequest request, MultipartFile file) {
		ModelAndView mv = new ModelAndView("user/edit");
		try {
			String roleId = request.getParameter("RoleId");
			model.setEmail(model.getEmail());
			model.setPass(model.getPass());
			model.getRole().setId(Util.toInt(roleId));
			model.setActive(model.isActive());
			model.setLastName(model.getLastName());
			model.setFirstName(model.getFirstName());
			model.setAddress(model.getAddress());
			model.setCity(model.getCity());
			model.setGender(model.getGender());
			model.setPhone(model.getPhone());
			byte[] byt = file.getBytes();
			System.out.println(byt);
			model.setPhoto(byt);
			model.setLatCor(model.getLatCor());
			model.setLongCor(model.getLongCor());
			mv.addObject("message", userDao.update(model) ? "Update" : "Can't update");
		} catch (Exception ex) {
			mv.addObject("message", "Error.. " + ex);
		}
		mv.addObject("user", model);
		return mv;
	}

	@GetMapping("/user/delete/{id}")
	public ModelAndView delete(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("user/delete");
		User user = new User(id);
		user = userDao.get(user);
		if (user == null)
			mv.setView(new RedirectView("/user"));
		else {
			mv.addObject("user", userDao.get(new User(id)));
			mv.addObject("role", roleDao.get(new Role(user.getRole().getId())));
		}
		return mv;
	}

	@PostMapping("/user/delete")
	public ModelAndView delete(User model) {
		ModelAndView mv = new ModelAndView("user/delete");
		try {
			if (userDao.delete(model))
				mv.setView(new RedirectView("/user"));
			else
				mv.addObject("message", "Please remove child record first");
		} catch (Exception ex) {
			mv.addObject("message", "Error.. " + ex);
		}
		mv.addObject("user", model);
		return mv;
	}
	@RequestMapping("/photo/{id}")
	public void getPhoto(@PathVariable Integer id, HttpServletResponse resp) throws IOException {
		
		List<byte[]> list = jdbc.query("select photo from user_profile where user_id=?",new RowMapper<byte[]>() {
		@Override
		public byte[] mapRow(ResultSet rs, int rowNum) throws SQLException {
			System.out.println(rs.getBytes(1));
			return rs.getBytes(1);
		}
		},id); 
		if(!list.isEmpty()) {
	        resp.setContentType("image/jpeg");
	        resp.getOutputStream().write( list.get(0) );
	        resp.getOutputStream().flush(); 
	     }  
	}
}
