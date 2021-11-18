
package com.app.servicio.gestionVisitas.domain;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@XmlRootElement(name="informationorganizational")
public class InformacionOrganizacional {
    int idInfOrganizacional;
    int idRubro;
    String nombreRubro;
    String nombreNegocio;
    int cantEmpleados;
    String direccionNegocio;
    int cantSucursales;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    Date fechaRegistroVisita;

    public int getIdInfOrganizacional() {
        return idInfOrganizacional;
    }

    public void setIdInfOrganizacional(int idInfOrganizacional) {
        this.idInfOrganizacional = idInfOrganizacional;
    }

    public int getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(int idRubro) {
        this.idRubro = idRubro;
    }

    public String getNombreNegocio() {
        return nombreNegocio;
    }

    public void setNombreNegocio(String nombreNegocio) {
        this.nombreNegocio = nombreNegocio;
    }

    public int getCantEmpleados() {
        return cantEmpleados;
    }

    public void setCantEmpleados(int cantEmpleados) {
        this.cantEmpleados = cantEmpleados;
    }

    public String getDireccionNegocio() {
        return direccionNegocio;
    }

    public void setDireccionNegocio(String direccionNegocio) {
        this.direccionNegocio = direccionNegocio;
    }

    public int getCantSucursales() {
        return cantSucursales;
    }

    public void setCantSucursales(int cantSucursales) {
        this.cantSucursales = cantSucursales;
    }

    public String getNombreRubro() {
        return nombreRubro;
    }

    public void setNombreRubro(String nombreRubro) {
        this.nombreRubro = nombreRubro;
    }

    public Date getFechaRegistroVisita() {
        return fechaRegistroVisita;
    }

    public void setFechaRegistroVisita(Date fechaRegistroVisita) {
        this.fechaRegistroVisita = fechaRegistroVisita;
    }
    
    @Override
    public String toString() {
        return "InformacionOrganizacional{" + "idInfOrganizacional=" + idInfOrganizacional + ", idRubro=" + idRubro + ", nombreNegocio=" + nombreNegocio + ", cantEmpleados=" + cantEmpleados + ", direccionNegocio=" + direccionNegocio + ", cantSucursales=" + cantSucursales + '}';
    }
    
}
