
package com.app.servicio.gestionVisitas.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Bienes")
public class Bienes {
    int idBien;
    int idInformacionFinanciera;
    String nombreNegocio;
    String nombreBien;
    int cantidadBien;
    String archivo;

    public int getIdBien() {
        return idBien;
    }

    public void setIdBien(int idBien) {
        this.idBien = idBien;
    }

    public int getIdInformacionFinanciera() {
        return idInformacionFinanciera;
    }

    public void setIdInformacionFinanciera(int idInformacionFinanciera) {
        this.idInformacionFinanciera = idInformacionFinanciera;
    }

    public String getNombreBien() {
        return nombreBien;
    }

    public void setNombreBien(String nombreBien) {
        this.nombreBien = nombreBien;
    }

    public int getCantidadBien() {
        return cantidadBien;
    }

    public void setCantidadBien(int cantidadBien) {
        this.cantidadBien = cantidadBien;
    }

    public String getNombreNegocio() {
        return nombreNegocio;
    }

    public void setNombreNegocio(String nombreNegocio) {
        this.nombreNegocio = nombreNegocio;
    }
    
    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    @Override
    public String toString() {
        return "Bienes{" + "idBien=" + idBien + ", idInformacionFinanciera=" + idInformacionFinanciera + ", nombreBien=" + nombreBien + ", cantidadBien=" + cantidadBien + "\nArchivo: "+archivo+'}';
    }
    
    
}