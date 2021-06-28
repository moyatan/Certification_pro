package Certification.app.service;

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
	ArticlesRepository articlesrepository;
	
	public Page<Articles> getAllArticles(Pageable pageable){
		
		return articlesrepository.findAll(pageable);
	}
	
	public Articles getAtricle(long id) {
		return articlesrepository.findById(id);
	}
	
	public Page<Articles> getAllArticlesOrder_new(Pageable pageable){
		
		return articlesrepository.findAllOrderByCreated_at(pageable);
	}
	
	public Page<Articles> getAllArticlesOrder_popular(Pageable pageable){
		
		return articlesrepository.findAllOrderByCreated_at(pageable);
	}
	
public Page<Articles> getAllArticlesOrder_search(Pageable pageable,long id){
		
		return articlesrepository.findById(pageable,id);
	}

public Page<Articles> getAllArticlesText(Pageable pageable,String text){
	return articlesrepository.findByTitle(pageable,text);
}

}
