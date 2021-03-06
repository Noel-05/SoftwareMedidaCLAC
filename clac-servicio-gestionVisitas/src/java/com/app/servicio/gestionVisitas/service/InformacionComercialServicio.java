
package com.app.servicio.gestionVisitas.service;

import com.app.servicio.gestionVisitas.connect.Conexion;
import com.app.servicio.gestionVisitas.domain.InformacionComercial;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service("informacionComercialServicio")
public class InformacionComercialServicio {
    
    // Conexión a la BD.
    Conexion con = new Conexion();
    JdbcTemplate jdbcTemplate = new JdbcTemplate(con.Conectar());
    
    // Variables a utilizar
    private List<InformacionComercial> informacionComercial = new ArrayList<InformacionComercial>();
    List datos;
    
    
    // LISTAR todas las personas
    public List<InformacionComercial> getAll(){
        System.out.println("Recuperando todas la Informacion Comercial de la BD.");
        
        String sql = "SELECT * FROM registro.informacioncomercial AS INFCOM INNER JOIN registro.informacionorganizacional AS INFORG ON INFCOM.idInformacionOrganizacional =  INFORG.idInformacionOrganizacional INNER JOIN registro.pais AS P ON P.idPais = INFORG.idPais ORDER BY INFORG.nombreNegocio ASC";
        
        informacionComercial = this.jdbcTemplate.query(sql, new  InformacionComercialRowMapper2());
        
        System.out.println(informacionComercial);
        
        return informacionComercial;
    }
    
    
    // CONSULTAR persona por ID
    public InformacionComercial getByID(int id){
        System.out.println("Recuperando persona con ID: " + id);
        
        String sql = "SELECT * FROM registro.informacioncomercial AS INFCOM INNER JOIN registro.informacionorganizacional AS INFORG ON INFCOM.idInformacionOrganizacional =  INFORG.idInformacionOrganizacional INNER JOIN registro.pais AS P ON P.idPais = INFORG.idPais WHERE INFCOM.idInformacionComercial = ?";
        
        return this.jdbcTemplate.queryForObject(sql, new  InformacionComercialRowMapper2(), id);
    }
    
    
    // CREAR persona
    public InformacionComercial add(InformacionComercial informacionComercial){
        System.out.println("Insertando nueva Informacion Comercial.");
        
        try{
            String sql = "INSERT INTO registro.informacioncomercial(idInformacionOrganizacional, departamentosDeComercioInterior, paisesDeComercioExterior, empresasSocias, archivoInformacionComercial) VALUES(?, ?, ?, ?, ?)";
            
            this.jdbcTemplate.update(sql, informacionComercial.getIdInformacionOrganizacional(), informacionComercial.getDepartamentosDeComercioInterior(), informacionComercial.getPaisesDeComercioExterior(), informacionComercial.getEmpresasSocias(), informacionComercial.getArchivo());
            
            System.out.println("Informacion Comercial Insertada Correctamente. ");
            
            return informacionComercial;
            
        }catch(Exception e){
            System.out.println(e);
            
            return null;
        }
    }
    
    
    // EDITAR persona
    public Boolean edit(InformacionComercial informacionComercial){
        System.out.println("Editando Informacion Comercial con ID: " + informacionComercial.getIdInformacionComercial());
        
        try{
            String sql = "UPDATE registro.informacioncomercial SET idInformacionOrganizacional=?, departamentosDeComercioInterior=?, paisesDeComercioExterior=?, empresasSocias=?, archivoInformacionComercial=?  "
                    + "WHERE idInformacionComercial = ?";
            
            this.jdbcTemplate.update(sql, informacionComercial.getIdInformacionOrganizacional(), informacionComercial.getDepartamentosDeComercioInterior(), informacionComercial.getPaisesDeComercioExterior(), informacionComercial.getEmpresasSocias(),
                    informacionComercial.getArchivo(), informacionComercial.getIdInformacionComercial());
            
            System.out.println("Persona Actualizada Correctamente.");
            
            return true;
            
        }catch(Exception e){
            System.out.println(e);
            
            return false;
        }
    }
    
    // ELIMINAR persona
    public Boolean delete(int id){
        System.out.println("(VISITAS) Eliminando informacionComercial con ID: " + id);
        
        try{
            String sql = "DELETE FROM registro.informacioncomercial WHERE idInformacionComercial = " + id;
            
            this.jdbcTemplate.update(sql);
            
            System.out.println("Información Comercial Eliminada Correctamente");
            
            return true;
            
        }catch(Exception e){
            System.out.println(e);
            
            return false;
        }
    }
    
    
    // FILTRAR POR GET
    public List<InformacionComercial> searchByID(int idInfOrg){
        System.out.println("Recuperando todos los datos de información comercial de la BD.");
        
        String sql = "SELECT * FROM registro.informacioncomercial AS INFCOM INNER JOIN registro.informacionorganizacional AS INFORG ON INFCOM.idInformacionOrganizacional =  INFORG.idInformacionOrganizacional WHERE INFCOM.idInformacionOrganizacional = " + idInfOrg;
        
        informacionComercial = this.jdbcTemplate.query(sql, new InformacionComercialRowMapper2());
        
        System.out.println(informacionComercial);
        
        return informacionComercial;
    }
}
