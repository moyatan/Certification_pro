package Certification.app.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Certification.app.model.CategoryModel;
import Certification.app.repository.CategoryRepository;

@Service
public class CategoryCreate {
	
	@Autowired
	CategoryRepository categoryrepository;
	
	public Map<Long,String> categorycreate(){
	List<CategoryModel> categoryList =  categoryrepository.findAll();
	Map<Long,String> categoryMap = new LinkedHashMap<Long,String>();
	for(CategoryModel category : categoryList) {
		categoryMap.put(category.getId(),category.getCategory());
	}
	
	return categoryMap;

}
}