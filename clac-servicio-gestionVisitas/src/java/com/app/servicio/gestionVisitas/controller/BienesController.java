
package com.app.servicio.gestionVisitas.controller;

import com.app.servicio.gestionVisitas.domain.Bienes;
import com.app.servicio.gestionVisitas.domain.BienesList;
import com.app.servicio.gestionVisitas.domain.Producto;
import com.app.servicio.gestionVisitas.service.BienesServicio;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BienesController {
    
    // Declaramos que utilizaremos el servicio de Bienes
    @Resource(name="bienesServicio")
    private BienesServicio bienesServicio;
    
    // LISTAR
    // Metodo para recuperar todas las personas de la BD
    @RequestMapping(value="/bienes", method=RequestMethod.GET, headers="Accept=application/json, application/xml")
    private @ResponseBody BienesList getBienes(){
        
        BienesList result = new BienesList();
        
        result.setData(bienesServicio.getAll());
        
        System.out.println(result);
        
        return result;
    }
    
    
    // CONSULTAR
    // Metodo para recuperar una persona por su ID
    @RequestMapping(value="/bienes/{id}", method = RequestMethod.GET, headers="Accept=application/json, application/xml")
    private @ResponseBody Bienes getBienById(@PathVariable("id") int id){
        
        return bienesServicio.getByID(id);
    }
    
    
    // CREAR
    // Metodo para agregar persona
    @RequestMapping(value="/bienesAdd", method = RequestMethod.POST, headers="Accept=application/json, application/xml")
    public @ResponseBody Bienes adBienes(@RequestBody Bienes bien){
    
        System.out.println("Se ha recibido el nuevo bien por Request");
        
        return bienesServicio.add(bien);
    }
    
    
    // EDITAR
    // Metodo para editar una persona
    @RequestMapping(value="/bienesUp/{id}", method = RequestMethod.PUT, headers="Accept=application/json, application/xml")
    public @ResponseBody String updateBienes(@PathVariable("id") int id, @RequestBody Bienes bien){
        
        bien.setIdBien(id);
        
        return bienesServicio.edit(bien).toString();
    }
    
    
    // ELIMINAR
    // Metodo para eliminar una persona
    @RequestMapping(value="/bienesDel/{id}", method = RequestMethod.DELETE, headers="Accept=application/json, application/xml")
    private @ResponseBody String deletProducto(@PathVariable("id") int id){
        System.out.println("Se ha eliminado el bien de ID: " + id);
        
        return bienesServicio.delete(id).toString();
    }
    
    
    
    // LISTAR CON FILTRO
    // Metodo para recuperar todas las personas de la BD
    @RequestMapping(value="/bienesF/{idInfFin}", method=RequestMethod.GET, headers="Accept=application/json, application/xml")
    private @ResponseBody BienesList searchBienById(@PathVariable("idInfFin") int idInfFin){
        
        System.out.println("********"+idInfFin);
        
        BienesList result = new BienesList();
        
        result.setData(bienesServicio.searchByID(idInfFin));
        
        System.out.println(result);
        
        return result;
    }
  
}