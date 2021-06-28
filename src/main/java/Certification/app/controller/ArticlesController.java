package Certification.app.controller;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import Certification.app.groups.GroupOrder;
import Certification.app.model.Account;
import Certification.app.model.Articles;
import Certification.app.model.CategoryModel;
import Certification.app.repository.ArticlesRepository;
import Certification.app.repository.CategoryRepository;
import Certification.app.service.CategoryCreate;
import Certification.app.service.LoginCheckService;

@Controller
public class ArticlesController {
	
	@Autowired
	ArticlesRepository articlesrepository;
	
	@Autowired
	CategoryCreate categorycreate;
	
	@Autowired
	LoginCheckService logincheck;
	
	
	@RequestMapping(value="articles",method=RequestMethod.GET)
	public String getArticles(Model model) {
		Map<Long,String> categoryMap = categorycreate.categorycreate();
		model.addAttribute("select",categoryMap);
		
		return "articles";
	}
	
	@RequestMapping(value="articles",method=RequestMethod.POST)
	public String postArticles(
			@ModelAttribute("articles")@Validated(GroupOrder.class)Articles articles,
			BindingResult result,
			@RequestParam("category")String id) {
		if(result.hasErrors()) {
			String str = "";
			for(ObjectError error : result.getAllErrors()) {
				str += error.getDefaultMessage();
			}
			System.out.println(str);
			return "error";
		}
		System.out.println(id);
		Timestamp time = new Timestamp(System.currentTimeMillis());
		Account account = logincheck.LoginCheck();
		articles.setAccount(account);
		articles.setCreated_at(time);
		articlesrepository.saveAndFlush(articles);
		return "redirect:articles";
		
	}
	

}