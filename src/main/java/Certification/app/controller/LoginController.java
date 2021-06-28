package Certification.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Certification.app.model.Account;

@Controller
public class LoginController {
	
	@Autowired
	
	
    
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String getlogin() {
		//ログインしてるユーザーかどうか確認する

		return "login";
		
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String postlogin() {
		return "login";
	}
}
