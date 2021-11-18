
package com.app.cliente.controller;

import com.app.cliente.domain.visitas.InformacionComercial;
import com.app.cliente.domain.visitas.InformacionComercialList;
import com.app.cliente.domain.visitas.InformacionOrganizacional;
import com.app.cliente.domain.visitas.InformacionOrganizacionalList;
import com.app.cliente.domain.monitoreos.Monitoreo;
import com.app.cliente.domain.monitoreos.MonitoreoList;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MonitoreoController {
    
    private RestTemplate restTemplate = new RestTemplate();
    ModelAndView mav = new ModelAndView();
    
    // LISTAR
    // Mostrar TODOS las monitoreos en el JSP
    @RequestMapping(value = "/getallMonitoreos", method = RequestMethod.GET)
    public String getAllMonitoreo(Model model){
        System.out.println("--> Recuperar Todos las Monitoreos de mi BD.");
        
        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);
        
        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Monitoreo> entity = new HttpEntity<Monitoreo>(headers);
        
        //Enviamos el request via GET
        try{
            ResponseEntity<MonitoreoList> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionMonitoreos/getallMonitoreos", 
                    HttpMethod.GET, entity, MonitoreoList.class);
            // Agregamos al Model
            model.addAttribute("monitoreosGetAll", result.getBody().getData());
        
        }catch(Exception e){
            System.out.println(e);
        }
        
        // Esto es para enviar al JSP de WEB-INF/jsp/consultarMonitoreos.jsp
        return "consultarMonitoreo";
    }
    
    
    // CONSULTAR
    // Mostrar los datos de una persona en el JSP por su ID
    @RequestMapping(value = "/getMonitoreo", method = RequestMethod.GET)
    public String getProducto(@RequestParam("id") int id, Model model) {
        System.out.println("--> Recuperando monitoreo con ID: " + id);

        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Monitoreo> entity = new HttpEntity<Monitoreo>(headers);

        // Enviamos el Request via GET
        try {
            ResponseEntity<Monitoreo> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionMonitoreos/monitoreos/{id}", 
                    HttpMethod.GET, entity, Monitoreo.class, id);
            // Agregamos al Model
            model.addAttribute("monitoreoGetId", result.getBody());

        } catch (Exception e) {
            System.out.println(e);
        }
        
        // Esto es para enviar al JSP de WEB-INF/jsp/obtenerPersona.jsp
        return "obtenerMonitoreo";
    }
    
    
    // CREAR MONITOREO
    // Mostrar el JSP para Agregar Monitoreo
    @RequestMapping(value = "/monitoreosAdd", method = RequestMethod.GET)
    public String getAddPageMonitoreo(Model model) {
        System.out.println("--> Recibiendo el Request para mostrarlo en la página de agregar.");
        
        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);
        
        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<InformacionOrganizacional> entity = new HttpEntity<InformacionOrganizacional>(headers);
        
        //Enviamos el request via GET
        try{
            ResponseEntity<InformacionOrganizacionalList> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/informacionOrganizacional", 
                    HttpMethod.GET, entity, InformacionOrganizacionalList.class);
            // Agregamos al Model
            model.addAttribute("informacionOrganizacionalList", result.getBody().getData());
            model.addAttribute("monitoreoAttribute", new Monitoreo());
        
        }catch(Exception e){
            System.out.println(e);
        }
        
        // Esto es para enviar al JSP de WEB-INF/jsp/agregarMonitoreo.jsp
        return "agregarMonitoreo";
    }
    
    // CREAR
    // Envíamos el registro y una solicitud de actualización por el metodo GET basados en la información que se envía en submit
    @RequestMapping(value = "/monitoreosAdd", method = RequestMethod.POST)
    public String addPersonMonitoreo(@ModelAttribute("monitoreoAttribute") Monitoreo monitoreo, Model model) {
        System.out.println("--> Agregar un nuevo monitoreo.");

        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Monitoreo> entity = new HttpEntity<Monitoreo>(monitoreo, headers);

        // Enviamos el Request via POST
        try {
            ResponseEntity<Monitoreo> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionMonitoreos/monitoreosAdd", 
                    HttpMethod.POST, entity, Monitoreo.class);
            
        } catch (Exception e) {
            System.out.println(e);
        }

        // Esto es para enviar al JSP de WEB-INF/jsp/consultarMonitoreos.jsp
        return "redirect:/getallMonitoreos";
    }
    
    
    // EDITAR MONITOREO
    // Mostrar y Recuperar los datos de la monitoreo a editar en el JSP
    @RequestMapping(value = "/updateMonitoreo", method = RequestMethod.GET)
    public String getUpdatePageMonitoreo(@RequestParam(value="id", required=true) Integer id, Model model) {
    	   System.out.println("--> Recibiendo el Request para mostrarlo en la página de editar.");
    
    	//Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Monitoreo> entity = new HttpEntity<Monitoreo>(headers);
        HttpEntity<InformacionOrganizacional> entity2 = new HttpEntity<InformacionOrganizacional>(headers);

        // Enviamos el Request via GET
        try {
            ResponseEntity<Monitoreo> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionMonitoreos/monitoreos/{id}", 
                    HttpMethod.GET, entity, Monitoreo.class, id);
            // Agregamos al Model
            model.addAttribute("monitoreoAttribute", result.getBody());
            
            ResponseEntity<InformacionOrganizacionalList> result2 = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/informacionOrganizacional", 
                    HttpMethod.GET, entity2, InformacionOrganizacionalList.class);
            // Agregamos al Model
            model.addAttribute("informacionOrganizacionalList", result2.getBody().getData());

        } catch (Exception e) {
                System.out.println(e);
        }
    	
    	// Esto es para enviar al JSP de WEB-INF/jsp/actualizarMonitoreo.jsp
    	return "actualizarMonitoreo";
    }
    
    // EDITAR
    // Envíar una solicitud de actualización basados en la información enviada en el submit
    @RequestMapping(value = "/updateMonitoreo", method = RequestMethod.POST)
    public String updatePersonMonitoreo(@ModelAttribute("monitoreoAttribute") Monitoreo monitoreo,
            @RequestParam(value="id",  required=true) int id, Model model) {
        System.out.println("--> Actualizando la monitoreo.");

        ///Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Monitoreo> entity = new HttpEntity<Monitoreo>(monitoreo, headers);

        // Enviamos el Request via PUT
        ResponseEntity<String> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionMonitoreos/monitoreosUp/{id}", 
                HttpMethod.PUT, entity, String.class, id);

        // Esto es para enviar al JSP de WEB-INF/jsp/consultarMonitoreos.jsp
        return "redirect:/getallMonitoreos";
    }
    
    
    // ELIMINAR
    // Envíamos una solicitud de eliminar
    @RequestMapping(value = "/deleteMonitoreo", method = RequestMethod.GET)
    public String deleteMonitoreo(@RequestParam("id") int id, Model model) {
       System.out.println("--> Eliminando el monitoreo.");

        ///Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        // Enviamos el Request via PUT
        ResponseEntity<String> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionMonitoreos/monitoreosDel/{id}", 
                HttpMethod.DELETE, entity, String.class, id);

        // Esto es para enviar al JSP de WEB-INF/jsp/consultarPersonas.jsp
        return "redirect:/getallMonitoreos";
    }
}
