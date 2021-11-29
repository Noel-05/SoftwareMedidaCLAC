
package com.app.cliente.controller;

import com.app.cliente.domain.usuarios.Rol;
import com.app.cliente.domain.usuarios.RolList;
import com.app.cliente.domain.usuarios.Usuario;
import com.app.cliente.domain.usuarios.UsuarioList;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Base64;
import javafx.beans.binding.Bindings;
import jdk.nashorn.internal.runtime.JSONFunctions;
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
public class UsuarioController {
        
    private RestTemplate restTemplate = new RestTemplate();
    ModelAndView mav = new ModelAndView();
    
    
    // Login
    @RequestMapping("/")
    public ModelAndView login(){
        mav.setViewName("login");
        return mav;
    }
    
    
    // INICIAR SESION
    @RequestMapping(value = "/iniciarSesion", method = RequestMethod.POST)
    public String login(@RequestParam("correo") String correo, @RequestParam("password") String password, Model model) {
        System.out.println("--> Buscando el usuario = "+correo);
        
        // Encriptamos la contraseña
        String pass = password;
        byte[] newPassword = null;
        try {
            newPassword = MessageDigest.getInstance("SHA").digest(pass.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();   
        }
        String encriptado = Base64.getEncoder().encodeToString(newPassword);
        encriptado = encriptado.replace("/", "");
        System.out.println("________________");
        System.out.println(encriptado);
        

        ///Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Usuario> entity = new HttpEntity<Usuario>(headers);

        ResponseEntity<String> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionUsuarios/login/{correo}/{password}",
                HttpMethod.GET, entity, String.class, correo, encriptado);

        System.out.println(">>>>>>>>>>>");
        System.out.println(result);
        System.out.println(result.toString().length());
        
        if(result.toString().length() >= 320){
            // Esto es para enviar al JSP de WEB-INF/jsp/consultarUsuarios.jsp
            mav.addObject("alerta", "sinAlerta");
            return "redirect:/index";
            //return "nav";
        }else{
            // Esto es para enviar al JSP de WEB-INF/jsp/consultarUsuarios.jsp
            mav.addObject("alerta", "alerta");
            return "redirect:/";
        }
    }
    
    
    // Index
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }
    
    
    // LISTAR
    // Mostrar TODAS los Usuarios en el JSP
    @RequestMapping(value = "/getallUsuarios", method = RequestMethod.GET)
    public String getAllUsuarios(Model model){
        System.out.println("--> Recuperar Todas los Usuarios de mi BD.");
        
        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);
        
        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Usuario> entity = new HttpEntity<Usuario>(headers);
        
        //Enviamos el request via GET
        try{
            ResponseEntity<UsuarioList> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionUsuarios/usuarios", 
                    HttpMethod.GET, entity, UsuarioList.class);
            // Agregamos al Model
            model.addAttribute("usuariosGetAll", result.getBody().getData());
        
        }catch(Exception e){
            System.out.println(e);
        }
        
        // Esto es para enviar al JSP de WEB-INF/jsp/consultarUsuarios.jsp
        return "consultarUsuarios";
    }
    
    
    // CONSULTAR
    // Mostrar los datos de un Usuario en el JSP por su ID
    @RequestMapping(value = "/getUsuarios", method = RequestMethod.GET)
    public String getUsuario(@RequestParam("idUsuario") int idUsuario, Model model) {
        System.out.println("--> Recuperando usuario con ID: " + idUsuario);

        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Usuario> entity = new HttpEntity<Usuario>(headers);

        // Enviamos el Request via GET
        try {
            ResponseEntity<Usuario> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionUsuarios/usuarios/{idUsuario}", 
                    HttpMethod.GET, entity, Usuario.class, idUsuario);
            // Agregamos al Model
            model.addAttribute("usuarioGetId", result.getBody());

        } catch (Exception e) {
            System.out.println(e);
        }
        
        // Esto es para enviar al JSP de WEB-INF/jsp/obtenerUsuario.jsp
        return "obtenerUsuario";
    }
    
    
    // CREAR
    // Mostrar el JSP para Agregar Usuario
    @RequestMapping(value = "/addUsuarios", method = RequestMethod.GET)
    public String getAddPageUsuarios(Model model) {
        System.out.println("--> Recibiendo el Request para mostrarlo en la página de agregar.");
        
        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Rol> entity = new HttpEntity<Rol>(headers);
        
        //Enviamos el request via GET
        try{
            ResponseEntity<RolList> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionUsuarios/roles", 
                    HttpMethod.GET, entity, RolList.class);
            
            // Agregamos al Model
            model.addAttribute("rolList", result.getBody().getData());
            model.addAttribute("usuarioAttribute", new Usuario());
        
        }catch(Exception e){
            System.out.println(e);
        }

        // Esto es para enviar al JSP de WEB-INF/jsp/agregarUsuario.jsp
        return "agregarUsuario";
    }
    
    // CREAR
    // Envíamos el registro y una solicitud de actualización por el metodo GET basados en la información que se envía en submit
    @RequestMapping(value = "/addUsuarios", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("usuarioAttribute") Usuario usuario, Model model) {
        System.out.println("--> Agregar una nueva usuario.");

        //Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Usuario> entity = new HttpEntity<Usuario>(usuario, headers);

        // Enviamos el Request via POST
        try {
            ResponseEntity<Usuario> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionUsuarios/usuariosAdd", 
                    HttpMethod.POST, entity, Usuario.class);
            
        } catch (Exception e) {
            System.out.println(e);
        }

        // Esto es para enviar al JSP de WEB-INF/jsp/consultarUsuarios.jsp
        return "redirect:/getallUsuarios";
    }
    
    
    // EDITAR
    // Mostrar y Recuperar los datos del usario a editar en el JSP
    @RequestMapping(value = "/updateUsuarios", method = RequestMethod.GET)
    public String getUpdatePageUsuarios(@RequestParam(value="idUsuario", required=true) Integer idUsuario, Model model) {
    	   System.out.println("--> Recibiendo el Request para mostrarlo en la página de editar.");
    
    	//Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Usuario> entity = new HttpEntity<Usuario>(headers);
        HttpEntity<Rol> entity2 = new HttpEntity<Rol>(headers);

        // Enviamos el Request via GET
        try {
            ResponseEntity<Usuario> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionUsuarios/usuarios/{idUsuario}", 
                    HttpMethod.GET, entity, Usuario.class, idUsuario);
            // Agregamos al Model
            model.addAttribute("usuarioAttribute", result.getBody());
            
            // PARA MOSTRAR LO DE LA TABLA RUBRO (El Select)
            ResponseEntity<RolList> result2 = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionUsuarios/roles", 
                    HttpMethod.GET, entity2, RolList.class);
            // Agregamos al Model
            model.addAttribute("rolList", result2.getBody().getData());

        } catch (Exception e) {
                System.out.println(e);
        }
    	
    	// Esto es para enviar al JSP de WEB-INF/jsp/actualizarUsuario.jsp
    	return "actualizarUsuario";
    }
    
    // EDITAR
    // Envíar una solicitud de actualización basados en la información enviada en el submit
    @RequestMapping(value = "/updateUsuarios", method = RequestMethod.POST)
    public String updateUsuarios(@ModelAttribute("usuarioAttribute") Usuario usuario,
            @RequestParam(value="idUsuario",  required=true) int idUsuario, Model model) {
        System.out.println("--> Actualizando el usuario.");

        ///Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Usuario> entity = new HttpEntity<Usuario>(usuario, headers);

        // Enviamos el Request via PUT
        ResponseEntity<String> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionUsuarios/usuariosUp/{idUsuario}", 
                HttpMethod.PUT, entity, String.class, idUsuario);

        // Esto es para enviar al JSP de WEB-INF/jsp/consultarUsuarios.jsp
        return "redirect:/getallUsuarios";
    }
    
    
    // ELIMINAR
    // Envíamos una solicitud de eliminar
    @RequestMapping(value = "/deleteUsuarios", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("idUsuario") int idUsuario, Model model) {
       System.out.println("--> Eliminando a la persona.");

        ///Preparar Tipos de datos a trabajar
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        //Preparo el header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        // Enviamos el Request via PUT
        ResponseEntity<String> result = restTemplate.exchange("http://localhost:8080/clac-servicio-gestionUsuarios/usuariosDel/{idUsuario}", 
                HttpMethod.DELETE, entity, String.class, idUsuario);

        // Esto es para enviar al JSP de WEB-INF/jsp/consultarUsuarios.jsp
        return "redirect:/getallUsuarios";
    }
}
