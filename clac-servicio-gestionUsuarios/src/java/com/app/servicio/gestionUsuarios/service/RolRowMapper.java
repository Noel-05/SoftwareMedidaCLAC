
package com.app.servicio.gestionUsuarios.service;

import com.app.servicio.gestionUsuarios.domain.Rol;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class RolRowMapper implements RowMapper<Rol>{
    public Rol mapRow(ResultSet rs, int i) throws SQLException {
        Rol rol = new Rol();
        rol.setIdRol(rs.getInt("idRol"));
        rol.setNombreRol(rs.getString("nombreRol"));
        
        return rol;
    }
}