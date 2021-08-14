package Certification.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Certification.app.model.Account;
import Certification.app.repository.UserRepository;
import Certification.app.service.AccountCheckService;

@Controller
public class ProfileEditController {
	
	@Autowired
	AccountCheckService accountCheck;
	
	@Autowired
	UserRepository accountRepository;
	
	@RequestMapping(value={"/profileEdit"},method=RequestMethod.GET)
	public String getProfileEdit(Model model) {
		Account account = accountCheck.checkAuthentication();
		model.addAttribute("account", account);
		return "profileEdit";
	}
	
	@RequestMapping(value={"/profileEdit"},method=RequestMethod.POST)
    public String test(
    		@RequestParam(name="profileImage",required = false)String profileImage,
    		@RequestParam(name="name")String name,
    		@RequestParam(name="profile",required=false)String profile,
    		Model model) {
    	Account account = accountCheck.checkAuthentication();
    	account.setProfileImage(profileImage);
    	account.setName(name);
    	account.setProfile(profile);
    	accountRepository.saveAndFlush(account);
    	return "redirect:mypage";
    }

}
