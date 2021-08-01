package Certification.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
	CopyAccountRepository cpRepository;
	
	@Autowired
	AccountService accountService;
	
	
	@Transactional
	@RequestMapping(value="/success",method=RequestMethod.GET)
	public String getSuccess(@RequestParam(name="uuid",required = false) String uuid,
			Model model) {
		//uuidが一致したらアカウント登録
		CopyAccount cpAccount = cpRepository.findByCopyAccountCheck(uuid);
		if(cpAccount != null) {
			accountService.create(cpAccount,cpAccount.getPassword());
			System.out.println("アカウント登録完了");
			model.addAttribute("success","登録完了しました");
			return "login";
		}
		model.addAttribute("errorMessage","アカウント登録に失敗しました");
		return "redirect:signup";
		
		
	}

}
