
package com.app.cliente.domain.visitas;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="bienes")
public class BienesList {
    
    private List<Bienes> data;

    public List<Bienes> getData() {
        return data;
    }

    public void setData(List<Bienes> data) {
        this.data = data;
    }
    
}