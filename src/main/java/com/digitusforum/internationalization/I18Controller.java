package com.digitusforum.internationalization;

import i18.InternationalizationVO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class I18Controller {
    @Autowired
    public I18Service i18Service = new I18Service();

    @RequestMapping(value = "/i18/v1")
    public InternationalizationVO internationalization(@RequestBody InternationalizationVO i18) {
        return i18Service.getInternationalizedString(i18);
    }
}