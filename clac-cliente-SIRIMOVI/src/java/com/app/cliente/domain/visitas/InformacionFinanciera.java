
package com.app.cliente.domain.visitas;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="inFinanciera")
public class InformacionFinanciera {
    int idinformacionFinanciera;
    int idinformacionOrganizacional;
    String nombreNegocio;
    String paisNegocio;
    String rangoSalariosEmpleados;
    float saldoDeudasActual;
    float saldoLiquidez;
    String archivo;

    public int getIdinformacionFinanciera() {
        return idinformacionFinanciera;
    }

    public void setIdinformacionFinanciera(int idinformacionFinanciera) {
        this.idinformacionFinanciera = idinformacionFinanciera;
    }

    public int getIdinformacionOrganizacional() {
        return idinformacionOrganizacional;
    }

    public void setIdinformacionOrganizacional(int idinformacionOrganizacional) {
        this.idinformacionOrganizacional = idinformacionOrganizacional;
    }

    public String getRangoSalariosEmpleados() {
        return rangoSalariosEmpleados;
    }

    public void setRangoSalariosEmpleados(String rangoSalariosEmpleados) {
        this.rangoSalariosEmpleados = rangoSalariosEmpleados;
    }

    public float getSaldoDeudasActual() {
        return saldoDeudasActual;
    }

    public void setSaldoDeudasActual(float saldoDeudasActual) {
        this.saldoDeudasActual = saldoDeudasActual;
    }

    public float getSaldoLiquidez() {
        return saldoLiquidez;
    }

    public void setSaldoLiquidez(float saldoLiquidez) {
        this.saldoLiquidez = saldoLiquidez;
    }

    public String getNombreNegocio() {
        return nombreNegocio;
    }

    public void setNombreNegocio(String nombreNegocio) {
        this.nombreNegocio = nombreNegocio;
    }
    
    public String getPaisNegocio() {
        return paisNegocio;
    }

    public void setPaisNegocio(String paisNegocio) {
        this.paisNegocio = paisNegocio;
    }
    
    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
    
    @Override
    public String toString(){
        return "Persona{" + "id informacion financiera=" + idinformacionFinanciera + ", id informacion organizacional=" + idinformacionOrganizacional + 
                ", rango de salarios de los empleados=" + rangoSalariosEmpleados + ", saldo de liquidez=" + saldoLiquidez + ", saldo de deuda actual=" + saldoDeudasActual + "\nArchivo: "+archivo+'}';
    }
 
}
