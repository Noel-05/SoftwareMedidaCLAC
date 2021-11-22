
package com.app.servicio.gestionVisitas.service;

import com.app.servicio.gestionVisitas.domain.Pais;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class PaisRowMapper implements RowMapper<Pais>{
    public Pais mapRow(ResultSet rs, int i) throws SQLException {
        Pais pa = new Pais();
        pa.setIdPais(rs.getInt("idpais"));
        pa.setNombrePais(rs.getString("nombrePais"));
        
        return pa;
    }
}
