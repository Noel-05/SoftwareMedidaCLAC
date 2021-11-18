
package com.app.servicio.gestionVisitas.service;

import com.app.servicio.gestionVisitas.connect.Conexion;
import com.app.servicio.gestionVisitas.domain.Bienes;
import com.app.servicio.gestionVisitas.domain.Producto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service("bienesServicio")
public class BienesServicio {
    
    // Conexión a la BD.
    Conexion con = new Conexion();
    JdbcTemplate jdbcTemplate = new JdbcTemplate(con.Conectar());
    
    // Variables a utilizar
    private List<Bienes> bienes = new ArrayList<Bienes>();
    List datos;
 
    
    // LISTAR todos los Bienes
    public List<Bienes> getAll(){
        System.out.println("Recuperando todos los bienes de la BD.");
        
        String sql = "SELECT * FROM registro.bienes AS B INNER JOIN registro.informacionfinanciera AS INFIN ON B.idInformacionFinanciera = INFIN.idInformacionFinanciera INNER JOIN registro.informacionorganizacional AS INFORG ON INFIN.idInformacionOrganizacional = INFORG.idInformacionOrganizacional";
        
        bienes = this.jdbcTemplate.query(sql, new BienesRowMapper2());
        
        System.out.println(bienes);
        
        return bienes;
    }
    
    
    //CONSULTAR produto
    public Bienes getByID(int id){
        System.out.println("Recuperando bien con ID: " + id);
        
        String sql = "SELECT * FROM registro.bienes AS B INNER JOIN registro.informacionfinanciera AS INFIN ON B.idInformacionFinanciera = INFIN.idInformacionFinanciera INNER JOIN registro.informacionorganizacional AS INFORG ON INFIN.idInformacionOrganizacional = INFORG.idInformacionOrganizacional WHERE B.idBien = ?";
        
        return this.jdbcTemplate.queryForObject(sql, new BienesRowMapper2(), id);
    }
    
    
    // CREAR Bien
    public Bienes add(Bienes bienes){
        System.out.println("Insertando nuevo bien.");
        
        try{
            String sql = "INSERT INTO registro.bienes(idInformacionFinanciera, nombreBien, cantidadBien) VALUES(?, ?, ?)";
            
            this.jdbcTemplate.update(sql, bienes.getIdInformacionFinanciera(), bienes.getNombreBien(), bienes.getCantidadBien());
            
            System.out.println("Bien Insertado Correctamente. ");
            
            return bienes;
            
        }catch(Exception e){
            System.out.println(e);
            
            return null;
        }
    }
    
    
    // EDITAR Producto
    public Boolean edit(Bienes bien){
        System.out.println("Editando bien con ID: " + bien.getIdBien());
        
        try{
            String sql = "UPDATE registro.bienes SET idInformacionFinanciera=?, nombreBien=?, cantidadBien=? WHERE idBien = ?";
            
            this.jdbcTemplate.update(sql, bien.getIdInformacionFinanciera(), bien.getNombreBien(), bien.getCantidadBien(), bien.getIdBien());
            
            System.out.println("Bien Actualizado Correctamente.");
            
            return true;
            
        }catch(Exception e){
            System.out.println(e);
            
            return false;
        }
    }
    
    
    // ELIMINAR bien
    public Boolean delete(int id){
        System.out.println("(VISITAS) Eliminando bien con ID: " + id);
        
        try{
            String sql = "DELETE FROM registro.bienes WHERE idBien = " + id;
            
            this.jdbcTemplate.update(sql);
            
            System.out.println("Bien Eliminado Correctamente");
            
            return true;
            
        }catch(Exception e){
            System.out.println(e);
            
            return false;
        }
    }
   
}