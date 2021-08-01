package Certification.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Certification.app.model.Account;
import Certification.app.model.Articles;
import Certification.app.model.CategoryModel;

@Repository
public interface ArticlesRepository extends JpaRepository<Articles, Long> {

	Articles findById(long id);

	@Query("SELECT a FROM Articles a ORDER BY a.created_at DESC")
	Page<Articles> findAllArticlesByNewOrder(Pageable pageable);
	
	@Query("SELECT a FROM Articles a WHERE categorymodel_id = :categoryId ORDER BY a.created_at DESC")
	Page<Articles> findAllArticlesByNewOrder_category(Pageable pageable,long categoryId);

	public Page<Articles> findAll(Pageable pageable);

	@Query("SELECT a FROM Articles a ORDER BY a.view_count DESC")
	Page<Articles> findAllArticlesByOrderPopular(Pageable pageable);
	
	@Query("SELECT a FROM Articles a WHERE categorymodel_id = :categoryId ORDER BY a.view_count DESC")
	Page<Articles> findAllArticlesByOrderPopular_category(Pageable pageable,long categoryId);

	@Query("SELECT a FROM Articles a INNER JOIN a.categorymodel WHERE a.categorymodel.id = :id ORDER BY a.created_at DESC")
	Page<Articles> findArticlesByCategoryId(Pageable pageable, long id);

	@Query("SELECT a FROM Articles a WHERE a.title = :text OR a.tag = :text")
	Page<Articles> findByTitle(Pageable pageable, String text);
	
	@Query(value="SELECT * FROM articles WHERE id IN (SELECT articles_id FROM favorites WHERE account_id = :id)",nativeQuery = true)
	Page<Articles> findArticlesListByFavorite(Pageable pageable,long id);
	
	@Query("SELECT a FROM Articles a WHERE a.account.id = :id")
	Page<Articles> findArticlesListByAccountId(Pageable pageable,long id);
	
	@Query("SELECT a FROM Articles a WHERE a.account.id = :id")
	List<Articles> findArticlesListByAccountId(long id);
	
}