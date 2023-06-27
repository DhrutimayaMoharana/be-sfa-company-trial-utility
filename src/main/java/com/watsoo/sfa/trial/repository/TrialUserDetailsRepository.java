package com.watsoo.sfa.trial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.watsoo.sfa.trial.model.TrialUserDetails;
import com.watsoo.sfa.trial.model.UserData;

public interface TrialUserDetailsRepository extends JpaRepository<TrialUserDetails, Long> {

	@Query(value = "select * from trial_user_details where email=?1 or email in (?2)", nativeQuery = true)
	List<TrialUserDetails> findByAdminEmailAndUserEmail(String adminEmail, String[] userEmail);

	@Query(value = "select * from trial_user_details where company_id in (?1)", nativeQuery = true)
	List<TrialUserDetails> findByCompanyId(List<Long> companyIds);

	@Query(value = "select * from trial_user_details tud join trial_company tc on tud.company_id=tc.id  where tud.email=?1 and tc.company_identifier=?2", nativeQuery = true)
	TrialUserDetails getTrialUserDetailsByEmail(String email,String comanyIdentifier);

}
