package com.digitusforum.internationalization;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;



@Service
public class I18Service {

	@Autowired
	I18Repository i18Repository;
	@Autowired
	I18MissingRepository i18MissingRepository;

	public I18VO getInternationalizedString(I18VO i18VO) {
		I18Entity i18FromDB = i18Repository.findByLocaleAndKeyy(i18VO.getLocale(), i18VO.getKey());
		if (i18FromDB != null) {
			i18VO.setMessage(i18FromDB.getMessage());
		}
		if (i18FromDB == null) {
			saveMissingEntity(i18VO);
			i18FromDB = tryEnUs(i18VO);
			if (i18FromDB != null) {
				i18VO.setMessage(i18FromDB.getMessage());
			}
			if (i18FromDB == null) {
				/*
				 * i18VO.setMessage(
				 * "We are working ta add a internacionalized message here, hope the code make sense for you: "
				 * + i18VO.getKey() + ", sorry the incovenience");
				 */
				i18VO.setMessage(i18VO.getKey());
			}
		}
		return i18VO;
	}

	public void saveMissingEntity(I18VO i18VO) {
		I18MissingEntity i18MissingEntity = i18MissingRepository.findByLocaleAndKeyy(i18VO.getLocale(), i18VO.getKey());
		if (i18MissingEntity == null)
			i18MissingRepository.save(new ModelMapper().map(i18VO, I18MissingEntity.class));
	}

	public I18Entity tryEnUs(I18VO i18VO) {
		return i18Repository.findByLocaleAndKeyy("en_us", i18VO.getKey());
	}

	public Object createUpdate(I18VO i18VO) {
		if (StringUtils.isBlank(i18VO.getLocale()))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, M.I18_MISSING_LOCALE);
		if (StringUtils.isBlank(i18VO.getKey()))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, M.I18_MISSING_KEY);
		if (StringUtils.isBlank(i18VO.getMessage()))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, M.I18_MISSING_MESSAGE);

		I18Entity i18FromDB = i18Repository.findByLocaleAndKeyy(i18VO.getLocale(), i18VO.getKey());
		if (i18FromDB != null) {
			i18FromDB.setMessage(i18VO.getMessage());
			i18FromDB = i18Repository.save(i18FromDB);
		}
		if (i18FromDB == null) {
			I18Entity i18New = new ModelMapper().map(i18VO, I18Entity.class);
			i18FromDB = i18Repository.save(i18New);
		}
		updateMissingMessages(i18VO);
		return i18FromDB;
	}

	public void updateMissingMessages(I18VO i18VO) {
		I18MissingEntity i18 = i18MissingRepository.findByLocaleAndKeyy(i18VO.getLocale(), i18VO.getKey());
		if(i18 != null)
			i18MissingRepository.deleteById(i18.getId());
	}

	public Object checkMissingMessages() {
		return i18MissingRepository.findAll();
	}
}