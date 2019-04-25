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
import com.aim.app.model.Category;

@Controller
public class CategoryController {

	private CategoryDao categoryDao;

	public CategoryController(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@RequestMapping("/admin/category")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin/category/index");
		mv.addObject("categorys", categoryDao.gets());
		mv.addObject("title","category");
		return mv;
	}
	@GetMapping("/admin/category/create")
	public ModelAndView create() {
		   ModelAndView mv = new ModelAndView("admin/category/create");
		   mv.addObject("category",new Category());
		   mv.addObject("title","category");
		   return mv;
	   }
	
	@PostMapping("/admin/category/create")
	public ModelAndView create(Category model) {
		   ModelAndView mv = new ModelAndView("admin/category/create");
		   try {
			   
			   model.setName( model.getName() );
			    mv.addObject("message", categoryDao.add(model) ? "Added" : "Can't add");   
		   } catch(Exception ex) {
			   mv.addObject("message","Error.. " + ex);
		   }
		   mv.addObject("category",model);
		   mv.addObject("title","category");
		   return mv;
	   }
	
	@GetMapping("/admin/category/display/{id}")
	public ModelAndView display(@PathVariable Integer id) {
		   ModelAndView mv = new ModelAndView("admin/category/display");
		   Category category = new Category(id);
		   category = categoryDao.get(category);
		   mv.addObject("title","category");
		   if(category != null)
		        mv.addObject("category",category);
		   return mv;
	   }
	
	
	@GetMapping("/admin/category/edit/{id}")
	public ModelAndView edit(@PathVariable Integer id) {
		   ModelAndView mv = new ModelAndView("admin/category/edit");
		   Category category = new Category(id);
		   category = categoryDao.get(category);
		   mv.addObject("title","category");
		   if(category == null) 
			   mv.setView( new RedirectView("/admin/category"));
		   else 
		       mv.addObject("category",categoryDao.get(new Category(id)) );
		   return mv;
	   }
	
	@PostMapping("/admin/category/edit")
	public ModelAndView edit(Category model) {
		   ModelAndView mv = new ModelAndView("admin/category/edit");
		   try {
			   
			   model.setName(model.getName());
			    mv.addObject("message", categoryDao.update(model) ? "Update" : "Can't update");   
		   } catch(Exception ex) {
			   mv.addObject("message","Error.. " + ex);
		   }
		   mv.addObject("title","category");
		   mv.addObject("category",model);
		   return mv;
	   }
	
	
	
	@GetMapping("/admin/category/delete/{id}")
	public ModelAndView delete(@PathVariable Integer id) {
		   ModelAndView mv = new ModelAndView("admin/category/delete");
		   Category category = new Category(id);
		   category = categoryDao.get(category);
		   mv.addObject("title","category");
		   if(category == null) 
			   mv.setView( new RedirectView("/category"));
		   else 
		       mv.addObject("category",categoryDao.get(new Category(id)) );
		   return mv;
	   }
	
	@PostMapping("/admin/category/delete")
	public ModelAndView delete(Category model) {
		   ModelAndView mv = new ModelAndView("admin/category/delete");
		   mv.addObject("title","category");
		   try {
			   if(categoryDao.delete(model))
				   mv.setView(new RedirectView("/category"));
			   else 
				   mv.addObject("message","Please remove child record first");
		   } catch(Exception ex) {
			   mv.addObject("message","Error.. " + ex);
		   }
		   mv.addObject("category",model);
		   return mv;
	   }

}
