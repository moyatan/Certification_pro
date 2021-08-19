package Certification.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import Certification.app.model.Account;
import Certification.app.model.Articles;
import Certification.app.repository.UserRepository;
import Certification.app.service.AccountCheckService;
import Certification.app.service.ArticlesService;

@Controller
public class UserPageController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AccountCheckService accountCheck;
	
	@Autowired
	ArticlesService articlesService;
	
	@RequestMapping(value={"/userPage"},method=RequestMethod.GET)
	public String getUserPage(Model model,@RequestParam(name="id",required=false)long userAccountId) {
		Account userAccount = userRepository.findById(userAccountId);
		Account myAccount = accountCheck.checkAuthentication();
		List<Articles> articlesList = articlesService.articlesList(userAccountId);
		
		model.addAttribute("articlesList",articlesList);
		model.addAttribute("myAccount",myAccount);
		model.addAttribute("userAccount",userAccount);
		return "userPage";
		
	}

}
