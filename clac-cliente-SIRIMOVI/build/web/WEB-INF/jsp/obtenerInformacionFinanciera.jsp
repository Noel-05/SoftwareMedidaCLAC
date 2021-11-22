<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Obtener Informacion Financiera</title>
    </head>
    
    <%@include file="nav.jsp" %>
    
    <body>
        </br>
        
        <h1 align="center">Obtener Informacion Financiera</h1>
        </br>
        
        <hr size="4px" style="width:50%; margin: auto; color: black;" />
        </br>
        
        <c:url var="getInformacionFinanciera" value="/getAllInformacionFinanciera" />

        <div class="container mt-4" align="center">
            <c:if test="${empty informacionFinancieraGetId}">
                No se encontraron Registros!
            </c:if>

            <c:if test="${!empty informacionFinancieraGetId}">
                
                <div class="mb-3">
                    <label path="idInformacionOrganizacional" class="form-label"><strong>Nombre del Negocio: </strong></label>
                    <input path="idInformacionOrganizacional" type="text" disabled class="form-control" style="width: 30%; text-align: center;" value="${informacionFinancieraGetId.nombreNegocio}"/>
                </div>
                
                <div class="mb-3">
                    <label path="paisNegocio" class="form-label"><strong>País del Negocio: </strong></label>
                    <input path="paisNegocio" type="text" disabled class="form-control" style="width: 30%; text-align: center;" value="${informacionFinancieraGetId.paisNegocio}"/>
                </div>
                    
                <div class="mb-3">
                    <label path="rangoSalarioEmpleado" class="form-label"><strong>Rango Salario Empleados </strong></label>
                    <input path="rangoSalarioEmpleado" type="text" disabled class="form-control" style="width: 30%; text-align: center;" value="${informacionFinancieraGetId.rangoSalariosEmpleados}"/>
                </div>
                    
                <div class="mb-3">
                    <label path="saldoDeudasActual" class="form-label"><strong>Saldo Deudas Actual: </strong></label>
                    <input path="saldoDeudasActual" type="text" disabled class="form-control" style="width: 30%; text-align: center;" value="$ ${informacionFinancieraGetId.saldoDeudasActual}"/>
                </div>
                
                <div class="mb-3">
                    <label path="saldoLiquidez" class="form-label"><strong>Saldo Liquidez Actual</strong></label>
                    <input path="saldoLiquidez" type="text" disabled class="form-control" style="width: 30%; text-align: center;" value="$ ${informacionFinancieraGetId.saldoLiquidez}"/>
                </div>
                
                <p align="center"><a href="${getInformacionFinanciera}" class="btn btn-danger">Regresar</a></p>
            </c:if>
        </div>

    </body>
</html>

