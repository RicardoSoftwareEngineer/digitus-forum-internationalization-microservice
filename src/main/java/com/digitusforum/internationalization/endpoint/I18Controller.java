package com.digitusforum.internationalization.endpoint;

import com.digitusforum.internationalization.service.I18Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vo.InternationalizationVO;

@RestController
public class I18Controller {
    @Autowired
    public I18Service i18Service = new I18Service();

    @RequestMapping(value = "/i18/v1")
    public InternationalizationVO internationalization(@RequestBody InternationalizationVO i18) {
        return i18Service.getInternationalizedString(i18);
    }
}