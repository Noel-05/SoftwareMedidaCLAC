
package com.app.servicio.gestionVisitas.service;

import com.app.servicio.gestionVisitas.domain.Bienes;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class BienesRowMapper2 implements RowMapper<Bienes>{

    @Override
    public Bienes mapRow(ResultSet rs, int i) throws SQLException {
        Bienes bienes = new Bienes();
        
        bienes.setIdBien(rs.getInt("idBien"));
        bienes.setIdInformacionFinanciera(rs.getInt("idInformacionFinanciera"));
        bienes.setNombreNegocio(rs.getString("nombreNegocio"));
        bienes.setNombreBien(rs.getString("nombreBien"));
        bienes.setCantidadBien(rs.getInt("cantidadBien"));
        bienes.setArchivo(rs.getString("archivoBienes"));
        
        return bienes;
    }
    
}