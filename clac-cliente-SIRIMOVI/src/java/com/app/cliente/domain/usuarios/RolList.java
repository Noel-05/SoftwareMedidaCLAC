
package com.app.cliente.domain.usuarios;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="rols")
public class RolList {
    private List<Rol> data;

    public List<Rol> getData() {
        return data;
    }

    public void setData(List<Rol> data) {
        this.data = data;
    }
}
