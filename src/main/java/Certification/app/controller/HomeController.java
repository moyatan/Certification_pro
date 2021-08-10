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
	
	private String title;



	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String getHome(Model model,
			Pageable pageable,@RequestParam(name = "page", required = false)String page) {
		if(page == null) {
		title="";
		}
		categoryId = 0;
		Page<Articles> articlesList = null;
		String URL = "";
		Map<Long, String> categoryList = categoryCreate.createMap();
		model.addAttribute("categoryList", categoryList);
		Account account = accountCheck.checkAuthentication();//認証されてるユーザーのチェック
		if (account != null) {
			URL = "userhome";
		} else {
			URL = "gesthome";
		}
		if(title != null && !title.isEmpty()) {
			articlesList = articlesService.getAllArticlesText(pageable, title);
		}else {
		articlesList = articlesService.articleListByNewOrder(pageable);
		}
		pageSet(model,articlesList);
		model.addAttribute("title",title);
		return URL;
	}

	
	//記事のソート
	@RequestMapping(value = { "/sortOrder" }, method = RequestMethod.GET)
	public String sort(@RequestParam("sortName") String sortName, Model model, Pageable pageable) {
		Page<Articles> articlesList = null;
		if(title != null) {
			if (sortName.equals("new")) {
				if(this.categoryId == 0) {
					articlesList = articlesService.articleListByNewOrder_title(pageable,title);
				}else {
				articlesList = articlesService.articleListByNewOrder_category_title(pageable,categoryId,title);
				}

			} else if (sortName.equals("popular")) {
				if(this.categoryId == 0) {
					articlesList = articlesService.articleListByOrderPopular_title(pageable,title);
				}else {
				articlesList = articlesService.articleListByOrderPopular_category_title(pageable,categoryId,title);
				}
			}
		}else {
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
			
		}
		}
		if(sortName.equals("favorite")) {
			Account account = accountCheck.checkAuthentication();
			articlesList = articlesService.articlesListByFavoriteSort(pageable, account.getId());
			
		}
		pageSet(model,articlesList);
		return "gesthome :: articleList";
	}
	

	//カテゴリ検索
	@RequestMapping(value = { "/categorySearch" }, method = RequestMethod.GET)
	public String CategorySearch(@RequestParam("categoryId") long categoryId, 
			Model model,
			Pageable pageable) {
		this.categoryId = categoryId;
		Page<Articles> articlesList = articlesService.articlesListByCategorySearch(pageable, categoryId);
		pageSet(model,articlesList);
		return "gesthome :: articleList";

	}
	//タイトル検索
	@RequestMapping(value = { "/titleSearch"}, method = RequestMethod.GET)
	public String titleSearch(@RequestParam("title") String title, Model model, Pageable pageable) {
		Page<Articles> articlesList = null;
		if (title.length() > 0) {
			this.title=title;
			articlesList = articlesService.getAllArticlesText(pageable, title);
		} else {
			this.title="";
			articlesList = articlesService.articleListByNewOrder(pageable);
		}
		pageSet(model,articlesList);
		return "gesthome :: articleList";
	}
	
	public void favoriteCount(Page<Articles> articlesList,Model model) {
		for(Articles articles : articlesList) {
			System.out.println(articles.getId());
		}
		
	}
	public void pageSet(Model model,Page<Articles> articlesList) {
		model.addAttribute("page",articlesList);
		model.addAttribute("articlesList",articlesList.getContent());
		model.addAttribute("url","/");
	}
}