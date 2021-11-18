
package com.app.cliente.domain.monitoreos;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="monitors")
public class MonitoreoList {
    private List<Monitoreo> data;

    public List<Monitoreo> getData() {
        return data;
    }
    
    public void setData(List<Monitoreo> data) {
        this.data = data;
    }
}
