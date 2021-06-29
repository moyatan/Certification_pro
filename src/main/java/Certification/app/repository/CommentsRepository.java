package Certification.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Certification.app.model.Comments;

@Repository
public interface CommentsRepository extends JpaRepository<Comments,Long>{
	
	@Query("SELECT c FROM Comments c WHERE  articles_id = :id")
	List<Comments> findByIdAll(long id);
}
