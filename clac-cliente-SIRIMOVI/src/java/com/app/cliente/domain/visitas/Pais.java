
package com.app.cliente.domain.visitas;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="pais")
public class Pais {
    int idPais;
    String nombrePais;

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }
    
}
