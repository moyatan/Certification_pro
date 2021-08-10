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

	@Query("SELECT a FROM Articles a WHERE a.delete_flag = 0 ORDER BY a.created_at DESC")
	Page<Articles> findAllArticlesByNewOrder(Pageable pageable);
	
	@Query("SELECT a FROM Articles a WHERE categorymodel_id = :categoryId AND a.delete_flag = 0 ORDER BY a.created_at DESC")
	Page<Articles> findAllArticlesByNewOrder_category(Pageable pageable,long categoryId);

	public Page<Articles> findAll(Pageable pageable);

	@Query("SELECT a FROM Articles a WHERE a.delete_flag = 0 ORDER BY a.view_count DESC")
	Page<Articles> findAllArticlesByOrderPopular(Pageable pageable);
	
	@Query("SELECT a FROM Articles a WHERE categorymodel_id = :categoryId AND a.delete_flag = 0 ORDER BY a.view_count DESC")
	Page<Articles> findAllArticlesByOrderPopular_category(Pageable pageable,long categoryId);

	@Query("SELECT a FROM Articles a INNER JOIN a.categorymodel WHERE a.categorymodel.id = :id AND a.delete_flag = 0 ORDER BY a.created_at DESC")
	Page<Articles> findArticlesByCategoryId(Pageable pageable, long id);

	@Query("SELECT a FROM Articles a WHERE a.delete_flag = 0 AND (a.title = :text OR a.tag = :text)")
	Page<Articles> findByTitle(Pageable pageable, String text);
	
	@Query(value="SELECT * FROM articles WHERE id IN (SELECT articles_id FROM favorites WHERE account_id = :id) AND delete_flag = 0",nativeQuery = true)
	Page<Articles> findArticlesListByFavorite(Pageable pageable,long id);
	
	@Query("SELECT a FROM Articles a WHERE a.account.id = :id AND delete_flag = 0")
	Page<Articles> findArticlesListByAccountId(Pageable pageable,long id);
	
	@Query("SELECT a FROM Articles a WHERE a.account.id = :id AND a.delete_flag = 0")
	List<Articles> findArticlesListByAccountId(long id);

	//タイトル　または　タグ検索がされていた時に実行
	@Query("SELECT a FROM Articles a WHERE a.delete_flag = 0 AND (a.title = :title OR a.tag = :title) ORDER BY a.created_at DESC")
	Page<Articles> findAllArticlesByNewOrder_title(Pageable pageable, String title);
	
	@Query("SELECT a FROM Articles a WHERE categorymodel_id = :categoryId AND a.delete_flag = 0 "
			+ "AND (a.title = :title OR a.tag = :title) ORDER BY a.created_at DESC")
	Page<Articles> findAllArticlesByNewOrder_category_title(Pageable pageable, long categoryId, String title);
	
	@Query("SELECT a FROM Articles a WHERE categorymodel_id = :categoryId AND a.delete_flag = 0 "
			+ "AND (a.title = :title OR a.tag = :title) ORDER BY a.view_count DESC")
	Page<Articles> findAllArticlesByOrderPopular_category_title(Pageable pageable, long categoryId, String title);

	@Query("SELECT a FROM Articles a WHERE a.delete_flag = 0 AND (a.title = :title OR a.tag = :title) ORDER BY a.view_count DESC")
	Page<Articles> findAllArticlesByOrderPopular_title(Pageable pageable, String title);
	
	
	
}