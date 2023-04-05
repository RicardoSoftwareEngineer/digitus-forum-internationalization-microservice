package com.digitusforum.internationalization;

import org.springframework.data.repository.CrudRepository;

public interface I18Repository extends CrudRepository<I18Entity, String> {
	I18Entity findByLocaleAndKeyy(String language, String key);
}
