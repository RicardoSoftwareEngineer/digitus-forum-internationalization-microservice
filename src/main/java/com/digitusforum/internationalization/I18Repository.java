package com.digitusforum.internationalization;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface I18Repository extends CrudRepository<I18Entity, String> {
	I18Entity findByLocaleAndKeyy(String language, String key);

	List<I18Entity> findByLocale(String locale);
}
