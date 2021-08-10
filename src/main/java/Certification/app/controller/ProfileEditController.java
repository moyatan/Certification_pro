package Certification.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Certification.app.model.Account;
import Certification.app.service.AccountCheckService;

@Controller
public class ProfileEditController {
	
	@Autowired
	AccountCheckService accountCheck;
	
	@RequestMapping(value={"/profileEdit"},method=RequestMethod.GET)
	public String getProfileEdit(Model model) {
		Account account = accountCheck.checkAuthentication();
		model.addAttribute("account", account);
		return "profileEdit";
	}

}
