
package com.app.servicio.gestionVisitas.service;

import com.app.servicio.gestionVisitas.connect.Conexion;
import com.app.servicio.gestionVisitas.domain.InformacionFinanciera;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service("inFinancieraServicio")
public class InformacionFinancieraServicio {
    
    Conexion con = new Conexion();
    JdbcTemplate jdbcTemplate = new JdbcTemplate(con.Conectar());
    
    private List<InformacionFinanciera> infoFinanciera = new ArrayList<InformacionFinanciera>();
    List datos;
    
    //LISTAR
    public List<InformacionFinanciera> getAll(){
        System.out.println("Recuperando toda la Informacion Financiera de la BD.");
        
        String sql = "SELECT * FROM registro.informacionfinanciera AS INFFIN INNER JOIN registro.informacionorganizacional AS INFORG ON INFFIN.idInformacionOrganizacional =  INFORG.idInformacionOrganizacional INNER JOIN registro.pais AS P ON P.idPais = INFORG.idPais ORDER BY INFORG.nombreNegocio ASC";
        infoFinanciera = this.jdbcTemplate.query(sql, new InformacionFinancieraRowMapper2());
        System.out.println(infoFinanciera);
        
        return infoFinanciera;
    }
    
    public InformacionFinanciera getByID(int id){
        System.out.println("Recuperando Informacion Financiera con ID: " + id);
        
        String sql = "SELECT * FROM registro.informacionfinanciera AS INFFIN INNER JOIN registro.informacionorganizacional AS INFORG ON INFFIN.idInformacionOrganizacional =  INFORG.idInformacionOrganizacional INNER JOIN registro.pais AS P ON P.idPais = INFORG.idPais WHERE INFFIN.idInformacionFinanciera = ?";
        
        return this.jdbcTemplate.queryForObject(sql, new InformacionFinancieraRowMapper2(), id);
    }
    
    //AGREGAR CREAR
    public InformacionFinanciera add(InformacionFinanciera informacion){
        System.out.println("Insertando nueva Informacion Financiera.");
        
        try{
            String sql = "INSERT INTO registro.informacionfinanciera(idInformacionOrganizacional, rangoSalarioEmpleados, saldoDeudasActual, saldoLiquidez, archivoInformacionFinanciera) VALUES(?, ?, ?, ?, ?)";
            
            this.jdbcTemplate.update(sql, informacion.getIdinformacionOrganizacional(), informacion.getRangoSalariosEmpleados(), informacion.getSaldoDeudasActual(), informacion.getSaldoLiquidez(), informacion.getArchivo());
            
            System.out.println("Informacion Financiera Insertada Correctamente. ");
            
            return informacion;
            
        }catch(Exception e){
            System.out.println(e);
            
            return null;
        }
    }
    
    // EDITAR 
    public Boolean edit(InformacionFinanciera infof){
        System.out.println("Editando Informacion Financiera con ID: " + infof.getIdinformacionFinanciera());
        
        try{
            String sql = "UPDATE registro.informacionfinanciera SET idInformacionOrganizacional=?, rangoSalarioEmpleados=?, saldoDeudasActual=?, saldoLiquidez=?, archivoInformacionFinanciera=?"
                    + " WHERE idInformacionFinanciera = ?";
            
            this.jdbcTemplate.update(sql, infof.getIdinformacionOrganizacional(), infof.getRangoSalariosEmpleados(), infof.getSaldoDeudasActual(),
                     infof.getSaldoLiquidez(), infof.getArchivo(), infof.getIdinformacionFinanciera());
            
            System.out.println("Informacion Financiera Actualizada Correctamente.");
            
            return true;
            
        }catch(Exception e){
            System.out.println(e);
            
            return false;
        }
    }
    
    
    // ELIMINAR persona
    public Boolean delete(int id){
        System.out.println("(VISITAS) Eliminando informacionFinanciera con ID: " + id);
        
        try{
            String sql = "DELETE FROM registro.informacionfinanciera WHERE idInformacionFinanciera = " + id;
            
            this.jdbcTemplate.update(sql);
            
            System.out.println("Información Financiera Eliminada Correctamente");
            
            return true;
            
        }catch(Exception e){
            System.out.println(e);
            
            return false;
        }
    }
    
    
    // FILTRAR POR GET
    public List<InformacionFinanciera> searchByID(int idInfOrg){
        System.out.println("Recuperando todos los datos de información financiera de la BD.");
        
        String sql = "SELECT * FROM registro.informacionfinanciera AS INFFIN INNER JOIN registro.informacionorganizacional AS INFORG ON INFFIN.idInformacionOrganizacional =  INFORG.idInformacionOrganizacional INNER JOIN registro.pais AS P ON P.idPais = INFORG.idPais WHERE  INFFIN.idInformacionOrganizacional = " + idInfOrg;
        
        infoFinanciera = this.jdbcTemplate.query(sql, new InformacionFinancieraRowMapper2());
        
        System.out.println(infoFinanciera);
        
        return infoFinanciera;
    }
}

