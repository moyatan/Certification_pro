package Certification.app.controller;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import Certification.app.groups.GroupOrder;
import Certification.app.model.Account;
import Certification.app.model.Articles;
import Certification.app.model.CategoryModel;
import Certification.app.repository.ArticlesRepository;
import Certification.app.repository.CategoryRepository;
import Certification.app.service.CategoryCreate;
import Certification.app.service.AccountCheckService;

@Controller
public class ArticlesController {

	@Autowired
	ArticlesRepository articlesRepository;

	@Autowired
	CategoryCreate categoryCreate;

	@Autowired
	AccountCheckService accountCheck;

	@Autowired
	CategoryRepository categoryRepository;

	@RequestMapping(value = "articles", method = RequestMethod.GET)
	public String getArticles(Model model) {
		Map<Long, String> categoryList = categoryCreate.createMap();
		model.addAttribute("categoryList", categoryList);

		return "articles";
	}

	// ここまでOK
	@RequestMapping(value = "articles", method = RequestMethod.POST)
	public String postArticles(@ModelAttribute("articles") @Validated(GroupOrder.class) Articles articles,
			BindingResult result, @RequestParam("categoryId") long categoryId, Model model) {
		String URL = "";

		if (result.hasErrors()) {
			String str = "";
			for (ObjectError error : result.getAllErrors()) {
				str += error.getDefaultMessage();
			}
			System.out.println(str);
			return "error";
		}

		// 現在認証されてるユーザー情報のチェック
		Account account = accountCheck.checkAuthentication();
		if (account != null) {
			CategoryModel categoryModel = categoryRepository.findById(categoryId);
			Timestamp time = new Timestamp(System.currentTimeMillis());
			articles.setCategorymodel(categoryModel);
			articles.setAccount(account);
			articles.setCreated_at(time);
			articlesRepository.saveAndFlush(articles);
			URL = "redirect:articles";
		} else {
			model.addAttribute("errorMessage", "ログインしてください");
			URL = "redirect:login";
		}
		return URL;
	}

}