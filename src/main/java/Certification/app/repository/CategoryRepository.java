package Certification.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Certification.app.model.CategoryModel;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel,Long>{
	
	CategoryModel findById(long categoryId);

}