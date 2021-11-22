
package com.app.servicio.gestionVisitas.domain;

import com.app.servicio.gestionVisitas.domain.Pais;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="paises")
public class PaisList {
       
    private List<Pais> data;

    public List<Pais> getData() {
        return data;
    }

    public void setData(List<Pais> data) {
        this.data = data;
    }
    
}
