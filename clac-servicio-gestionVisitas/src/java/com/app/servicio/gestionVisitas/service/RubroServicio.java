
package com.app.servicio.gestionVisitas.service;

import com.app.servicio.gestionVisitas.connect.Conexion;
import com.app.servicio.gestionVisitas.domain.Rubro;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service("rubroServicio")
public class RubroServicio {
    // Conexión a la BD.
    Conexion con = new Conexion();
    JdbcTemplate jdbcTemplate = new JdbcTemplate(con.Conectar());
    
    // Variables a utilizar
    private List<Rubro> rubros = new ArrayList<Rubro>();
    List datos;
    
    
   // LISTAR todas las personas
    public List<Rubro> getAll(){
        System.out.println("Recuperando todos los rubros de la BD.");
        
        String sql = "SELECT * FROM registro.rubro ORDER BY nombreRubro ASC;";
        
        rubros = this.jdbcTemplate.query(sql, new RubroRowMapper());
        
        System.out.println(rubros);
        
        return rubros;
    }
    
}
