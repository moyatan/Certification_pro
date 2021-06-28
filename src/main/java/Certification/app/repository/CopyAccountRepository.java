package Certification.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Certification.app.model.Account;
import Certification.app.model.CopyAccount;

@Repository
public interface CopyAccountRepository extends JpaRepository<CopyAccount, Long> {
	
	@Query("SELECT c FROM CopyAccount c WHERE uuid = :uuid")
	public CopyAccount findByAccountCheck(String uuid);


}