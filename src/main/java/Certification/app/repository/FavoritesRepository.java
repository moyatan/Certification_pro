package Certification.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Certification.app.model.Articles;
import Certification.app.model.Favorites;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorites,Long>{
	
	@Query("SELECT f FROM Favorites f WHERE account_id = :accountId AND articles_id = :articlesId")
	Favorites favoriteCheck(long accountId,long articlesId);
	
	@Query("SELECT COUNT(account_id) FROM Favorites WHERE articles_id = :id")
	int favoriteCount(long id);
	
}
