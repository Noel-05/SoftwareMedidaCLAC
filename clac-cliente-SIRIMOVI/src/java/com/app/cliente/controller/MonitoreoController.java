package com.app.cliente.controller;

import com.app.cliente.domain.visitas.InformacionOrganizacional;
import com.app.cliente.domain.visitas.InformacionOrganizacionalList;
import com.app.cliente.domain.monitoreos.Monitoreo;
import com.app.cliente.domain.monitoreos.MonitoreoList;
import com.app.cliente.domain.visitas.Pais;
import com.app.cliente.domain.visitas.PaisList;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MonitoreoController {

    private RestTemplate restTemplate = new RestTemplate();
    ModelAndView mav = new ModelAndView();

    // LISTAR
    // Mostrar TODOS las monitoreos en el JSP
    @RequestMapping(value = "/getallMonitoreos", method = RequestMethod.GET)
    public String getAllMonitoreo(Model model) {
        System.out.println("--> Recuperar Todos las Monitoreos de mi BD.");

        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Monitoreo> entity = new HttpEntity<Monitoreo>(headers);
        HttpEntity<Pais> entity2 = new HttpEntity<Pais>(headers);
        
        //Enviamos el request via GET
        try {
            ResponseEntity<MonitoreoList> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionMonitoreos/getallMonitoreos",
                    HttpMethod.GET, entity, MonitoreoList.class);
            
            ResponseEntity<PaisList> result2 = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/paises", 
                    HttpMethod.GET, entity2, PaisList.class);
            
            // Agregamos al Model
            model.addAttribute("paisList", result2.getBody().getData());
            model.addAttribute("monitoreosGetAll", result.getBody().getData());
            model.addAttribute("empresas");
        
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
        try {
            ResponseEntity<InformacionOrganizacionalList> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/informacionOrganizacional",
                    HttpMethod.GET, entity, InformacionOrganizacionalList.class);
            // Agregamos al Model
            model.addAttribute("informacionOrganizacionalList", result.getBody().getData());
            model.addAttribute("monitoreoAttribute", new Monitoreo());

        } catch (Exception e) {
            System.out.println(e);
        }

        // Esto es para enviar al JSP de WEB-INF/jsp/agregarMonitoreo.jsp
        return "agregarMonitoreo";
    }

    // CREAR
    // Envíamos el registro y una solicitud de actualización por el metodo GET basados en la información que se envía en submit
    @RequestMapping(value = "/monitoreosAdd", method = RequestMethod.POST)
    public String addPersonMonitoreo(@ModelAttribute("monitoreoAttribute") Monitoreo monitoreo, Model model, @RequestParam("doc") MultipartFile file) {
        System.out.println("--> Agregar un nuevo monitoreo.");

        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Monitoreo> entity = new HttpEntity<Monitoreo>(monitoreo, headers);

        //Se valida si el archivo recibido no esta vacio
        if (!file.isEmpty()) {
            LocalDate fecha = LocalDate.now();
            String identificacion = monitoreo.getIdInfo() + "/" + fecha + "/";
            String ruta = "c:/Archivos/Monitoreo/" + identificacion;
            System.out.println("--->" + ruta);
            //Se invoca al metodo para guardar el archivo localmente
            String nombreArchivo = guardarAchivo(file, ruta);
            if (nombreArchivo != null) {
                String path = ruta + nombreArchivo;
                monitoreo.setArchivo(path);
            }
        }

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

    // EDITAR MONITOREO
    // Mostrar y Recuperar los datos de la monitoreo a editar en el JSP
    @RequestMapping(value = "/updateMonitoreo", method = RequestMethod.GET)
    public String getUpdatePageMonitoreo(@RequestParam(value = "id", required = true) Integer id, Model model) {
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
            @RequestParam(value = "id", required = true) int id, Model model, @RequestParam("doc") MultipartFile file) {
        System.out.println("--> Actualizando la monitoreo.");

        ///Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Monitoreo> entity = new HttpEntity<Monitoreo>(monitoreo, headers);
        
        //Se valida si el archivo recibido no esta vacio
        if (!file.isEmpty()) {
            LocalDate fecha = LocalDate.now();
            String identificacion = monitoreo.getIdInfo() + "/" + fecha + "/";
            String ruta = "c:/Archivos/Monitoreo/" + identificacion;
            System.out.println("--->" + ruta);
            //Se invoca al metodo para guardar el archivo localmente
            String nombreArchivo = guardarAchivo(file, ruta);
            if (nombreArchivo != null) {
                String path = ruta + nombreArchivo;
                monitoreo.setArchivo(path);
            }
        }

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
    
    
    
    // FILTRAR
    // Mostrar TODAS las personas en el JSP
    @RequestMapping(value = "/filtrarMonitoreoGet", method = RequestMethod.POST)
    public String searchMonitoreoById(@RequestParam("paisSel") int idPais, Model model) {
        System.out.println("--> Recuperando monitoreos con idPais: " + idPais);

        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Monitoreo> entity = new HttpEntity<Monitoreo>(headers);
        HttpEntity<Pais> entity2 = new HttpEntity<Pais>(headers);
        HttpEntity<InformacionOrganizacional> entity3 = new HttpEntity<InformacionOrganizacional>(headers);

        // Enviamos el Request via GET
        try {
            if(idPais > 0){
                ResponseEntity<MonitoreoList> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionMonitoreos/filtrarMonitoreo/{idPais}", 
                        HttpMethod.GET, entity, MonitoreoList.class, idPais);
                
                ResponseEntity<InformacionOrganizacionalList> result3 = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/informacionOrganizacional", 
                    HttpMethod.GET, entity3, InformacionOrganizacionalList.class);
                
                model.addAttribute("monitoreosGetAll", result.getBody().getData());
            }else{
                ResponseEntity<MonitoreoList> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionMonitoreos/getallMonitoreos", 
                    HttpMethod.GET, entity, MonitoreoList.class);
                
                model.addAttribute("monitoreosGetAll", result.getBody().getData());
            }
            
            ResponseEntity<PaisList> result2 = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/paises", 
                    HttpMethod.GET, entity2, PaisList.class);
            
            // Agregamos al Model
            model.addAttribute("paisList", result2.getBody().getData());
            model.addAttribute("monitoreoAttribute", new Monitoreo());

        } catch (Exception e) {
            System.out.println(e);
        }
        
        // Esto es para enviar al JSP de WEB-INF/jsp/obtenerUsuario.jsp
        return "consultarMonitoreo";
    }

    
    //Metodo para descargar archivos (ESTE METODO ES GENERAL DEBERIA IR EN OTRO PAQUETE)
    @RequestMapping(value = "/descarga", method = RequestMethod.GET)
    public void descargar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //Path del archivo
        File file = new File(request.getParameter("archivo"));

        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            String mimeType = URLConnection.guessContentTypeFromStream(inputStream);

            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }

            response.setContentType(mimeType);
            response.setContentLength((int) file.length());
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));

            FileCopyUtils.copy(inputStream, response.getOutputStream());
        } catch (Exception e) {
            System.out.println(e);
            //Aqui deberia ir una alerta que no se encontro el archivo
        }
    }
}
