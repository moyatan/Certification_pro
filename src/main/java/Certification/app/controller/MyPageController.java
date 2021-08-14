package Certification.app.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;

import Certification.app.groups.GroupOrder;
import Certification.app.model.Account;
import Certification.app.model.Articles;
import Certification.app.model.CategoryModel;
import Certification.app.repository.ArticlesRepository;
import Certification.app.repository.CategoryRepository;
import Certification.app.service.AccountCheckService;
import Certification.app.service.ArticlesService;
import Certification.app.service.CategoryCreate;

@Controller
public class MyPageController {
	private Articles article;
	
	@Autowired
	CategoryCreate categoryCreate;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	AccountCheckService accountCheck;
	
	@Autowired
	ArticlesService articlesService;
	
	@Autowired
	ArticlesRepository articlesRepository;
	
	@RequestMapping(value={"/mypage"},method=RequestMethod.GET)
	public String getMyPage(Model model,Pageable pageable) {
		Account account = accountCheck.checkAuthentication();
		List<Articles>articlesList = articlesService.articlesList(account.getId());
		model.addAttribute("account",account);
		model.addAttribute("articlesList",articlesList);
		return "mypage";
	}
	
	@RequestMapping(value={"/edit"},method=RequestMethod.POST)
	public String postMyPage(Model model,@RequestParam("action")String action,@RequestParam("articleId")long articleId) {
		System.out.println("ここまで");
		article = articlesRepository.findById(articleId);
		System.out.println("articleId:" + articleId);
		if(action.equals("edit")) {
			Map<Long, String> categoryList = categoryCreate.createMap();
			model.addAttribute("categoryList", categoryList);
			model.addAttribute("article",article);
			return "edit";
			
		}else if(action.equals("delete")) {
			System.out.println(article.getId());
			article.setDelete_flag(1);
			articlesRepository.saveAndFlush(article);
		}
		System.out.println("ここまできてる");
		return "redirect:mypage";
	}
	
	@RequestMapping(value={"/execution"},method=RequestMethod.POST)
	public String postExecution(Model model,@RequestParam("execution")String execution,@ModelAttribute("articles") @Validated(GroupOrder.class) Articles articles,
			BindingResult result, @RequestParam("categoryId") long categoryId) {
		if(execution.equals("regist")) {
			CategoryModel categoryModel = categoryRepository.findById(categoryId);
			this.article.setCategorymodel(categoryModel);
			this.article.setTag(articles.getTag());
			this.article.setTitle(articles.getTitle());
			this.article.setContent(articles.getContent());
			try {
			articlesRepository.saveAndFlush(article);
			System.out.println("編集完了");
			}catch(Exception e) {
			}
		}
		return "redirect:mypage";
	}
}
