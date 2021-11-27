
package com.app.cliente.controller;

import com.app.cliente.domain.visitas.InformacionComercial;
import com.app.cliente.domain.visitas.InformacionComercialList;
import com.app.cliente.domain.visitas.InformacionOrganizacional;
import com.app.cliente.domain.visitas.InformacionOrganizacionalList;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InformacionComercialController {
    
    private RestTemplate restTemplate = new RestTemplate();
    ModelAndView mav = new ModelAndView();
    
    // LISTAR
    // Mostrar TODAS las informacionesComerciales en el JSP
    @RequestMapping(value = "/getallInformacionComercial", method = RequestMethod.GET)
    public String getAllInformacionComercial(Model model){
        System.out.println("--> Recuperar Todas las Informaciones Comerciales de mi BD.");
        
        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);
        
        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<InformacionComercial> entity = new HttpEntity<InformacionComercial>(headers);
        
        //Enviamos el request via GET
        try{
            ResponseEntity<InformacionComercialList> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/informacionComercial", 
                    HttpMethod.GET, entity, InformacionComercialList.class);
            // Agregamos al Model
            model.addAttribute("informacionComercialGetAll", result.getBody().getData());
        
        }catch(Exception e){
            System.out.println(e);
        }
        
        // Esto es para enviar al JSP de WEB-INF/jsp/consultarPersonas.jsp
        return "consultarInformacionComercial";
    }
    
    
    // CONSULTAR
    // Mostrar los datos de un Usuario en el JSP por su ID
    @RequestMapping(value = "/getIdInformacionComercial", method = RequestMethod.GET)
    public String getInformacionFinancieraById(@RequestParam("id") int idInfComercial, Model model) {
        System.out.println("--> Recuperando informacion comercial con ID: " + idInfComercial);

        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<InformacionComercial> entity = new HttpEntity<InformacionComercial>(headers);

        // Enviamos el Request via GET
        try {
            ResponseEntity<InformacionComercial> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/informacionComercial/{id}", 
                    HttpMethod.GET, entity, InformacionComercial.class, idInfComercial);
            // Agregamos al Model
            model.addAttribute("informacionComercialGetId", result.getBody());

        } catch (Exception e) {
            System.out.println(e);
        }
        
        // Esto es para enviar al JSP de WEB-INF/jsp/obtenerUsuario.jsp
        return "obtenerInformacionComercial";
    }
    
    
    // CREAR
    // Mostrar el JSP para Agregar Informacion Comercial
    @RequestMapping(value = "/addInformacionComercial", method = RequestMethod.GET)
    public String getAddPageInformacionComercial(Model model) {
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
            model.addAttribute("InformacionOrganizacionalList", result.getBody().getData());
            model.addAttribute("informacionComercialAttribute", new InformacionComercial());
        
        }catch(Exception e){
            System.out.println(e);
        }

        // Esto es para enviar al JSP de WEB-INF/jsp/agregarPersona.jsp
        return "agregarInformacionComercial";
    }
    
    // CREAR
    // Envíamos el registro y una solicitud de actualización por el metodo GET basados en la información que se envía en submit
    @RequestMapping(value = "/addInformacionComercial", method = RequestMethod.POST)
    public String addInformacionComercial(@ModelAttribute("informacionComercialAttribute") InformacionComercial infComercial, Model model, @RequestParam("doc") MultipartFile file) {
        System.out.println("--> Agregar una nueva Informacion Comercial.");

        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<InformacionComercial> entity = new HttpEntity<InformacionComercial>(infComercial, headers);
        
        //Se valida si el archivo recibido no esta vacio
        if (!file.isEmpty()) {
            LocalDate fecha = LocalDate.now();
            String identificacion = infComercial.getIdInformacionOrganizacional() + "/" + fecha + "/";
            String ruta = "c:/Archivos/InformacionComercial/" + identificacion;
            System.out.println("--->" + ruta);
            //Se invoca al metodo para guardar el archivo localmente
            String nombreArchivo = guardarAchivo(file, ruta);
            if (nombreArchivo != null) {
                String path = ruta + nombreArchivo;
                infComercial.setArchivo(path);
            }
        }

        // Enviamos el Request via POST
        try {
            ResponseEntity<InformacionComercial> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/informacionComercialAdd", 
                    HttpMethod.POST, entity, InformacionComercial.class);
            
        } catch (Exception e) {
            System.out.println(e);
        }

        // Esto es para enviar al JSP de WEB-INF/jsp/consultarPersonas.jsp
        return "redirect:/getallInformacionComercial";
    }
    
    //Metodo para guardar el archivo localmente
    public static String guardarAchivo(MultipartFile file, String ruta) {
        //Se obtiene el nombre original del archivo
        String nombreOriginal = file.getOriginalFilename();
        try {
            //Se hace la creacion del objeto y se almacena
            File archivoFile = new File(ruta + nombreOriginal);
            file.transferTo(archivoFile);
            return nombreOriginal;
        } catch (IOException e) {
            return null;
        }
    }
    
    // EDITAR
    // Mostrar y Recuperar los datos de la informacion comercial a editar en el JSP
    @RequestMapping(value = "/updateInformacionComercial", method = RequestMethod.GET)
    public String getUpdatePageInformacionComercial(@RequestParam(value="id", required=true) Integer id, Model model) {
    	   System.out.println("--> Recibiendo el Request para mostrarlo en la página de editar.");
    
    	//Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<InformacionComercial> entity = new HttpEntity<InformacionComercial>(headers);
        HttpEntity<InformacionOrganizacional> entity2 = new HttpEntity<InformacionOrganizacional>(headers);

        // Enviamos el Request via GET
        try {
            ResponseEntity<InformacionComercial> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/informacionComercial/{id}", 
                    HttpMethod.GET, entity, InformacionComercial.class, id);
            // Agregamos al Model
            model.addAttribute("informacionComercialAttribute", result.getBody());
            
            ResponseEntity<InformacionOrganizacionalList> result2 = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/informacionOrganizacional", 
                    HttpMethod.GET, entity2, InformacionOrganizacionalList.class);
            // Agregamos al Model
            model.addAttribute("InformacionOrganizacionalList", result2.getBody().getData());

        } catch (Exception e) {
                System.out.println(e);
        }
    	
    	// Esto es para enviar al JSP de WEB-INF/jsp/actualizarPersona.jsp
    	return "actualizarInformacionComercial";
    }
    
    // EDITAR
    // Envíar una solicitud de actualización basados en la información enviada en el submit
    @RequestMapping(value = "/updateInformacionComercial", method = RequestMethod.POST)
    public String updateInformacionComercial(@ModelAttribute("informacionComercialAttribute") InformacionComercial infComercial,
            @RequestParam(value="id",  required=true) int id, Model model, @RequestParam("doc") MultipartFile file) {
        System.out.println("--> Actualizando la Infomracion Comercial.");

        ///Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<InformacionComercial> entity = new HttpEntity<InformacionComercial>(infComercial, headers);
        
        //Se valida si el archivo recibido no esta vacio
        if (!file.isEmpty()) {
            LocalDate fecha = LocalDate.now();
            String identificacion = infComercial.getIdInformacionOrganizacional() + "/" + fecha + "/";
            String ruta = "c:/Archivos/InformacionComercial/" + identificacion;
            System.out.println("--->" + ruta);
            //Se invoca al metodo para guardar el archivo localmente
            String nombreArchivo = guardarAchivo(file, ruta);
            if (nombreArchivo != null) {
                String path = ruta + nombreArchivo;
                infComercial.setArchivo(path);
            }
        }

        // Enviamos el Request via PUT
        ResponseEntity<String> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/informacionComercialUp/{id}", 
                HttpMethod.PUT, entity, String.class, id);

        // Esto es para enviar al JSP de WEB-INF/jsp/consultarPersonas.jsp
        return "redirect:/getallInformacionComercial";
    }
    
    
    // ELIMINAR
    // Envíamos una solicitud de eliminar
    @RequestMapping(value = "/deleteInformacionComercial", method = RequestMethod.GET)
    public String deletePerson(@RequestParam("id") int id, Model model) {
       System.out.println("--> Eliminando la informacion comercial.");

        ///Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        // Enviamos el Request via PUT
        ResponseEntity<String> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/informacionComercialDel/{id}", 
                HttpMethod.DELETE, entity, String.class, id);

        // Esto es para enviar al JSP de WEB-INF/jsp/consultarPersonas.jsp
        return "redirect:/getallInformacionComercial";
    }
    
    
    
    // LISTAR FILTRADA
    // Mostrar TODAS las personas en el JSP
    @RequestMapping(value = "/getallInformacionComercialF", method = RequestMethod.GET)
    public String getAllComercialFinancieraF(@RequestParam("idInfOrg") int idInfOrg, Model model) {
        System.out.println("--> Recuperar la Informacion Comercial de: " + idInfOrg);
        
        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);
        
        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<InformacionComercial> entity = new HttpEntity<InformacionComercial>(headers);
        
        //Enviamos el request via GET
        try{
            ResponseEntity<InformacionComercialList> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/comerciallistF/{idInfOrg}", 
                    HttpMethod.GET, entity, InformacionComercialList.class, idInfOrg);
            
            // Agregamos al Model
            model.addAttribute("informacionComercialGetAll", result.getBody().getData());
        
        }catch(Exception e){
            System.out.println(e);
        }
        
        // Esto es para enviar al JSP de WEB-INF/jsp/consultarPersonas.jsp
        return "consultarInformacionComercial";
    }
    
}
