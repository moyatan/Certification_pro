package Certification.app.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import Certification.app.model.Account;
import Certification.app.model.Articles;
import Certification.app.model.Comments;
import Certification.app.repository.ArticlesRepository;
import Certification.app.repository.CategoryRepository;
import Certification.app.repository.CommentsRepository;

import Certification.app.repository.UserRepository;
import Certification.app.service.ArticlesService;
import Certification.app.service.CategoryCreate;
import Certification.app.service.AccountCheckService;

@Controller
public class HomeController {
	private long  categoryId = 0;
	
	@Autowired
	ArticlesRepository art;

	@Autowired
	UserRepository userrepository;

	@Autowired
	ArticlesService articlesService;

	@Autowired
	CategoryCreate categoryCreate;

	@Autowired
	AccountCheckService accountCheck;

	@Autowired
	CommentsRepository commentRepository;
	
	@Autowired
	UserRepository userRepository;



	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String getHome(Model model,
			Pageable pageable) {
		categoryId = 0;
		String URL = "";
		Map<Long, String> categoryList = categoryCreate.createMap();
		model.addAttribute("categoryList", categoryList);
		Account account = accountCheck.checkAuthentication();//認証されてるユーザーのチェック
		if (account != null) {
			URL = "userhome";
		} else {
			URL = "gesthome";
		}
		Page<Articles> articlesList = articlesService.articleListByNewOrder(pageable);
		model.addAttribute("articlesList",articlesList);
		return URL;
	}

	
	//記事のソート
	@RequestMapping(value = { "/sortOrder" }, method = RequestMethod.GET)
	public String sort(@RequestParam("sortName") String sortName, Model model, Pageable pageable) {
		Page<Articles> articlesList = null;
		if (sortName.equals("new")) {
			if(this.categoryId == 0) {
				articlesList = articlesService.articleListByNewOrder(pageable);
			}else {
			articlesList = articlesService.articleListByNewOrder_category(pageable,categoryId);
			}

		} else if (sortName.equals("popular")) {
			if(this.categoryId == 0) {
				articlesList = articlesService.articleListByOrderPopular(pageable);
			}else {
			articlesList = articlesService.articleListByOrderPopular_category(pageable,categoryId);
			}
			
		}else if(sortName.equals("favorite")) {
			Account account = accountCheck.checkAuthentication();
			articlesList = articlesService.articlesListByFavoriteSort(pageable, account.getId());
			
		}
		model.addAttribute("articlesList",articlesList);
		return "gesthome :: articleList";
	}
	

	//カテゴリ検索
	@RequestMapping(value = { "/categorySearch" }, method = RequestMethod.GET)
	public String CategorySearch(@RequestParam("categoryId") long categoryId, 
			Model model,
			Pageable pageable) {
		this.categoryId = categoryId;
		Page<Articles> articlesList = articlesService.articlesListByCategorySearch(pageable, categoryId);
		model.addAttribute("articlesList",articlesList);
		return "gesthome :: articleList";

	}
	//タイトル検索
	@RequestMapping(value = { "/titleSearch"}, method = RequestMethod.GET)
	public String titleSearch(@RequestParam("title") String title, Model model, Pageable pageable) {
		Page<Articles> articlesList = null;
		if (title.length() > 0) {
			articlesList = articlesService.getAllArticlesText(pageable, title);
		} else {
			articlesList = articlesService.articleListByNewOrder(pageable);
		}
		model.addAttribute("articlesList",articlesList);
		return "gesthome :: articleList";
	}
	
	public void favoriteCount(Page<Articles> articlesList,Model model) {
		for(Articles articles : articlesList) {
			System.out.println(articles.getId());
		}
		
	}
}