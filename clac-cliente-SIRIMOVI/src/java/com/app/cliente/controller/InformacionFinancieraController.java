
package com.app.cliente.controller;

import com.app.cliente.domain.visitas.InformacionFinanciera;
import com.app.cliente.domain.visitas.InformacionFinancieraList;
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
public class InformacionFinancieraController {
    
    private RestTemplate restTemplate = new RestTemplate();
    ModelAndView mav = new ModelAndView();
    
    // LISTAR
    // Mostrar TODAS las personas en el JSP
    @RequestMapping(value = "/getAllInformacionFinanciera", method = RequestMethod.GET)
    public String getAllInformacionFinanciera(Model model){
        System.out.println("--> Recuperar Toda la Informacion Financiera de mi BD.");
        
        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);
        
        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<InformacionFinanciera> entity = new HttpEntity<InformacionFinanciera>(headers);
        
        //Enviamos el request via GET
        try{
            ResponseEntity<InformacionFinancieraList> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/financieralist", 
                    HttpMethod.GET, entity, InformacionFinancieraList.class);
            // Agregamos al Model
            model.addAttribute("infoGetAll", result.getBody().getData());
        
        }catch(Exception e){
            System.out.println(e);
        }
        
        // Esto es para enviar al JSP de WEB-INF/jsp/consultarPersonas.jsp
        return "consultarInformacionFinanciera";
    }
    
    
    // CONSULTAR
    // Mostrar los datos de un Usuario en el JSP por su ID
    @RequestMapping(value = "/getIdInformacionFinanciera", method = RequestMethod.GET)
    public String getInformacionFinancieraById(@RequestParam("id") int idInfFinanciera, Model model) {
        System.out.println("--> Recuperando informacion financiera con ID: " + idInfFinanciera);

        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<InformacionFinanciera> entity = new HttpEntity<InformacionFinanciera>(headers);

        // Enviamos el Request via GET
        try {
            ResponseEntity<InformacionFinanciera> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/infoFinanciera/{id}", 
                    HttpMethod.GET, entity, InformacionFinanciera.class, idInfFinanciera);
            // Agregamos al Model
            model.addAttribute("informacionFinancieraGetId", result.getBody());

        } catch (Exception e) {
            System.out.println(e);
        }
        
        // Esto es para enviar al JSP de WEB-INF/jsp/obtenerUsuario.jsp
        return "obtenerInformacionFinanciera";
    }
    
    
    // CREAR
    // Mostrar el JSP para Agregar Persona
    @RequestMapping(value = "/informacionFadd", method = RequestMethod.GET)
    public String getAddPageInformacionFinanciera(Model model) {
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
            model.addAttribute("informaAttribute", new InformacionFinanciera());
        
        }catch(Exception e){
            System.out.println(e);
        }
        
        // Esto es para enviar al JSP de WEB-INF/jsp/agregarPersona.jsp
        return "agregarInformacionFinanciera";
    }
    
    //CREAR
    @RequestMapping(value = "/informacionFadd", method = RequestMethod.POST)
    public String addInfoFinanciera(@ModelAttribute("informaAttribute") InformacionFinanciera info, Model model, @RequestParam("doc") MultipartFile file) {
        System.out.println("--> Agregar una nueva Informacion Financiera.");

        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<InformacionFinanciera> entity = new HttpEntity<InformacionFinanciera>(info, headers);
        
        //Se valida si el archivo recibido no esta vacio
        if (!file.isEmpty()) {
            LocalDate fecha = LocalDate.now();
            String identificacion = info.getIdinformacionOrganizacional() + "/" + fecha + "/";
            String ruta = "c:/Archivos/InformacionFinanciera/" + identificacion;
            System.out.println("--->" + ruta);
            //Se invoca al metodo para guardar el archivo localmente
            String nombreArchivo = guardarAchivo(file, ruta);
            if (nombreArchivo != null) {
                String path = ruta + nombreArchivo;
                info.setArchivo(path);
            }
        }

        // Enviamos el Request via POST
        try {
            ResponseEntity<InformacionFinanciera> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/infoFinancieraAdd", 
                    HttpMethod.POST, entity, InformacionFinanciera.class);
            
        } catch (Exception e) {
            System.out.println(e);
        }
        // Esto es para enviar al JSP de WEB-INF/jsp/consultarPersonas.jsp
        return "redirect:/getAllInformacionFinanciera";
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
    // Mostrar y Recuperar los datos de la persona a editar en el JSP
    @RequestMapping(value = "/updateInformacionFinanciera", method = RequestMethod.GET)
    public String getUpdatePageInformacionFinanciera(@RequestParam(value="id", required=true) Integer id, Model model) {
    	   System.out.println("--> Recibiendo el Request para mostrarlo en la página de editar.");
    
    	//Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<InformacionFinanciera> entity = new HttpEntity<InformacionFinanciera>(headers);
        HttpEntity<InformacionOrganizacional> entity2 = new HttpEntity<InformacionOrganizacional>(headers);

        // Enviamos el Request via GET
        try {
            ResponseEntity<InformacionFinanciera> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/infoFinanciera/{id}", 
                    HttpMethod.GET, entity, InformacionFinanciera.class, id);
            // Agregamos al Model
            model.addAttribute("inforAttribute", result.getBody());
            
            ResponseEntity<InformacionOrganizacionalList> result2 = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/informacionOrganizacional", 
                    HttpMethod.GET, entity2, InformacionOrganizacionalList.class);
            // Agregamos al Model
            model.addAttribute("informacionOrganizacionalList", result2.getBody().getData());

        } catch (Exception e) {
                System.out.println(e);
        }
    	
    	// Esto es para enviar al JSP de WEB-INF/jsp/actualizarPersona.jsp
    	return "actualizarInformacionFinanciera";
    }
    
    // EDITAR
    // Envíar una solicitud de actualización basados en la información enviada en el submit
    @RequestMapping(value = "/updateInformacionFinanciera", method = RequestMethod.POST)
    public String updateInfoFinanciera(@ModelAttribute("inforAttribute") InformacionFinanciera info,
            @RequestParam(value="id",  required=true) int id, Model model, @RequestParam("doc") MultipartFile file) {
        System.out.println("--> Actualizando la Informacion Financiera.");

        ///Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<InformacionFinanciera> entity = new HttpEntity<InformacionFinanciera>(info, headers);
        
        //Se valida si el archivo recibido no esta vacio
        if (!file.isEmpty()) {
            LocalDate fecha = LocalDate.now();
            String identificacion = info.getIdinformacionOrganizacional() + "/" + fecha + "/";
            String ruta = "c:/Archivos/InformacionFinanciera/" + identificacion;
            System.out.println("--->" + ruta);
            //Se invoca al metodo para guardar el archivo localmente
            String nombreArchivo = guardarAchivo(file, ruta);
            if (nombreArchivo != null) {
                String path = ruta + nombreArchivo;
                info.setArchivo(path);
            }
        }

        // Enviamos el Request via PUT
        ResponseEntity<String> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/infoFinancieraUp/{id}", 
                HttpMethod.PUT, entity, String.class, id);

        // Esto es para enviar al JSP de WEB-INF/jsp/consultarPersonas.jsp
        return "redirect:/getAllInformacionFinanciera";
    }
    
    
    // ELIMINAR
    // Envíamos una solicitud de eliminar
    @RequestMapping(value = "/deleteInformacionFinanciera", method = RequestMethod.GET)
    public String deletePerson(@RequestParam("id") int id, Model model) {
       System.out.println("--> Eliminando la informacion financiera.");

        ///Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        // Enviamos el Request via PUT
        ResponseEntity<String> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/informacionFinancieraDel/{id}", 
                HttpMethod.DELETE, entity, String.class, id);

        // Esto es para enviar al JSP de WEB-INF/jsp/consultarPersonas.jsp
        return "redirect:/getAllInformacionFinanciera";
    }
    
    
    
    // LISTAR FILTRADA
    // Mostrar TODAS las personas en el JSP
    @RequestMapping(value = "/getAllInformacionFinancieraF", method = RequestMethod.GET)
    public String getAllInformacionFinancieraF(@RequestParam("idInfOrg") int idInfOrg, Model model) {
        System.out.println("--> Recuperar la Informacion Financiera de: " + idInfOrg);
        
        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);
        
        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<InformacionFinanciera> entity = new HttpEntity<InformacionFinanciera>(headers);
        
        //Enviamos el request via GET
        try{
            ResponseEntity<InformacionFinancieraList> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/financieralistF/{idInfOrg}", 
                    HttpMethod.GET, entity, InformacionFinancieraList.class, idInfOrg);
            
            // Agregamos al Model
            model.addAttribute("infoGetAll", result.getBody().getData());
        
        }catch(Exception e){
            System.out.println(e);
        }
        
        // Esto es para enviar al JSP de WEB-INF/jsp/consultarPersonas.jsp
        return "consultarInformacionFinanciera";
    }
    
}
