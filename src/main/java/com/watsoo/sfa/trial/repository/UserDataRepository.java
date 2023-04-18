package com.watsoo.sfa.trial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.watsoo.sfa.trial.model.UserData;

public interface UserDataRepository extends JpaRepository<UserData, Long> {

	UserData getUserDataByEmail(String email);

}
