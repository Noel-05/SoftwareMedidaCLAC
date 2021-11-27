
package com.app.servicio.gestionVisitas.service;

import com.app.servicio.gestionVisitas.connect.Conexion;
import com.app.servicio.gestionVisitas.domain.InformacionOrganizacional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service("informacionOrganizacionalServicio")
public class InformacionOrganizacionalServicio {
    
    // Conexión a la BD.
    Conexion con = new Conexion();
    JdbcTemplate jdbcTemplate = new JdbcTemplate(con.Conectar());
    
    // Variables a utilizar
    private List<InformacionOrganizacional> informacionOrganizacionales = new ArrayList<InformacionOrganizacional>();
    List datos;
    
    
    // LISTAR InformacionOrganizacional
    public List<InformacionOrganizacional> getAll(){
        System.out.println("Recuperando todos los datos de información organizacional de la BD.");
        
        String sql = "SELECT * FROM registro.informacionorganizacional AS INFORG INNER JOIN registro.rubro AS R ON INFORG.idRubro = R.idRubro INNER JOIN registro.pais P ON INFORG.idPais = P.idpais ORDER BY INFORG.nombreNegocio, P.nombrePais ASC";
        
        informacionOrganizacionales = this.jdbcTemplate.query(sql, new InformacionOrganizacionalRowMapper2());
        
        System.out.println(informacionOrganizacionales);
        
        return informacionOrganizacionales;
    }

    
    // CONSULTAR informacionOrganizacional por ID
    public InformacionOrganizacional getByID(int id){
        System.out.println("Recuperando informacionOrganizacional con ID: " + id);
        
        String sql = "SELECT * FROM registro.informacionorganizacional AS INFORG INNER JOIN registro.rubro AS R ON INFORG.idRubro = R.idRubro INNER JOIN registro.pais P ON INFORG.idPais = P.idpais WHERE INFORG.idInformacionOrganizacional = ?";
        
        return this.jdbcTemplate.queryForObject(sql, new InformacionOrganizacionalRowMapper2(), id);
    }
    
    
    // CREAR InformacionOrganizacional
    public InformacionOrganizacional add(InformacionOrganizacional informacionOrganizacional){
        System.out.println("Insertando nueva informacion organizacional.");
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(informacionOrganizacional.getFechaRegistroVisita());
        cal.add(Calendar.DAY_OF_YEAR, 1);
        
        try{
            String sql = "INSERT INTO registro.informacionorganizacional(idRubro, nombreNegocio, cantidadEmpleados, direccionNegocio, cantidadSucursales, fechaRegistroVisita, idPais, archivoInformacionOrganizacional) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            
            this.jdbcTemplate.update(sql, informacionOrganizacional.getIdRubro(), informacionOrganizacional.getNombreNegocio(), 
                    informacionOrganizacional.getCantEmpleados(), informacionOrganizacional.getDireccionNegocio(), 
                    informacionOrganizacional.getCantSucursales(), cal, informacionOrganizacional.getIdPais(), informacionOrganizacional.getArchivo());
            
            System.out.println("Información Organizacional Insertada Correctamente. ");
            
            return informacionOrganizacional;
            
        }catch(Exception e){
            System.out.println(e);
            
            return null;
        }
    }
    
 
    // EDITAR InformacionOrganizacional
    public Boolean edit(InformacionOrganizacional informacionOrganizacional){
        System.out.println("Editando información organizacional con ID: " + informacionOrganizacional.getIdInfOrganizacional());
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(informacionOrganizacional.getFechaRegistroVisita());
        cal.add(Calendar.DAY_OF_YEAR, 1);
        
        try{
            String sql = "UPDATE registro.informacionorganizacional SET idRubro=?, nombreNegocio=?, cantidadEmpleados=?, direccionNegocio=?, cantidadSucursales =?, fechaRegistroVisita=?, idPais=?, archivoInformacionOrganizacional=?  "
                    + "WHERE idInformacionOrganizacional = ?";
            
            this.jdbcTemplate.update(sql, informacionOrganizacional.getIdRubro(), informacionOrganizacional.getNombreNegocio(), informacionOrganizacional.getCantEmpleados(),
                    informacionOrganizacional.getDireccionNegocio(), informacionOrganizacional.getCantSucursales(), cal, informacionOrganizacional.getIdPais(), 
                    informacionOrganizacional.getArchivo(), informacionOrganizacional.getIdInfOrganizacional());
            
            System.out.println("Informacion Organizacional Actualizada Correctamente.");
            
            return true;
            
        }catch(Exception e){
            System.out.println(e);
            
            return false;
        }
    }
    
    // ELIMINAR persona
    public Boolean delete(int id){
        System.out.println("(VISITAS) Eliminando informacionOrganizacional con ID: " + id);
        
        try{
            String sql = "DELETE FROM registro.informacionorganizacional WHERE idInformacionOrganizacional = " + id;
            
            this.jdbcTemplate.update(sql);
            
            System.out.println("Información Organizacional Eliminado Correctamente");
            
            return true;
            
        }catch(Exception e){
            System.out.println(e);
            
            return false;
        }
    }
    
    
    
    // FILTRAR POR GET
    public List<InformacionOrganizacional> searchByID(int idPais){
        System.out.println("Recuperando todos los datos de información organizacional de la BD.");
        
        String sql = "SELECT * FROM registro.informacionorganizacional AS INFORG INNER JOIN registro.rubro AS R ON INFORG.idRubro = R.idRubro INNER JOIN registro.pais P ON INFORG.idPais = P.idpais WHERE INFORG.idPais = " + idPais;
        
        informacionOrganizacionales = this.jdbcTemplate.query(sql, new InformacionOrganizacionalRowMapper2());
        
        System.out.println(informacionOrganizacionales);
        
        return informacionOrganizacionales;
    }
    
}