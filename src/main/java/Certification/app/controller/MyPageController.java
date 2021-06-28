package Certification.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Certification.app.model.Articles;
import Certification.app.repository.ArticlesRepository;

@Controller
public class MyPageController {
	
	@Autowired
	ArticlesRepository articlesrepository;
	
	@RequestMapping(value="/mypage",method=RequestMethod.GET)
	public String get(Model model) {
//		List<Articles> ar = articlesrepository.findAll();
//		model.addAttribute("articleslist",ar);
		return "userpage";
		
	}

}
