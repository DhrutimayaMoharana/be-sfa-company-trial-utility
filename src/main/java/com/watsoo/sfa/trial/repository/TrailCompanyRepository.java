package com.watsoo.sfa.trial.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.watsoo.sfa.trial.dto.TrialCompanyDto;
import com.watsoo.sfa.trial.model.TrialCompany;
import com.watsoo.sfa.trial.model.UserData;

public interface TrailCompanyRepository extends JpaRepository<TrialCompany, Long> {

	static Specification<TrialCompany> search(TrialCompanyDto companyDto) {
		return (root, cq, cb) -> {
			Predicate p1 = cb.conjunction();

			if (companyDto != null && companyDto.getIsActive() != null) {
				p1.getExpressions().add(cb.equal(root.get("isActive"), companyDto.getIsActive()));
			}

			if (companyDto.getCompanyIdentifier() != null && !companyDto.getCompanyIdentifier().isEmpty()) {
				p1.getExpressions().add(
						cb.or(cb.like(root.get("companyIdentifier"), "%" + companyDto.getCompanyIdentifier() + "%")));
			}

			if (companyDto.getAdminEmail() != null && !companyDto.getAdminEmail().isEmpty()) {
				Join<?, ?> joinTrailUser = root.join("trialUserDetails");
				p1.getExpressions()
						.add(cb.or(cb.like(joinTrailUser.get("email"), "%" + companyDto.getAdminEmail() + "%")));
			}
			if (companyDto.getUserEmail() != null && !companyDto.getUserEmail().isEmpty()) {
				Join<?, ?> joinTrailUser = root.join("trialUserDetails");
				p1.getExpressions()
						.add(cb.or(cb.like(joinTrailUser.get("email"), "%" + companyDto.getUserEmail() + "%")));
			}
			if (companyDto.getExpiryDate() != null) {
				p1.getExpressions().add(cb.or(cb.equal(root.get("expiryDate"), companyDto.getExpiryDate())));
			}

			if ((companyDto.getCreatedBy() != null && companyDto.getCreatedBy().getName() != null
					&& !companyDto.getCreatedBy().getName().isEmpty())) {
				Join<?, ?> joinUser = root.join("createdBy");
				p1.getExpressions()
						.add(cb.or(cb.like(joinUser.get("name"), "%" + companyDto.getCreatedBy().getName() + "%")));
			}

			if ((companyDto.getUsedBy() != null && companyDto.getUsedBy().getName() != null
					&& !companyDto.getUsedBy().getName().isEmpty())) {
				Join<?, ?> joinUser = root.join("usedBy");
				p1.getExpressions()
						.add(cb.or(cb.like(joinUser.get("name"), "%" + companyDto.getUsedBy().getName() + "%")));
			}

			if (companyDto.getCreatedOn() != null) {

				Date toDate = companyDto.getCreatedOn();
				Date fromDate = companyDto.getCreatedOn();
				Calendar c = Calendar.getInstance();
				c.setTime(toDate);
				c.set(Calendar.HOUR_OF_DAY, 23);
				c.set(Calendar.MINUTE, 59);
				c.set(Calendar.SECOND, 59);
				toDate = c.getTime();
				p1.getExpressions().add(cb.or(cb.between(root.get("createdOn"), fromDate, toDate)));
			}

			return p1;

		};

	}

	Page<TrialCompany> findAll(Specification<TrialCompany> search, Pageable page);

	default Page<TrialCompany> findAll(TrialCompanyDto companyDto, Pageable page) {
		return findAll(search(companyDto), page);

	}

	@Query(value = "select * from company where admin_email=?1 or user_email=?2", nativeQuery = true)
	List<TrialCompany> findByAdminEmailAndUserEmail(String adminEmail, String userEmail);

	@Query(value = "select * from company where id<>?3 and (admin_email=?1 or user_email=?2)", nativeQuery = true)
	List<TrialCompany> findByAdminEmailAndUserEmailAndId(String adminEmail, String userEmail, Long id);

	@Query(value = "select * from company where date(expiry_date) = date(?1) and is_active=true", nativeQuery = true)
	List<TrialCompany> findByExpiryDate(String expiryDate);

	@Query(value = "select * from company where company_identifier=?1", nativeQuery = true)
	TrialCompany findByCompanyIdentifier(String companyIdentifier);

	@Query(value = "select id from company where used_by=?1", nativeQuery = true)
	List<Long> findByUsedBy(Long id);

}
