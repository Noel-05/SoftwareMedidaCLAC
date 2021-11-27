
package com.app.cliente.controller;

import com.app.cliente.domain.visitas.Bienes;
import com.app.cliente.domain.visitas.BienesList;
import com.app.cliente.domain.visitas.InformacionFinanciera;
import com.app.cliente.domain.visitas.InformacionFinancieraList;
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
public class BienController {
    
    private RestTemplate restTemplate = new RestTemplate();
    ModelAndView mav = new ModelAndView();
    
    // LISTAR
    // Mostrar TODAS las personas en el JSP
    @RequestMapping(value = "/getallBienes", method = RequestMethod.GET)
    public String getAllBienes(Model model){
        System.out.println("--> Recuperar Todos los Bienes de mi BD.");
        
        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);
        
        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Bienes> entity = new HttpEntity<Bienes>(headers);
        
        //Enviamos el request via GET
        try{
            ResponseEntity<BienesList> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/bienes", 
                    HttpMethod.GET, entity, BienesList.class);
            // Agregamos al Model
            model.addAttribute("bienesGetAll", result.getBody().getData());
        
        }catch(Exception e){
            System.out.println(e);
        }
        
        // Esto es para enviar al JSP de WEB-INF/jsp/consultarPersonas.jsp
        return "consultarBienes";
    }
    
    
    // CONSULTAR
    // Mostrar los datos de una persona en el JSP por su ID
    @RequestMapping(value = "/getBien", method = RequestMethod.GET)
    public String getProducto(@RequestParam("id") int id, Model model) {
        System.out.println("--> Recuperando bien con ID: " + id);

        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Bienes> entity = new HttpEntity<Bienes>(headers);

        // Enviamos el Request via GET
        try {
            ResponseEntity<Bienes> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/bienes/{id}", 
                    HttpMethod.GET, entity, Bienes.class, id);
            // Agregamos al Model
            model.addAttribute("bienGetId", result.getBody());

        } catch (Exception e) {
            System.out.println(e);
        }
        
        // Esto es para enviar al JSP de WEB-INF/jsp/obtenerPersona.jsp
        return "obtenerBien";
    }
    

    // CREAR
    // Mostrar el JSP para Agregar Bienes
    @RequestMapping(value = "/bienesAdd", method = RequestMethod.GET)
    public String getAddPageBienes(Model model) {
        System.out.println("--> Recibiendo el Request para mostrarlo en la página de agregar.");
        
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
            model.addAttribute("informacionFinancieraList", result.getBody().getData());
            model.addAttribute("bienesAttribute", new Bienes());
        
        }catch(Exception e){
            System.out.println(e);
        }

        // Esto es para enviar al JSP de WEB-INF/jsp/agregarPersona.jsp
        return "agregarBienes";
    }
    
    // CREAR
    // Envíamos el registro y una solicitud de actualización por el metodo GET basados en la información que se envía en submit
    @RequestMapping(value = "/bienesAdd", method = RequestMethod.POST)
    public String addBienes(@ModelAttribute("bienesAttribute") Bienes bien, Model model, @RequestParam("doc") MultipartFile file) {
        System.out.println("--> Agregar una nueva bienes.");

        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Bienes> entity = new HttpEntity<Bienes>(bien, headers);
        
        //Se valida si el archivo recibido no esta vacio
        if (!file.isEmpty()) {
            LocalDate fecha = LocalDate.now();
            String identificacion = bien.getIdInformacionFinanciera() + "/" + fecha + "/";
            String ruta = "c:/Archivos/Bienes/" + identificacion;
            System.out.println("--->" + ruta);
            //Se invoca al metodo para guardar el archivo localmente
            String nombreArchivo = guardarAchivo(file, ruta);
            if (nombreArchivo != null) {
                String path = ruta + nombreArchivo;
                bien.setArchivo(path);
            }
        }

        // Enviamos el Request via POST
        try {
            ResponseEntity<Bienes> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/bienesAdd", 
                    HttpMethod.POST, entity, Bienes.class);
            
        } catch (Exception e) {
            System.out.println(e);
        }

        // Esto es para enviar al JSP de WEB-INF/jsp/consultarPersonas.jsp
        return "redirect:/getallBienes";
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
    // Mostrar y Recuperar los datos del producto a editar en el JSP
    @RequestMapping(value = "/updateBienes", method = RequestMethod.GET)
    public String getUpdatePageBienes(@RequestParam(value="id", required=true) Integer id, Model model) {
    	   System.out.println("--> Recibiendo el Request para mostrarlo en la página de editar.");
    
    	//Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Bienes> entity = new HttpEntity<Bienes>(headers);
        HttpEntity<InformacionFinanciera> entity2 = new HttpEntity<InformacionFinanciera>(headers);

        // Enviamos el Request via GET
        try {
            ResponseEntity<Bienes> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/bienes/{id}", 
                    HttpMethod.GET, entity, Bienes.class, id);
            // Agregamos al Model
            model.addAttribute("bienesAttribute", result.getBody());
            
            ResponseEntity<InformacionFinancieraList> result2 = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/financieralist", 
                    HttpMethod.GET, entity2, InformacionFinancieraList.class);
            // Agregamos al Model
            model.addAttribute("informacionFinancieraList", result2.getBody().getData());

        } catch (Exception e) {
                System.out.println(e);
        }
    	
    	// Esto es para enviar al JSP de WEB-INF/jsp/actualizarPersona.jsp
    	return "actualizarBienes";
    }
    
    // EDITAR
    // Envíar una solicitud de actualización basados en la información enviada en el submit
    @RequestMapping(value = "/updateBienes", method = RequestMethod.POST)
    public String updateBienes(@ModelAttribute("bienesAttribute") Bienes bienes,
            @RequestParam(value="id",  required=true) int id, Model model, @RequestParam("doc") MultipartFile file) {
        System.out.println("--> Actualizando el Bien.");

        ///Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Bienes> entity = new HttpEntity<Bienes>(bienes, headers);
        
        //Se valida si el archivo recibido no esta vacio
        if (!file.isEmpty()) {
            LocalDate fecha = LocalDate.now();
            String identificacion = bienes.getIdInformacionFinanciera() + "/" + fecha + "/";
            String ruta = "c:/Archivos/Bienes/" + identificacion;
            System.out.println("--->" + ruta);
            //Se invoca al metodo para guardar el archivo localmente
            String nombreArchivo = guardarAchivo(file, ruta);
            if (nombreArchivo != null) {
                String path = ruta + nombreArchivo;
                bienes.setArchivo(path);
            }
        }

        // Enviamos el Request via PUT
        ResponseEntity<String> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/bienesUp/{id}", 
                HttpMethod.PUT, entity, String.class, id);

        // Esto es para enviar al JSP de WEB-INF/jsp/consultarPersonas.jsp
        return "redirect:/getallBienes";
    }
    
    
    // ELIMINAR
    // Envíamos una solicitud de eliminar
    @RequestMapping(value = "/deleteBien", method = RequestMethod.GET)
    public String deletePerson(@RequestParam("id") int id, Model model) {
       System.out.println("--> Eliminando el bien.");

        ///Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        // Enviamos el Request via PUT
        ResponseEntity<String> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/bienesDel/{id}", 
                HttpMethod.DELETE, entity, String.class, id);

        // Esto es para enviar al JSP de WEB-INF/jsp/consultarPersonas.jsp
        return "redirect:/getallBienes";
    }
    
    
    // LISTAR FILTRADA
    // Mostrar TODAS las personas en el JSP
    @RequestMapping(value = "/getallBienesF", method = RequestMethod.GET)
    public String getAllBienesF(@RequestParam("idInfFin") int idInfFin, Model model) {
        System.out.println("--> Recuperar los Bienes de: " + idInfFin);
        
        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);
        
        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Bienes> entity = new HttpEntity<Bienes>(headers);
        
        //Enviamos el request via GET
        try{
            ResponseEntity<BienesList> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/bienesF/{idInfFin}", 
                    HttpMethod.GET, entity, BienesList.class, idInfFin);
            
            // Agregamos al Model
            model.addAttribute("bienesGetAll", result.getBody().getData());
        
        }catch(Exception e){
            System.out.println(e);
        }
        
        // Esto es para enviar al JSP de WEB-INF/jsp/consultarPersonas.jsp
        return "consultarBienes";
    }
}