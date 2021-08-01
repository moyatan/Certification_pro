package Certification.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Certification.app.model.Account;

@Repository
public interface UserRepository extends JpaRepository<Account, Long> {
	Account findByEmail(String email);
	
	Account findById(long id);

}