package Certification.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Certification.app.model.Account;
import Certification.app.repository.UserRepository;

@Service
public class AccountCheckService {
	
	@Autowired
	UserRepository userRepository;
	
	public Account checkAuthentication() {
	String userMail = SecurityContextHolder.getContext().getAuthentication().getName();
	Account account = userRepository.findByEmail(userMail);
	
	return account;
	}

}
