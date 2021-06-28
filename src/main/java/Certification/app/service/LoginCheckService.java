package Certification.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Certification.app.model.Account;
import Certification.app.repository.UserRepository;

@Service
public class LoginCheckService {
	
	@Autowired
	UserRepository userrepository;
	
	public Account LoginCheck() {
	String usermail = SecurityContextHolder.getContext().getAuthentication().getName();
	Account account = userrepository.findByEmail(usermail);
	
	return account;
	}

}
