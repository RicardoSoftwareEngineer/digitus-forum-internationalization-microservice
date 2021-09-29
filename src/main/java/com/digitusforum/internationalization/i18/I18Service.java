package com.digitusforum.internationalization.i18;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import i18.InternationalizationVO;

@Service
public class I18Service implements MessageSourceAware {
	private MessageSource messageSource;

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public InternationalizationVO getInternationalizedString(InternationalizationVO i18) {
		try {
			String message = this.messageSource.getMessage(i18.getKey(), new Object[0], new Locale(i18.getLocale()));
			i18.setMessage(message);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.valueOf(404), e.getMessage());
		}
		return i18;
	}
}