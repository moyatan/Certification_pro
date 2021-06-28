package Certification.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ContactusController {
	
	@RequestMapping(value="/contactus",method=RequestMethod.GET)
	public String getContactus() {
		
		return"contactus";
	}

	@RequestMapping(value="/contactus",method=RequestMethod.POST)
	public String postContactus() {
		
		return"redirect:";
	}
}
