
package com.app.servicio.gestionVisitas.service;

import com.app.servicio.gestionVisitas.connect.Conexion;
import com.app.servicio.gestionVisitas.domain.Producto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service("productoServicio")
public class ProductoServicio {
    
    // Conexión a la BD.
    Conexion con = new Conexion();
    JdbcTemplate jdbcTemplate = new JdbcTemplate(con.Conectar());
    
    // Variables a utilizar
    private List<Producto> productos = new ArrayList<Producto>();
    List datos;
       
    
    // LISTAR todas los productos
    public List<Producto> getAll(){
        System.out.println("Recuperando todos los productos de la BD.");
        
        String sql = "SELECT * FROM registro.productos AS P INNER JOIN registro.informacioncomercial AS INFCOM ON P.idInformacionComercial =  INFCOM.idInformacionComercial INNER JOIN registro.informacionorganizacional AS INFORG ON INFCOM.idInformacionOrganizacional =  INFORG.idInformacionOrganizacional ORDER BY INFORG.nombreNegocio ASC";
        
        productos = this.jdbcTemplate.query(sql, new ProductoRowMapper2());
        
        System.out.println(productos);
        
        return productos;
    }
    
    
    // CREAR Producto
    public Producto add(Producto producto){
        System.out.println("Insertando nuevo Producto");
        
        try{
            String sql = "INSERT INTO registro.productos(idInformacionComercial, nombreProducto, precioVenta, precioFabricacion) VALUES(?, ?, ?, ?)";
            
            this.jdbcTemplate.update(sql, producto.getIdInformacionComercial(), producto.getNombre(), producto.getPrecioVenta(), producto.getPrecioFabricacion());
            
            System.out.println("Producto Insertado Correctamente. ");
            
            return producto;
            
        }catch(Exception e){
            System.out.println(e);
            
            return null;
        }
    }
    
    
    //CONSULTAR produto
    public Producto getByID(int id){
        System.out.println("Recuperando producto con ID: " + id);
        
        String sql = "SELECT * FROM registro.productos AS P INNER JOIN registro.informacioncomercial AS INFCOM ON P.idInformacionComercial =  INFCOM.idInformacionComercial INNER JOIN registro.informacionorganizacional AS INFORG ON INFCOM.idInformacionOrganizacional =  INFORG.idInformacionOrganizacional WHERE P.idProducto = ?";
        
        return this.jdbcTemplate.queryForObject(sql, new ProductoRowMapper2(), id);
    }
    
    
    // EDITAR Producto
    public Boolean edit(Producto producto){
        System.out.println("Editando producto con ID: " + producto.getId());
        
        try{
            String sql = "UPDATE registro.productos SET idInformacionComercial=?, nombreProducto=?, precioVenta=?, precioFabricacion=? WHERE idProducto = ?";
            
            this.jdbcTemplate.update(sql, producto.getIdInformacionComercial(), producto.getNombre(), producto.getPrecioVenta(), producto.getPrecioFabricacion(), producto.getId());
            
            System.out.println("Producto Actualizado Correctamente.");
            
            return true;
            
        }catch(Exception e){
            System.out.println(e);
            
            return false;
        }
    }
    
    
    // ELIMINAR persona
    public Boolean delete(int id){
        System.out.println("(VISITAS) Eliminando producto con ID: " + id);
        
        try{
            String sql = "DELETE FROM registro.productos WHERE idProducto = " + id;
            
            this.jdbcTemplate.update(sql);
            
            System.out.println("Producto Eliminado Correctamente");
            
            return true;
            
        }catch(Exception e){
            System.out.println(e);
            
            return false;
        }
    }
    
    
    // FILTRAR POR GET
    public List<Producto> searchByID(int idInfCom){
        System.out.println("Recuperando todos los datos de los Bienes de la BD.");
        
        String sql = "SELECT * FROM registro.productos AS P INNER JOIN registro.informacioncomercial AS INFCOM ON P.idInformacionComercial =  INFCOM.idInformacionComercial INNER JOIN registro.informacionorganizacional AS INFORG ON INFCOM.idInformacionOrganizacional =  INFORG.idInformacionOrganizacional WHERE INFCOM.idInformacionComercial = " + idInfCom;
        
        productos = this.jdbcTemplate.query(sql, new ProductoRowMapper2());
        
        System.out.println(productos);
        
        return productos;
    }
      
}
