package Certification.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import Certification.app.model.Articles;
import Certification.app.model.CategoryModel;
import Certification.app.repository.ArticlesRepository;

@Service
public class ArticlesService {

	@Autowired
	ArticlesRepository articlesRepository;
	
	//記事全てを取得
	public Page<Articles> getAllArticles(Pageable pageable) {

		return articlesRepository.findAll(pageable);
	}
	
	//記事IDを基に取得
	public Articles getAtricle(long id) {
		return articlesRepository.findById(id);
	}
	
	//最新の記事で取得
	public Page<Articles> articleListByNewOrder(Pageable pageable) {

		return articlesRepository.findAllArticlesByNewOrder(pageable);
	}

	//人気の記事で取得
	public Page<Articles> articleListByOrderPopular(Pageable pageable) {

		return articlesRepository.findAllArticlesByOrderPopular(pageable);
	}
	
	//記事のカテゴリを基に取得
	public Page<Articles> articlesListByCategorySearch(Pageable pageable, long id) {
		return articlesRepository.findArticlesByCategoryId(pageable, id);
	}
	//最新の記事で取得(カテゴリ選択時)
		public Page<Articles> articleListByNewOrder_category(Pageable pageable,long categoryId) {

			return articlesRepository.findAllArticlesByNewOrder_category(pageable,categoryId);
		}

		//人気の記事で取得(カテゴリ選択時)
		public Page<Articles> articleListByOrderPopular_category(Pageable pageable,long categoryId) {

			return articlesRepository.findAllArticlesByOrderPopular_category(pageable,categoryId);
		}
	//タイトル検索
	public Page<Articles> getAllArticlesText(Pageable pageable, String text) {
		return articlesRepository.findByTitle(pageable, text);
	}
	
	//お気に入りで並び替え
	public Page<Articles> articlesListByFavoriteSort(Pageable pageable,long id){
		return articlesRepository.findArticlesListByFavorite(pageable, id);
	}
	public Page<Articles> articlesListByAccountId(Pageable pageable,long id){
		return articlesRepository.findArticlesListByAccountId(pageable,id);
	}
	public List<Articles> articlesList(long id){
		return articlesRepository.findArticlesListByAccountId(id);
	}

}
