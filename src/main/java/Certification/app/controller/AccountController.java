package Certification.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import Certification.app.groups.GroupOrder;
import Certification.app.mail.MailSend;
import Certification.app.model.Account;
import Certification.app.model.CopyAccount;
import Certification.app.repository.CopyAccountRepository;
import Certification.app.repository.UserRepository;
import Certification.app.service.AccountCheckService;
import Certification.app.service.AccountService;
import Certification.app.service.UserCheckService;

@Controller
public class AccountController {

	@Autowired
	AccountService accountService;

	@Autowired
	UserCheckService userCheck;
	
	@Autowired
	AccountCheckService accountCheck;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CopyAccountRepository cprepository;
	
	@Autowired
	MailSend send;

	@RequestMapping(value = {"/signup"}, method = RequestMethod.GET)
	public String getSignup() {
		Account account = accountCheck.checkAuthentication();
		if(account != null) {
			return "logout";
		}
		return "new";
	}

	@RequestMapping(value = {"/signup"}, method = RequestMethod.POST)
	public String postSignup(Model model, @ModelAttribute("account") @Validated(GroupOrder.class) CopyAccount cpAccount,
			BindingResult result, @RequestParam("rePass") String rePass) {
		List<String> errorList = new ArrayList<String>();
		model.addAttribute("validationError",errorList);
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());

			}
			
			return "new";
		}
		// パスワード再確認用
		if (!rePass.equals(cpAccount.getPassword())) {
			errorList.add("パスワードが不一致です");
			return "new";
		}else {
		Account account = userRepository.findByEmail(cpAccount.getEmail());
		if(account == null) {
			UUID uuid =UUID.randomUUID();
			cpAccount.setUuid(uuid.toString());
			cprepository.saveAndFlush(cpAccount);
	    	send.sendMail(cpAccount);
			return "redirect:signup";
		}
		errorList.add("すでに登録されているアカウントです");
		return "new";
	}
	}
}