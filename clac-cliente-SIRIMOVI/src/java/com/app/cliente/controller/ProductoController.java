
package com.app.cliente.controller;

import com.app.cliente.domain.visitas.InformacionComercial;
import com.app.cliente.domain.visitas.InformacionComercialList;
import com.app.cliente.domain.visitas.Producto;
import com.app.cliente.domain.visitas.ProductoList;
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
public class ProductoController {
    
    private RestTemplate restTemplate = new RestTemplate();
    ModelAndView mav = new ModelAndView();
    
    // LISTAR
    // Mostrar TODAS las personas en el JSP
    @RequestMapping(value = "/getallProducto", method = RequestMethod.GET)
    public String getAllProductos(Model model){
        System.out.println("--> Recuperar Todas los productos de mi BD.");
        
        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);
        
        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Producto> entity = new HttpEntity<Producto>(headers);
        
        //Enviamos el request via GET
        try{
            ResponseEntity<ProductoList> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/productos", 
                    HttpMethod.GET, entity, ProductoList.class);
            // Agregamos al Model
            model.addAttribute("productosGetAll", result.getBody().getData());
        
        }catch(Exception e){
            System.out.println(e);
        }
        
        // Esto es para enviar al JSP de WEB-INF/jsp/consultarPersonas.jsp
        return "consultarProductos";
    }
    
    
    // CONSULTAR
    // Mostrar los datos de una persona en el JSP por su ID
    @RequestMapping(value = "/getProducto", method = RequestMethod.GET)
    public String getProducto(@RequestParam("id") int id, Model model) {
        System.out.println("--> Recuperando producto con ID: " + id);

        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Producto> entity = new HttpEntity<Producto>(headers);

        // Enviamos el Request via GET
        try {
            ResponseEntity<Producto> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/productos/{id}", 
                    HttpMethod.GET, entity, Producto.class, id);
            // Agregamos al Model
            model.addAttribute("productoGetId", result.getBody());

        } catch (Exception e) {
            System.out.println(e);
        }
        
        // Esto es para enviar al JSP de WEB-INF/jsp/obtenerPersona.jsp
        return "obtenerProducto";
    }
    
    
    // CREAR
    // Mostrar el JSP para Agregar Producto
    @RequestMapping(value = "/addProducto", method = RequestMethod.GET)
    public String getAddPageProducto(Model model) {
        System.out.println("--> Recibiendo el Request para mostrarlo en la página de agregar.");
        
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
            model.addAttribute("informacionComercialList", result.getBody().getData());
            model.addAttribute("productoAttribute", new Producto());
        
        }catch(Exception e){
            System.out.println(e);
        }

        // Esto es para enviar al JSP de WEB-INF/jsp/agregarPersona.jsp
        return "agregarProducto";
    }
    
    // CREAR
    // Envíamos el registro y una solicitud de actualización por el metodo GET basados en la información que se envía en submit
    @RequestMapping(value = "/addProducto", method = RequestMethod.POST)
    public String addProducto(@ModelAttribute("productoAttribute") Producto producto, Model model, @RequestParam("doc") MultipartFile file) {
        System.out.println("--> Agregar un nuevo Producto.");

        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Producto> entity = new HttpEntity<Producto>(producto, headers);
        
        //Se valida si el archivo recibido no esta vacio
        if (!file.isEmpty()) {
            LocalDate fecha = LocalDate.now();
            String identificacion = producto.getIdInformacionComercial() + "/" + fecha + "/";
            String ruta = "c:/Archivos/Producto/" + identificacion;
            System.out.println("--->" + ruta);
            //Se invoca al metodo para guardar el archivo localmente
            String nombreArchivo = guardarAchivo(file, ruta);
            if (nombreArchivo != null) {
                String path = ruta + nombreArchivo;
                producto.setArchivo(path);
            }
        }

        // Enviamos el Request via POST
        try {
            ResponseEntity<Producto> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/addProducto", 
                    HttpMethod.POST, entity, Producto.class);
            
        } catch (Exception e) {
            System.out.println(e);
        }

        // Esto es para enviar al JSP de WEB-INF/jsp/consultarPersonas.jsp
        return "redirect:/getallProducto";
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
    @RequestMapping(value = "/updateProducto", method = RequestMethod.GET)
    public String getUpdatePageProducto(@RequestParam(value="id", required=true) Integer id, Model model) {
    	   System.out.println("--> Recibiendo el Request para mostrarlo en la página de editar.");
    
    	//Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Producto> entity = new HttpEntity<Producto>(headers);
        HttpEntity<InformacionComercial> entity2 = new HttpEntity<InformacionComercial>(headers);

        // Enviamos el Request via GET
        try {
            ResponseEntity<Producto> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/productos/{id}", 
                    HttpMethod.GET, entity, Producto.class, id);
            // Agregamos al Model
            model.addAttribute("productoAttribute", result.getBody());
            
            ResponseEntity<InformacionComercialList> result2 = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/informacionComercial", 
                    HttpMethod.GET, entity2, InformacionComercialList.class);
            // Agregamos al Model
            model.addAttribute("informacionComercialList", result2.getBody().getData());

        } catch (Exception e) {
                System.out.println(e);
        }
    	
    	// Esto es para enviar al JSP de WEB-INF/jsp/actualizarPersona.jsp
    	return "actualizarProducto";
    }
    
    // EDITAR
    // Envíar una solicitud de actualización basados en la información enviada en el submit
    @RequestMapping(value = "/updateProducto", method = RequestMethod.POST)
    public String updateProducto(@ModelAttribute("productoAttribute") Producto producto,
            @RequestParam(value="id",  required=true) int id, Model model) {
        System.out.println("--> Actualizando el Producto.");

        ///Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Producto> entity = new HttpEntity<Producto>(producto, headers);

        // Enviamos el Request via PUT
        ResponseEntity<String> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/productosUp/{id}", 
                HttpMethod.PUT, entity, String.class, id);

        // Esto es para enviar al JSP de WEB-INF/jsp/consultarPersonas.jsp
        return "redirect:/getallProducto";
    }
    
    
    // ELIMINAR
    // Envíamos una solicitud de eliminar
    @RequestMapping(value = "/deleteProducto", method = RequestMethod.GET)
    public String deleteProducto(@RequestParam("id") int id, Model model) {
       System.out.println("--> Eliminando el producto.");

        ///Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        // Enviamos el Request via PUT
        ResponseEntity<String> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/productoDel/{id}", 
                HttpMethod.DELETE, entity, String.class, id);

        // Esto es para enviar al JSP de WEB-INF/jsp/consultarPersonas.jsp
        return "redirect:/getallProducto";
    }
    
    
            
    // LISTAR FILTRADA
    // Mostrar TODAS las personas en el JSP
    @RequestMapping(value = "/getallProductoF", method = RequestMethod.GET)
    public String getAllBienesF(@RequestParam("idInfCom") int idInfCom, Model model) {
        System.out.println("--> Recuperar los Productos de: " + idInfCom);
        
        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);
        
        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Producto> entity = new HttpEntity<Producto>(headers);
        
        //Enviamos el request via GET
        try{
            ResponseEntity<ProductoList> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionVisitas/productosF/{idInfCom}", 
                    HttpMethod.GET, entity, ProductoList.class, idInfCom);
            
            // Agregamos al Model
            model.addAttribute("productosGetAll", result.getBody().getData());
        
        }catch(Exception e){
            System.out.println(e);
        }
        
        // Esto es para enviar al JSP de WEB-INF/jsp/consultarPersonas.jsp
        return "consultarProductos";
    }
    
    //Metodo para descargar archivos
   /*@RequestMapping(value = "/descarga", method = RequestMethod.GET)
    public void descargar(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //Path del archivo
        File file = new File(request.getParameter("archivo"));
        
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        String mimeType = URLConnection.guessContentTypeFromStream(inputStream);
        
        if(mimeType == null){
            mimeType = "application/octet-stream";
        }
        
        response.setContentType(mimeType);
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }*/
}
