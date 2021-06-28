package Certification.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import Certification.app.model.Account;
import Certification.app.model.CopyAccount;
import Certification.app.repository.CopyAccountRepository;
import Certification.app.service.AccountService;

@Controller
public class SuccessController {
	
	@Autowired
	CopyAccountRepository cprepository;
	
	@Autowired
	AccountService accountservice;
	
	
	
	@RequestMapping(value="/success",method=RequestMethod.GET)
	public String getSuccess(@RequestParam(name="id",required = false) String uuid,
			Model model) {
		CopyAccount cpAccount = cprepository.findByAccountCheck(uuid);
		System.out.println("ここまで");
		if(cpAccount != null) {
			accountservice.create(cpAccount,cpAccount.getPassword());
			System.out.println("アカウント登録完了");
			model.addAttribute("success","登録完了しました");
			return "redirect:";
		}
		return "redirect:signup";
		
		
	}

}
