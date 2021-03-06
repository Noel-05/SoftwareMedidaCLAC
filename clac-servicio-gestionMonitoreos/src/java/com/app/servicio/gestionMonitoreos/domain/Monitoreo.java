
package com.app.servicio.gestionMonitoreos.domain;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.format.annotation.DateTimeFormat;

@XmlRootElement(name="monitor")
public class Monitoreo {
    int idMonitoreo;
    int idInfo;
    String nombreNegocio;
    String nombrePais;
    int cantidadProduccion;
    int cantidadPerdidas;
    int totalVentas;
    int ganancia;
    String archivo;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    Date fechaRegistroMonitoreo;

    public int getCantidadPerdidas() {
        return cantidadPerdidas;
    }

    public void setCantidadPerdidas(int cantidadPerdidas) {
        this.cantidadPerdidas = cantidadPerdidas;
    }

    public int getIdMonitoreo() {
        return idMonitoreo;
    }

    public void setIdMonitoreo(int idMonitoreo) {
        this.idMonitoreo = idMonitoreo;
    }

    public int getIdInfo() {
        return idInfo;
    }

    public void setIdInfo(int idInfo) {
        this.idInfo = idInfo;
    }

    public int getCantidadProduccion() {
        return cantidadProduccion;
    }

    public void setCantidadProduccion(int cantidadProduccion) {
        this.cantidadProduccion = cantidadProduccion;
    }

    public int getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(int totalVentas) {
        this.totalVentas = totalVentas;
    }

    public int getGanancia() {
        return ganancia;
    }

    public void setGanancia(int ganancia) {
        this.ganancia = ganancia;
    }

    public String getNombreNegocio() {
        return nombreNegocio;
    }

    public void setNombreNegocio(String nombreNegocio) {
        this.nombreNegocio = nombreNegocio;
    }

    public Date getFechaRegistroMonitoreo() {
        return fechaRegistroMonitoreo;
    }

    public void setFechaRegistroMonitoreo(Date fechaRegistroMonitoreo) {
        this.fechaRegistroMonitoreo = fechaRegistroMonitoreo;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }
    
    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
    
    @Override
    public String toString() {
        return "Monitoreo:" + "\nid Monitoreo: " + idMonitoreo + "\nId Informacion: " + idInfo + "\nCantidad de Produccion: " + cantidadProduccion + "\nCantidad de perdidas:" + cantidadPerdidas + "\nTotal de ventas: "+totalVentas+"\nGanancias: "+ganancia+"\nArchivo: "+archivo+'}';
    }
}
