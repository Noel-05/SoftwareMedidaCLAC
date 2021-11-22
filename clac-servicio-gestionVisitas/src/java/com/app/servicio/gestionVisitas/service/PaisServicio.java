
package com.app.servicio.gestionVisitas.service;

import com.app.servicio.gestionVisitas.connect.Conexion;
import com.app.servicio.gestionVisitas.domain.Pais;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service("paisServicio")
public class PaisServicio {
    // Conexión a la BD.
    Conexion con = new Conexion();
    JdbcTemplate jdbcTemplate = new JdbcTemplate(con.Conectar());
    
    // Variables a utilizar
    private List<Pais> paises = new ArrayList<Pais>();
    List datos;
    
    
   // LISTAR todas las personas
    public List<Pais> getAll(){
        System.out.println("Recuperando todos los paises de la BD.");
        
        String sql = "SELECT * FROM registro.pais ORDER BY nombrePais ASC";
        
        paises = this.jdbcTemplate.query(sql, new PaisRowMapper());
        
        System.out.println(paises);
        
        return paises;
    }
    
}