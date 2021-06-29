package Certification.app.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import Certification.app.model.Account;
import Certification.app.model.Articles;
import Certification.app.model.Comments;
import Certification.app.repository.ArticlesRepository;
import Certification.app.repository.CategoryRepository;
import Certification.app.repository.CommentsRepository;
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
	
	@Autowired
	CommentsRepository commentrepository;
	
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
			System.out.println("記事のタイトル" + article.getTitle());
			List<Comments> list = commentrepository.findByIdAll(id);
			model.addAttribute("comments",list);
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
	
	@Transactional
	@RequestMapping(value= {"/comment"},method=RequestMethod.GET)
	public String comment(@RequestParam("text") String text,@RequestParam("hidden")long hidden,Model model) {
		System.out.println(text);
		System.out.println(hidden);
		Articles articles = art.findById(hidden);
		Account account = logincheck.LoginCheck();
		Comments comments = new Comments();
		comments.setAccount(account);
		comments.setContent(text);
		comments.setArticles(articles);
		Timestamp time = new Timestamp(System.currentTimeMillis());
		comments.setCreated_at(time);
		try {
		commentrepository.saveAndFlush(comments);
		System.out.println("無事完了");
		List<Comments> list = commentrepository.findByIdAll(articles.getId());
		model.addAttribute("comments",list);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "show :: comment";
	}
	
	public  void setPage(Model model,Page<Articles> articlesList) {
		model.addAttribute("articleslist",articlesList);
		model.addAttribute("url", "/");
		model.addAttribute("words",articlesList.getContent());
	}
}