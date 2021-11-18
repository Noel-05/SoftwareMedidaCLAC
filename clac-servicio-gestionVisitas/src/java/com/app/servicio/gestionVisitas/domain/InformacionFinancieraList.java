
package com.app.servicio.gestionVisitas.domain;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="inFinancieras")
public class InformacionFinancieraList {
    
    private List<InformacionFinanciera> Data;

    public List<InformacionFinanciera> getData() {
        return Data;
    }

    public void setData(List<InformacionFinanciera> Data) {
        this.Data = Data;
    }
}
