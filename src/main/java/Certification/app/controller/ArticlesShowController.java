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
import Certification.app.model.Favorites;
import Certification.app.repository.ArticlesRepository;
import Certification.app.repository.CommentsRepository;
import Certification.app.repository.FavoritesRepository;
import Certification.app.repository.UserRepository;
import Certification.app.service.AccountCheckService;
import Certification.app.service.ArticlesService;
import Certification.app.service.CategoryCreate;
import Certification.app.service.FavoritesService;


@Controller
public class ArticlesShowController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ArticlesService articlesService;

	@Autowired
	CommentsRepository commentRepository;
	
	@Autowired
	FavoritesRepository favoriteRepository;
	
	@Autowired
	FavoritesService favoritesService;
	
	@Autowired
	ArticlesRepository articlesRepository;
	
	@Autowired
	AccountCheckService accountCheck;
	
	@Autowired
	CategoryCreate categoryCreate;

	@RequestMapping(value = { "/articlesShow" }, method = RequestMethod.GET)
	@Transactional
	public String AccountDetails(@RequestParam(name = "id", required = false)long articlesId, Model model) {
String URL = "gestArticleShow";
		Articles article = articlesService.getAtricle(articlesId);
		article.setView_count(article.getView_count()+1);
		articlesRepository.save(article);
		//記事ごとにコメントを全件
		List<Comments> commentsList = commentRepository.findAllCommentsByArticlesId(articlesId);
		Account account = accountCheck.checkAuthentication();
		if(account != null) {
		model.addAttribute("account",account);
		URL = "articlesShow";
		Favorites favorite = favoritesService.favoriteCheck(account, articlesId);
		if(favorite != null) {
			model.addAttribute("favorite",favorite);
		}
		}
		model.addAttribute("commentsList", commentsList);
		model.addAttribute("articles", article);
		return URL;

	}

	//コメント登録
	@Transactional
	@RequestMapping(value = { "/comment" }, method = RequestMethod.GET)
	public String comment(@RequestParam("comment") String comment, @RequestParam("articleId") long articleId, Model model) {
		Articles articles = articlesRepository.findById(articleId);
		Account account = accountCheck.checkAuthentication();
		Comments comments = new Comments();
		comments.setAccount(account);
		comments.setContent(comment);
		comments.setArticles(articles);
		Timestamp time = new Timestamp(System.currentTimeMillis());
		comments.setCreated_at(time);
		try {
			commentRepository.saveAndFlush(comments);
			List<Comments> commentsList = commentRepository.findAllCommentsByArticlesId(articles.getId());
			model.addAttribute("account", account);
			model.addAttribute("commentsList", commentsList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "articlesShow :: comment";
	}


	//お気に入り登録
	@RequestMapping(value = { "/inputFavorite" }, method = RequestMethod.GET)
	@ResponseBody
	@Transactional
	public String favoritcount(@RequestParam("articleId") long articleId,Model model) {
		Account account = accountCheck.checkAuthentication();
		if (account != null) {
			Favorites favorite = favoritesService.favoriteCheck(account, articleId);
			if (favorite == null) {
				Articles article = articlesRepository.findById(articleId);
				Favorites favorit = new Favorites();
				favorit.setAccount(account);
				favorit.setArticles(article);
				try {
					favoriteRepository.saveAndFlush(favorit);
					int favoriteCount =favoriteRepository.favoriteCount(articleId);
					article.setFavoriteCount(favoriteCount);
					articlesRepository.save(article);
					return "お気に入りできました";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				try {
				favoriteRepository.delete(favorite);
				System.out.println("削除完了");
				}catch(Exception e) {
					System.out.println("削除失敗");
				}
			return null;
			}
		}
		return "お気に入りは会員限定です";
	}
}