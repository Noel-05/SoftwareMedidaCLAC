
package com.app.servicio.gestionVisitas.service;

import com.app.servicio.gestionVisitas.domain.Rubro;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class RubroRowMapper implements RowMapper<Rubro>{
    public Rubro mapRow(ResultSet rs, int i) throws SQLException {
        Rubro rub = new Rubro();
        rub.setIdRubro(rs.getInt("idRubro"));
        rub.setNombreRubro(rs.getString("nombreRubro"));
        
        return rub;
    }
}
