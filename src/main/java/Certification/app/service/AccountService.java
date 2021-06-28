package Certification.app.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Certification.app.model.Account;
import Certification.app.model.CopyAccount;
import Certification.app.repository.UserRepository;

@Service
@Transactional
public class AccountService {
	
	@Autowired
	PasswordEncoder pass;
	
	@Autowired
	UserRepository user;
	
	public Account create(CopyAccount cpAccount,String rawPassword) {
		String enPass = pass.encode(rawPassword);
		System.out.println("ハッシュコード" + enPass);
		Account account = new Account();
		BeanUtils.copyProperties(cpAccount, account);
		account.setPassword(enPass);
         return user.saveAndFlush(account);
	}

}
