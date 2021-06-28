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
	Page<Articles> findAllOrderByCreated_at(Pageable pageable);
	
	public Page<Articles> findAll(Pageable pageable);
	
	@Query("SELECT a FROM Articles a ORDER BY a.view_count DESC")
	Page<Articles> findByOrderByView_count(Pageable pageable);

	
@Query("SELECT a FROM Articles a INNER JOIN a.categorymodel WHERE a.categorymodel.id = :id ORDER BY a.created_at DESC")
Page<Articles> findById(Pageable pageable,long id);


@Query("SELECT a FROM Articles a WHERE a.title = :text")
Page<Articles> findByTitle(Pageable pageable,String text);
}