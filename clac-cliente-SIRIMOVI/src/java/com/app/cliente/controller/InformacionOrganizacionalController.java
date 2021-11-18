
package com.app.cliente.controller;

import com.app.cliente.domain.visitas.InformacionOrganizacional;
import com.app.cliente.domain.visitas.InformacionOrganizacionalList;
import com.app.cliente.domain.visitas.Rubro;
import com.app.cliente.domain.visitas.RubroList;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InformacionOrganizacionalController {
    
    private RestTemplate restTemplate = new RestTemplate();
    ModelAndView mav = new ModelAndView();
    
    // LISTAR
    // Mostrar TODAS las personas en el JSP
    @RequestMapping(value = "/getallInformacionOrganizacional", method = RequestMethod.GET)
    public String getAllInformacionOrganizacional(Model model){
        System.out.println("--> (ADMIN) Recuperar todos los registros de la Informacion Organizacional de mi BD.");
        
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
            model.addAttribute("informacionOrganizacionalGetAll", result.getBody().getData());
        
        }catch(Exception e){
            System.out.println(e);
        }
        
        // Esto es para enviar al JSP de WEB-INF/jsp/consultarPersonas.jsp
        return "consultarInformacionOrganizacional";
    }
    
    
    // CONSULTAR
    // Mostrar los datos de un Usuario en el JSP por su ID
    @RequestMapping(value = "/getIdInformacionOrganizacional", method = RequestMethod.GET)
    public String getInformacionOrganizacionalById(@RequestParam("id") int idInfOrganizacional, Model model) {
        System.out.println("--> Recuperando informacion organizacional con ID: " + idInfOrganizacional);

        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<InformacionOrganizacional> entity = new HttpEntity<InformacionOrganizacional>(headers);

        // Enviamos el Request via GET
        try {
            ResponseEntity<InformacionOrganizacional> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/informacionOrganizacional/{id}", 
                    HttpMethod.GET, entity, InformacionOrganizacional.class, idInfOrganizacional);
            // Agregamos al Model
            model.addAttribute("informacionOrganizacionalGetId", result.getBody());

        } catch (Exception e) {
            System.out.println(e);
        }
        
        // Esto es para enviar al JSP de WEB-INF/jsp/obtenerUsuario.jsp
        return "obtenerInformacionOrganizacional";
    }
    
    
    // CREAR
    // Mostrar el JSP para Agregar Persona
    @RequestMapping(value = "/addInformacionOrganizacional", method = RequestMethod.GET)
    public String getAddPageInformacionOrganizacional(Model model) {
        System.out.println("--> Recibiendo el Request para mostrarlo en la página de agregar.");
        
        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Rubro> entity = new HttpEntity<Rubro>(headers);
        
        //Enviamos el request via GET
        try{
            ResponseEntity<RubroList> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/rubros", 
                    HttpMethod.GET, entity, RubroList.class);
            // Agregamos al Model
            model.addAttribute("rubrosList", result.getBody().getData());
            model.addAttribute("informacionOrganizacionalAttribute", new InformacionOrganizacional());
        
        }catch(Exception e){
            System.out.println(e);
        }

        // Esto es para enviar al JSP de WEB-INF/jsp/agregarPersona.jsp
        return "agregarInformacionOrganizacional";
    }
    
    // CREAR
    // Envíamos el registro y una solicitud de actualización por el metodo GET basados en la información que se envía en submit
    @RequestMapping(value = "/addInformacionOrganizacional", method = RequestMethod.POST)
    public String addInformacionOrganizacional(@ModelAttribute("informacionOrganizacionalAttribute") InformacionOrganizacional informacionOrganizacional, Model model) {
        System.out.println("--> Agregar una nueva información organizacional.");

        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<InformacionOrganizacional> entity = new HttpEntity<InformacionOrganizacional>(informacionOrganizacional, headers);

        // Enviamos el Request via POST
        try {
            ResponseEntity<InformacionOrganizacional> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/informacionOrganizacionalAdd", 
                    HttpMethod.POST, entity, InformacionOrganizacional.class);
            
        } catch (Exception e) {
            System.out.println(e);
        }

        // Esto es para enviar al JSP de WEB-INF/jsp/consultarPersonas.jsp
        return "redirect:/getallInformacionOrganizacional";
    }
    
    
    // EDITAR
    // Mostrar y Recuperar los datos de la persona a editar en el JSP
    @RequestMapping(value = "/updateInformacionOrganizacional", method = RequestMethod.GET)
    public String getUpdatePageInformacionOrganizacional(@RequestParam(value="id", required=true) Integer id, Model model) {
    	System.out.println("--> Recibiendo el Request para mostrarlo en la página de editar.");
    
    	//Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<InformacionOrganizacional> entity = new HttpEntity<InformacionOrganizacional>(headers);
        HttpEntity<Rubro> entity2 = new HttpEntity<Rubro>(headers);

        // Enviamos el Request via GET
        try {
            // PARA MOSTRAR LO DE LA TABLA INFORMACION ORGANIZACIONAL
            ResponseEntity<InformacionOrganizacional> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/informacionOrganizacional/{id}", 
                    HttpMethod.GET, entity, InformacionOrganizacional.class, id);
            // Agregamos al Model
            model.addAttribute("informacionOrganizacionalAttribute", result.getBody());
            
            // PARA MOSTRAR LO DE LA TABLA RUBRO (El Select)
            ResponseEntity<RubroList> result2 = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/rubros", 
                    HttpMethod.GET, entity, RubroList.class);
            // Agregamos al Model
            model.addAttribute("rubrosList", result2.getBody().getData());

        } catch (Exception e) {
                System.out.println(e);
        }
    	
    	// Esto es para enviar al JSP de WEB-INF/jsp/actualizarPersona.jsp
    	return "actualizarInformacionOrganizacional";
    }
    
    // EDITAR
    // Envíar una solicitud de actualización basados en la información enviada en el submit
    @RequestMapping(value = "/updateInformacionOrganizacional", method = RequestMethod.POST)
    public String updateInformacionOrganizacional(@ModelAttribute("informacionOrganizacionalAttribute") InformacionOrganizacional informacionOrganizacional,
            @RequestParam(value="id",  required=true) int id, Model model) {
        System.out.println("--> Actualizando la Informacion Organizacional.");

        ///Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<InformacionOrganizacional> entity = new HttpEntity<InformacionOrganizacional>(informacionOrganizacional, headers);

        // Enviamos el Request via PUT
        ResponseEntity<String> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/informacionOrganizacionalUp/{id}", 
                HttpMethod.PUT, entity, String.class, id);

        // Esto es para enviar al JSP de WEB-INF/jsp/consultarPersonas.jsp
        return "redirect:/getallInformacionOrganizacional";
    }
    
    
    // ELIMINAR
    // Envíamos una solicitud de eliminar
    @RequestMapping(value = "/deleteInformacionOrganizacional", method = RequestMethod.GET)
    public String deletePerson(@RequestParam("id") int id, Model model) {
       System.out.println("--> Eliminando la informacion organizacional.");

        ///Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        // Enviamos el Request via PUT
        ResponseEntity<String> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/informacionOrganizacionalDel/{id}", 
                HttpMethod.DELETE, entity, String.class, id);

        // Esto es para enviar al JSP de WEB-INF/jsp/consultarPersonas.jsp
        return "redirect:/getallInformacionOrganizacional";
    }
}
