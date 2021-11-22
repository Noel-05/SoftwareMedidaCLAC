/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.servicio.gestionVisitas.controller;

import com.app.servicio.gestionVisitas.domain.PaisList;
import com.app.servicio.gestionVisitas.domain.RubroList;
import com.app.servicio.gestionVisitas.service.PaisServicio;
import com.app.servicio.gestionVisitas.service.RubroServicio;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PaisController {
    // Declaramos que utilizaremos el servicio de Persona
    @Resource(name="paisServicio")
    private PaisServicio paisServicio;
    
    // LISTAR
    // Metodo para recuperar todas las personas de la BD
    @RequestMapping(value="/paises", method=RequestMethod.GET, headers="Accept=application/json, application/xml")
    private @ResponseBody PaisList getPaises(){
        
        PaisList result = new PaisList();
        
        result.setData(paisServicio.getAll());
        
        System.out.println(result);
        
        return result;
    }
    
}