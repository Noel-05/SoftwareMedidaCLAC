
package com.app.servicio.gestionVisitas.domain;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="rubros")
public class RubroList {
    private List<Rubro> data;

    public List<Rubro> getData() {
        return data;
    }

    public void setData(List<Rubro> data) {
        this.data = data;
    }
}
