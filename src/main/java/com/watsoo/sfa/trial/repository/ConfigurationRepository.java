package com.watsoo.sfa.trial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.watsoo.sfa.trial.model.Configuration;

public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {

	Configuration findByConfigurationKey(String configurationKey);

}
