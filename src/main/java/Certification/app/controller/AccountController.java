package Certification.app.controller;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
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
import Certification.app.model.CopyAccount;
import Certification.app.service.AccountService;
import Certification.app.service.UserCheckService;



@Controller
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	UserCheckService usercheck;
	
	
	@RequestMapping(value="signup",method=RequestMethod.GET)
	public String getsignup() {
		return "new";
	}
	
	@RequestMapping(value="signup",method=RequestMethod.POST)
	public String postsignup(Model model,
			@ModelAttribute("account")@Validated(GroupOrder.class) CopyAccount cpAccount,BindingResult result,
			@RequestParam("repass")String repass) {
		if(result.hasErrors()) {
			String str = "";
			for(ObjectError error : result.getAllErrors()) {
				str += error.getDefaultMessage();
				
			}
			System.out.println(str);
		return "redirect:signup";
		}
		//パスワード再確認用
		if(!repass.equals(cpAccount.getPassword())) {
			model.addAttribute("errorMessage","パスワードが一致しませんでした");
			System.out.println("パスワードが不一致");
			return "redirect:signup";
		}
		
		return usercheck.check(cpAccount);
	}
	

}
