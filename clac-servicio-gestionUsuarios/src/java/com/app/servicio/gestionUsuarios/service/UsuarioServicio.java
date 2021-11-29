
package com.app.servicio.gestionUsuarios.service;

import com.app.servicio.gestionUsuarios.connect.Conexion;
import com.app.servicio.gestionUsuarios.domain.Usuario;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service("usuarioServicio")
public class UsuarioServicio {
    
    // Conexión a la BD.
    Conexion con = new Conexion();
    JdbcTemplate jdbcTemplate = new JdbcTemplate(con.Conectar());
    
    // Variables a utilizar
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    List datos;
    
    
    // LOGIN    
    public String login(String user, String pass){
        System.out.println("Buscando al usuario con sus credenciales en la BD.");
        
        String resul;
        
        String sql = "select * from registro.usuarios where correoUsuario = ? and contrasenaUsuario = ?";
        
        resul = this.jdbcTemplate.query(sql, new UsuarioRowMapper(), user, pass).toString();
        
        System.out.println("===========");
        System.out.println(resul);
        System.out.println(resul.length());
        
        return resul;
        
    }
    
    // LISTAR todos los usuarios
    public List<Usuario> getAll(){
        System.out.println("Recuperando todos los usuarios de la BD.");
        
        String sql = "SELECT * FROM registro.usuarios AS USUA INNER JOIN registro.rol AS ROL ON USUA.idRol = ROL.idRol";
        
        usuarios = this.jdbcTemplate.query(sql, new UsuarioRowMapper2());
        
        System.out.println(usuarios);
        
        return usuarios;
    }
    
    
    // CONSULTAR usuarios por ID
    public Usuario getByID(int idUsuario){
        System.out.println("Recuperando usuario con ID: " + idUsuario);
        
        String sql = "SELECT * FROM registro.usuarios AS USUA INNER JOIN registro.rol AS ROL ON USUA.idRol = ROL.idRol WHERE USUA.idUsuario = ?";
        
        return this.jdbcTemplate.queryForObject(sql, new UsuarioRowMapper2(), idUsuario);
    }
    
    
    // CREAR usuarios
    public Usuario add(Usuario usuario){
        System.out.println("Insertando nuevo usuario.");
        
        // Encriptamos la contraseña
        String pass = usuario.getPassword();
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
        
        try{
            String sql = "INSERT INTO registro.usuarios(idRol, nombresUsuario, apellidosUsuario, correoUsuario, contrasenaUsuario) VALUES(?, ?, ?, ?, ?)";
            
            this.jdbcTemplate.update(sql, usuario.getIdRol(),usuario.getNombre(), usuario.getApellido(),usuario.getCorreo(), encriptado);
            
            System.out.println("Usuario Insertado Correctamente. ");
            
            return usuario;
            
        }catch(Exception e){
            System.out.println(e);
            
            return null;
        }
    }
    
    
    // EDITAR usuarios
    public Boolean edit(Usuario usuario){
        System.out.println("Editando Usuario con ID: " + usuario.getIdUsuario());
        
        // Encriptamos la contraseña
        String pass = usuario.getPassword();
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
        
        try{
            String sql = "UPDATE registro.usuarios SET idRol = ?, nombresUsuario = ?, apellidosUsuario = ?, correoUsuario = ?, contrasenaUsuario=? WHERE idUsuario = ?";
            
            this.jdbcTemplate.update(sql, usuario.getIdRol(), usuario.getNombre(), usuario.getApellido(),usuario.getCorreo(), encriptado, usuario.getIdUsuario());
            
            System.out.println("Usuario Actualizado Correctamente.");
            
            return true;
            
        }catch(Exception e){
            System.out.println(e);
            
            return false;
        }
    }
    
    
    // ELIMINAR usuarios
    public Boolean delete(int idUsuario){
        System.out.println("Eliminando usuario con ID: " + idUsuario);
        
        try{
            String sql = "DELETE FROM registro.usuarios WHERE idUsuario = " + idUsuario;
            
            this.jdbcTemplate.update(sql);
            
            System.out.println("Usuario Eliminado Correctamente");
            
            return true;
            
        }catch(Exception e){
            System.out.println(e);
            
            return false;
        }
    }
    
}
