
package com.app.cliente.controller;

import com.app.cliente.domain.visitas.InformacionOrganizacional;
import com.app.cliente.domain.visitas.InformacionOrganizacionalList;
import com.app.cliente.domain.visitas.Pais;
import com.app.cliente.domain.visitas.PaisList;
import com.app.cliente.domain.visitas.Rubro;
import com.app.cliente.domain.visitas.RubroList;
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
        HttpEntity<Pais> entity2 = new HttpEntity<Pais>(headers);
        
        //Enviamos el request via GET
        try{
            ResponseEntity<InformacionOrganizacionalList> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/informacionOrganizacional", 
                    HttpMethod.GET, entity, InformacionOrganizacionalList.class);
            
            ResponseEntity<PaisList> result2 = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/paises", 
                    HttpMethod.GET, entity2, PaisList.class);
            
            // Agregamos al Model
            model.addAttribute("paisList", result2.getBody().getData());
            model.addAttribute("informacionOrganizacionalGetAll", result.getBody().getData());
            model.addAttribute("informacionOrganizacionalAttribute", new InformacionOrganizacional());
        
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
        HttpEntity<Pais> entity2 = new HttpEntity<Pais>(headers);
        
        //Enviamos el request via GET
        try{
            ResponseEntity<RubroList> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/rubros", 
                    HttpMethod.GET, entity, RubroList.class);
            
            ResponseEntity<PaisList> result2 = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/paises", 
                    HttpMethod.GET, entity2, PaisList.class);
            
            // Agregamos al Model
            model.addAttribute("rubrosList", result.getBody().getData());
            model.addAttribute("paisList", result2.getBody().getData());
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
    public String addInformacionOrganizacional(@ModelAttribute("informacionOrganizacionalAttribute") InformacionOrganizacional informacionOrganizacional, Model model,  @RequestParam("doc") MultipartFile file) {
        System.out.println("--> Agregar una nueva información organizacional.");

        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<InformacionOrganizacional> entity = new HttpEntity<InformacionOrganizacional>(informacionOrganizacional, headers);

        //Se valida si el archivo recibido no esta vacio
        if (!file.isEmpty()) {
            LocalDate fecha = LocalDate.now();
            String identificacion = informacionOrganizacional.getNombreNegocio() + "/" + fecha + "/";
            String ruta = "c:/Archivos/InformacionOrganizacional/" + identificacion;
            System.out.println("--->" + ruta);
            //Se invoca al metodo para guardar el archivo localmente
            String nombreArchivo = guardarAchivo(file, ruta);
            if (nombreArchivo != null) {
                String path = ruta + nombreArchivo;
                informacionOrganizacional.setArchivo(path);
            }
        }

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
        HttpEntity<Pais> entity3 = new HttpEntity<Pais>(headers);

        // Enviamos el Request via GET
        try {
            // PARA MOSTRAR LO DE LA TABLA INFORMACION ORGANIZACIONAL
            ResponseEntity<InformacionOrganizacional> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/informacionOrganizacional/{id}", 
                    HttpMethod.GET, entity, InformacionOrganizacional.class, id);
            // Agregamos al Model
            model.addAttribute("informacionOrganizacionalAttribute", result.getBody());
            
            // PARA MOSTRAR LO DE LA TABLA RUBRO (El Select)
            ResponseEntity<RubroList> result2 = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/rubros", 
                    HttpMethod.GET, entity2, RubroList.class);
            // Agregamos al Model
            model.addAttribute("rubrosList", result2.getBody().getData());
            
            // PARA MOSTRAR LO DE LA TABLA Pais (El Select)
            ResponseEntity<PaisList> result3 = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/paises", 
                    HttpMethod.GET, entity3, PaisList.class);
            // Agregamos al Model
            model.addAttribute("paisList", result3.getBody().getData());

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
            @RequestParam(value="id",  required=true) int id, Model model, @RequestParam("doc") MultipartFile file) {
        System.out.println("--> Actualizando la Informacion Organizacional.");

        ///Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<InformacionOrganizacional> entity = new HttpEntity<InformacionOrganizacional>(informacionOrganizacional, headers);
        
        //Se valida si el archivo recibido no esta vacio
        if (!file.isEmpty()) {
            LocalDate fecha = LocalDate.now();
            String identificacion = informacionOrganizacional.getNombreNegocio() + "/" + fecha + "/";
            String ruta = "c:/Archivos/InformacionOrganizacional/" + identificacion;
            System.out.println("--->" + ruta);
            //Se invoca al metodo para guardar el archivo localmente
            String nombreArchivo = guardarAchivo(file, ruta);
            if (nombreArchivo != null) {
                String path = ruta + nombreArchivo;
                informacionOrganizacional.setArchivo(path);
            }
        }

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
    
    
    
    // FILTRAR
    // Mostrar TODAS las personas en el JSP
    @RequestMapping(value = "/filtrarInformacionOrganizacionalGet", method = RequestMethod.POST)
    public String searchInformacionOrganizacionalById(@RequestParam("paisSel") int idPais, Model model) {
        System.out.println("--> Recuperando informacion organizacional con ID: " + idPais);

        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<InformacionOrganizacional> entity = new HttpEntity<InformacionOrganizacional>(headers);
        HttpEntity<Pais> entity2 = new HttpEntity<Pais>(headers);

        // Enviamos el Request via GET
        try {
            if(idPais > 0){
                ResponseEntity<InformacionOrganizacionalList> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/filtrarInformacionOrganizacional/{idPais}", 
                        HttpMethod.GET, entity, InformacionOrganizacionalList.class, idPais);
                
                model.addAttribute("informacionOrganizacionalGetAll", result.getBody().getData());
            }else{
                ResponseEntity<InformacionOrganizacionalList> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/informacionOrganizacional", 
                    HttpMethod.GET, entity, InformacionOrganizacionalList.class);
                
                model.addAttribute("informacionOrganizacionalGetAll", result.getBody().getData());
            }
            
            ResponseEntity<PaisList> result2 = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/paises", 
                    HttpMethod.GET, entity2, PaisList.class);
            
            // Agregamos al Model
            model.addAttribute("paisList", result2.getBody().getData());
            model.addAttribute("informacionOrganizacionalAttribute", new InformacionOrganizacional());

        } catch (Exception e) {
            System.out.println(e);
        }
        
        // Esto es para enviar al JSP de WEB-INF/jsp/obtenerUsuario.jsp
        return "consultarInformacionOrganizacional";
    }
    
}
