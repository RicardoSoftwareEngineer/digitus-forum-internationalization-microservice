package com.digitusforum.internationalization.service;

//import microservice.HistoryMicroservice;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import vo.InternationalizationVO;

import java.util.Locale;

@Service
public class I18Service implements MessageSourceAware {
    private MessageSource messageSource;
    //private HistoryMicroservice historyMicroservice = new HistoryMicroservice();

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public InternationalizationVO getInternationalizedString(InternationalizationVO i18){
    	try {
    		String message = this.messageSource.getMessage(i18.getKey(),
                    new Object[0],
                    new Locale(i18.getLocale()));
            i18.setMessage(message);
    	}catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.valueOf(404), e.getMessage());
		}
        
        //historyMicroservice.create(i18);
        return i18;
    }
}