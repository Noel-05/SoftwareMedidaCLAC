
package com.app.servicio.gestionVisitas.service;

import com.app.servicio.gestionVisitas.domain.InformacionComercial;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class InformacionComercialRowMapper2 implements RowMapper<InformacionComercial>{
 
    @Override
    public InformacionComercial mapRow(ResultSet rs, int i) throws SQLException {
        InformacionComercial infCom = new InformacionComercial();
        
        infCom.setIdInformacionComercial(rs.getInt("idInformacionComercial"));
        infCom.setIdInformacionOrganizacional(rs.getInt("idInformacionOrganizacional"));
        infCom.setNombreNegocio(rs.getString("nombreNegocio"));
        infCom.setDepartamentosDeComercioInterior(rs.getString("departamentosDeComercioInterior"));
        infCom.setPaisesDeComercioExterior(rs.getString("paisesDeComercioExterior"));
        infCom.setEmpresasSocias(rs.getString("empresasSocias"));
        infCom.setArchivo(rs.getString("archivoInformacionComercial"));
        
        return infCom;
    }
}
