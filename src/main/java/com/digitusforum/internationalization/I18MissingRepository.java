package com.digitusforum.internationalization;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface I18MissingRepository extends CrudRepository<I18MissingEntity, String> {
	I18MissingEntity findByLocaleAndKey(String locale, String key);
	List<I18MissingEntity> findByLocale(String locale);
	void deleteById(String id);
}