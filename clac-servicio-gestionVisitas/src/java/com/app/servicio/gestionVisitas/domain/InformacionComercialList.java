
package com.app.servicio.gestionVisitas.domain;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="commercialinformations")
public class InformacionComercialList {
    
    private List<InformacionComercial> data;

    public List<InformacionComercial> getData() {
        return data;
    }

    public void setData(List<InformacionComercial> data) {
        this.data = data;
    }
}
