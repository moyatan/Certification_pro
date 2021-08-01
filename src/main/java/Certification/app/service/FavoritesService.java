package Certification.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Certification.app.model.Account;
import Certification.app.model.Favorites;
import Certification.app.repository.FavoritesRepository;

@Service
public class FavoritesService {

	@Autowired
	FavoritesRepository favoriteRepository;
	
	//お気に入りがされてるのかチェック
	public Favorites favoriteCheck(Account account,long articleId) {
	return favoriteRepository.favoriteCheck(account.getId(), articleId);
	}
}
