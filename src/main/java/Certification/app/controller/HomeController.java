package Certification.app.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import Certification.app.model.Account;
import Certification.app.model.Articles;
import Certification.app.repository.ArticlesRepository;
import Certification.app.repository.CategoryRepository;
import Certification.app.repository.UserRepository;
import Certification.app.service.ArticlesService;
import Certification.app.service.CategoryCreate;
import Certification.app.service.LoginCheckService;

@Controller
public class HomeController {
	
	@Autowired
	ArticlesRepository art;
	
	@Autowired
	UserRepository userrepository;
	
	@Autowired
	ArticlesService articlesservice;
	
	@Autowired
	CategoryCreate categorycreate;
	
	@Autowired
	LoginCheckService logincheck;
	
	@Autowired
	CategoryRepository cm;
	
	//URLのパラメーターが？で書かれてる時はRequestParamを使う
	@RequestMapping(value={"/"},method=RequestMethod.GET)
	public String getHome(Model model,
			@RequestParam(name="id",required = false) String articlesId,
			Pageable pageable) {
		String URL ="";
		Map<Long,String> categoryMap = categorycreate.categorycreate();
		model.addAttribute("select",categoryMap);
		Account account = logincheck.LoginCheck();
		System.out.println(account);
		if(account !=null) {
			URL = "userhome";
		}else {
			URL = "gesthome";
		}
		
		Page<Articles> articlesList = articlesservice.getAllArticlesOrder_new(pageable);		setPage(model,articlesList);

		
		//記事のIDがクリックされてた場合実行
		if(articlesId != null && !articlesId.isEmpty()) {

			long id = Long.parseLong(articlesId);
			Articles article = articlesservice.getAtricle(id);
			System.out.println(article.getTitle());
			model.addAttribute("articles",article);
			return "show";
		}

		return URL;
	}	

	@RequestMapping(value= {"/sort"},method=RequestMethod.GET)
	public String sort(@RequestParam("sortid") String sort,
			Model model,
			Pageable pageable) {
		System.out.println(sort);
		if(sort.equals("new")) {
			Page<Articles> articlesList =  articlesservice.getAllArticlesOrder_new(pageable);
			setPage(model,articlesList);
			return "gesthome :: sample";
			
		}else if(sort.equals("popular")) {
			Page<Articles> articlesList = articlesservice.getAllArticlesOrder_popular(pageable);
			setPage(model,articlesList);
			return "gesthome :: sample";
		}
		return "";
		
	}
	@RequestMapping(value={"/category"},method=RequestMethod.GET)
	public String CategorySearch(@RequestParam("searchid")long num,
			Model model,
			Pageable pageable) {
		Page<Articles> articlesList =articlesservice.getAllArticlesOrder_search(pageable,num);
		setPage(model,articlesList);
		return "gesthome :: sample";
		
	}
	
	@RequestMapping(value={"/cc"},method = RequestMethod.GET)
	public String cc(@RequestParam("text") String text,
			Model model,
			Pageable pageable) {
		Page<Articles> articlesList =null;
		if(text.length() > 0) {
			articlesList = articlesservice.getAllArticlesText(pageable, text);
		}else {
			articlesList = articlesservice.getAllArticlesOrder_new(pageable);
		}
		setPage(model,articlesList);
		System.out.println("テキスト");
		System.out.println(text);
		return "gesthome :: sample";
	}
	public  void setPage(Model model,Page<Articles> articlesList) {
		System.out.println("aaa");
		model.addAttribute("articleslist",articlesList);
		model.addAttribute("url", "/");
		model.addAttribute("words",articlesList.getContent());
	}
}