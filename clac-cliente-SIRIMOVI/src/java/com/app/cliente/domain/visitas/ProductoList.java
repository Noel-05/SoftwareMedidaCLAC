
package com.app.cliente.domain.visitas;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="products")
public class ProductoList {
    
    private List<Producto> data;

    public List<Producto> getData() {
        return data;
    }

    public void setData(List<Producto> data) {
        this.data = data;
    }
    
}
