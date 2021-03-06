
package com.app.servicio.gestionMonitoreos.service;

import com.app.servicio.gestionMonitoreos.connect.Conexion;
import com.app.servicio.gestionMonitoreos.domain.Monitoreo;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service("monitoreoServicio")
public class MonitoreoServicio {
    // Conexión a la BD.
    Conexion con = new Conexion();
    JdbcTemplate jdbcTemplate = new JdbcTemplate(con.Conectar());
    
    // Variables a utilizar
    private List<Monitoreo> monitoreos = new ArrayList<Monitoreo>();
    List datos;
    
    
    // LISTAR todas los monitoreos
    public List<Monitoreo> getAll(){
        System.out.println("Recuperando todas los monitoreos de la BD.");
        
        String sql = "SELECT * FROM registro.monitoreos AS M INNER JOIN registro.informacionorganizacional AS INFORG ON M.idInformacionOrganizacional =  INFORG.idInformacionOrganizacional INNER JOIN registro.pais P ON P.idPais = INFORG.idPais ORDER BY INFORG.nombreNegocio ASC";
        
        monitoreos = this.jdbcTemplate.query(sql, new MonitoreoRowMapper2());
        
        System.out.println(monitoreos);
        
        return monitoreos;
    }
    
    
    // CONSULTAR monitoreo por ID
    public Monitoreo getByID(int id){
        System.out.println("Recuperando monitoreo con ID: " + id);
        
        String sql = "SELECT * FROM registro.monitoreos AS M INNER JOIN registro.informacionorganizacional AS INFORG ON M.idInformacionOrganizacional =  INFORG.idInformacionOrganizacional INNER JOIN registro.pais P ON P.idPais = INFORG.idPais WHERE M.idMonitoreo = ?";
        
        return this.jdbcTemplate.queryForObject(sql, new MonitoreoRowMapper2(), id);
    }
    
    
    // CREAR monitoreo
    public Monitoreo add(Monitoreo monitoreo){
        System.out.println("Insertando nuevo monitoreos.");
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(monitoreo.getFechaRegistroMonitoreo());
        cal.add(Calendar.DAY_OF_YEAR, 1);
        
        try{
            String sql = "INSERT INTO registro.monitoreos(idInformacionOrganizacional, cantidadProduccion, cantidadPerdidas, totalVentas, ganancia, fechaRegistroMonitoreo, archivoMonitoreo) VALUES(?, ?, ?, ?, ?, ?, ?)";
            
            this.jdbcTemplate.update(sql, monitoreo.getIdInfo(), monitoreo.getCantidadProduccion(), monitoreo.getCantidadPerdidas(), monitoreo.getTotalVentas(), monitoreo.getGanancia(),cal,monitoreo.getArchivo());
            
            System.out.println("Monitoreo Insertada Correctamente. ");
            
            return monitoreo;
            
        }catch(Exception e){
            System.out.println(e);
            
            return null;
        }
    }
    
    
    // EDITAR monitoreo
    public Boolean edit(Monitoreo monitoreo){
        System.out.println("Editando monitoreo con ID: " + monitoreo.getIdMonitoreo());
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(monitoreo.getFechaRegistroMonitoreo());
        cal.add(Calendar.DAY_OF_YEAR, 1);
        
        try{
            String sql = "UPDATE registro.monitoreos SET idInformacionOrganizacional=?, cantidadProduccion=?, cantidadPerdidas=?, totalVentas=?, ganancia=?, fechaRegistroMonitoreo=?, archivoMonitoreo=? WHERE idMonitoreo = ?";
            
            this.jdbcTemplate.update(sql, monitoreo.getIdInfo(), monitoreo.getCantidadProduccion(), monitoreo.getCantidadPerdidas(),monitoreo.getTotalVentas(),monitoreo.getGanancia(), cal, monitoreo.getArchivo(), monitoreo.getIdMonitoreo());
            
            System.out.println("Monitoreo Actualizado Correctamente.");
            
            return true;
            
        }catch(Exception e){
            System.out.println(e);
            
            return false;
        }
    }
    
    
    // ELIMINAR monitoreo
    public Boolean delete(int id){
        System.out.println("Eliminando monitoreo con ID: " + id);
        
        try{
            String sql = "DELETE FROM registro.monitoreos WHERE idMonitoreo = " + id;
            
            this.jdbcTemplate.update(sql);
            
            System.out.println("Monitoreo Eliminado Correctamente");
            
            return true;
            
        }catch(Exception e){
            System.out.println(e);
            
            return false;
        }
    }
    
    
    // FILTRAR POR GET
    public List<Monitoreo> searchByID(int idPais){
        System.out.println("Recuperando todos los datos de monitoreos de la BD.");
        
        String sql = "SELECT * FROM registro.monitoreos AS M INNER JOIN registro.informacionorganizacional AS INFORG ON M.idInformacionOrganizacional =  INFORG.idInformacionOrganizacional INNER JOIN registro.pais P ON P.idPais = INFORG.idPais WHERE INFORG.idPais = " + idPais;
        
        monitoreos = this.jdbcTemplate.query(sql, new MonitoreoRowMapper2());
        
        System.out.println(monitoreos);
        
        return monitoreos;
    }
}
