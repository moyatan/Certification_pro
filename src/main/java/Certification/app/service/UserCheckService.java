package Certification.app.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Certification.app.mail.MailSend;
import Certification.app.model.Account;
import Certification.app.model.CopyAccount;
import Certification.app.repository.CopyAccountRepository;
import Certification.app.repository.UserRepository;

@Service
@Transactional
public class UserCheckService {
	
	@Autowired
	MailSend send;
	
	@Autowired
	UserRepository userrepository;
	
	@Autowired
	CopyAccountRepository cprepository;
	
	public String check(CopyAccount cpAccount) {
		Account account = userrepository.findByEmail(cpAccount.getEmail());
		if(account == null) {
		UUID uuid =UUID.randomUUID();
		cpAccount.setUuid(uuid.toString());
		cprepository.saveAndFlush(cpAccount);
    	send.sendMail(cpAccount);
		return "redirect:";
		}
	
		System.out.println("アカウントがありました");
	return "redirect:login";
	}
}

