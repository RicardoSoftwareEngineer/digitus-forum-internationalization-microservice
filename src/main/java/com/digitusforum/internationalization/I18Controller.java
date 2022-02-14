package com.digitusforum.internationalization;


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
    public I18VO internationalization(@RequestBody I18VO i18) {
        return i18Service.getInternationalizedString(i18);
    }
    
    @RequestMapping(value = "/i18/v1/createUpdate")
    public Object create(@RequestBody I18VO i18) {
    	//TODO aqui eu vou fazer uma query no banco buscando as i18 que faltam
    	//terei um teste automatico que busca mensagens faltantes entre linguas diferentes
    	//o teste aumatico atualiza a tabela faltante quando é adicionado uma mensagem faltante
    	
        return i18Service.createUpdate(i18);
    }
    
    @RequestMapping(value = "/i18/v1/missing")
    public Object missing() {
    	//TODO aqui eu vou fazer uma query no banco buscando as i18 que faltam
    	//terei um teste automatico que busca mensagens faltantes entre linguas diferentes
    	//o teste aumatico atualiza a tabela faltante quando é adicionado uma mensagem faltante
    	
        return i18Service.checkMissingMessages();
    }
}