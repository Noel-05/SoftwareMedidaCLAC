
package com.app.servicio.gestionVisitas.controller;

import com.app.servicio.gestionVisitas.domain.InformacionFinanciera;
import com.app.servicio.gestionVisitas.domain.InformacionFinancieraList;
import com.app.servicio.gestionVisitas.service.InformacionFinancieraServicio;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class InformacionFinancieraController {
    
    @Resource(name="inFinancieraServicio")
    private InformacionFinancieraServicio infoFinanciera;
    
    //LISTAR
    @RequestMapping(value="/financieralist", method=RequestMethod.GET, headers="Accept=application/json, application/xml")
    private @ResponseBody InformacionFinancieraList getInfoFinanciera(){
        
        InformacionFinancieraList result = new InformacionFinancieraList();
        
        result.setData(infoFinanciera.getAll());
        
        System.out.println(result);
        
        return result;
    }
    
    //CONSULTAR
    @RequestMapping(value="/infoFinanciera/{id}", method = RequestMethod.GET, headers="Accept=application/json, application/xml")
    private @ResponseBody InformacionFinanciera getFinancieraById(@PathVariable("id") int id){
        
        return infoFinanciera.getByID(id);
    }
    
    //CREAR
    @RequestMapping(value="/infoFinancieraAdd", method = RequestMethod.POST, headers="Accept=application/json, application/xml")
    public @ResponseBody InformacionFinanciera addInfoFinanciera(@RequestBody InformacionFinanciera i){
    
        System.out.println("Se ha recibido la nueva informacion financiera por Request");
        
        return infoFinanciera.add(i);
    }
    
    //EDITAR
    @RequestMapping(value="/infoFinancieraUp/{id}", method = RequestMethod.PUT, headers="Accept=application/json, application/xml")
    public @ResponseBody String updateInfoFinanciera(@PathVariable("id") int id, @RequestBody InformacionFinanciera i){
        
        i.setIdinformacionFinanciera(id);
        
        return infoFinanciera.edit(i).toString();
    }
    
    // ELIMINAR
    // Metodo para eliminar una persona
    @RequestMapping(value="/informacionFinancieraDel/{id}", method = RequestMethod.DELETE, headers="Accept=application/json, application/xml")
    private @ResponseBody String deletInformacionFinanciera(@PathVariable("id") int id){
        System.out.println("Se ha eliminado el registro de ID: " + id);
        
        return infoFinanciera.delete(id).toString();
    }
}
