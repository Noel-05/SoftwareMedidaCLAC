<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Informacion Financiera</title>
    </head>
    
    <%@include file="nav.jsp" %>
    
    <body>
        </br>
        
        <h1 align="center">Crear Nueva Informacion Financiera</h1>
        </br>
        
        <hr size="4px" style="width:50%; margin: auto; color: black;" />
        </br>

        <c:url var="saveUrl" value="/informacionFadd" />
        <c:url var="getInfFin" value="/getAllInformacionFinanciera" />
        
        <div class="container mt-4" align="center">
            <form:form modelAttribute="informaAttribute" method="POST" action="${saveUrl}" enctype="multipart/form-data">
                <div class="mb-3">
                    <form:label path="idinformacionOrganizacional" class="form-label">Negocio </form:label>
                    <form:select path="idinformacionOrganizacional" class="form-control" style="width: 30%;" required="true">
                        <option value="">Seleccione el negocio...</option>
                        <c:forEach var="infOrg" items="${informacionOrganizacionalList}">
                            <form:option value="${infOrg.idInfOrganizacional}">${infOrg.nombreNegocio}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
                    
                <div class="mb-3">
                    <form:label path="rangoSalariosEmpleados" class="form-label">Rango de Salarios de Empleados </form:label>
                    <form:input path="rangoSalariosEmpleados" required="true" type="text" class="form-control" aria-describedby="emailHelp" style="width: 30%;"/>
                    <div id="emailHelp" class="form-text">Escribirlo de la siguiente manera: Ejemplo: $300.00 - $1,000.00</div>
                </div>
                    
                <div class="mb-3">
                    <form:label path="saldoDeudasActual" class="form-label">Saldo de Deudas Actual </form:label>
                    <form:input path="saldoDeudasActual" required="true" type="number" step= "0.01"  class="form-control" aria-describedby="emailHelp" style="width: 30%;"/>
                </div>
                
                <div class="mb-3">
                    <form:label path="saldoLiquidez" class="form-label">Saldo de Liquidez Actual </form:label>
                    <form:input path="saldoLiquidez" required="true" type="number" step= "0.01"  class="form-control" aria-describedby="emailHelp" style="width: 30%;"/>
                </div>
                
                <div class="mb-3" style="display: none;">
                    <form:label path="archivo" class="form-label">archivo: </form:label>
                    <form:input path="archivo" type="text" required="false" class="form-control" aria-describedby="emailHelp" style="width: 30%;" value="no archivo"/>
                </div>
                
                <div class="mb-3">
                    <label class="form-label">Archivo: </label>
                    <input id="doc" name="doc" required="false" type="file" class="form-control" style="width: 30%;"/>
                </div>
                
                <button type="submit" class="btn btn-primary">Guardar</button>
                <a href="${getInfFin}" class="btn btn-danger">Regresar</a>
            </form:form>
        </div>
        </br>

    </body>
</html>