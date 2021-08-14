package Certification.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import Certification.app.model.Account;
import Certification.app.repository.UserRepository;

//SpringSecurityでDB接続するためのクラス
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	
//	@Autowired
//	SessionData sessiondata;
	
	@Autowired
    private UserRepository userRepository;
	
	//暗黙的に呼ばれるメソッド
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
		Account account = userRepository.findByEmail(email);
		if(account == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return account;
	}catch(UsernameNotFoundException e) {
		System.out.println("ユーザーが見つからない");
		throw new UsernameNotFoundException("user not found",e);
	}
}
}
