<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Obtener Información Organizacional</title>
    </head>
    
    <%@include file="nav.jsp" %>
    
    <body>
        </br>
        
        <h1 align="center">Obtener Información Organizacional</h1>
        </br>
        
        <hr size="4px" style="width:50%; margin: auto; color: black;" />
        </br>
        
        <c:url var="getInformacionOrganizacional" value="/getallInformacionOrganizacional" />

        <div class="container mt-4" align="center">
            <c:if test="${empty informacionOrganizacionalGetId}">
                No se encontraron Registros!
            </c:if>

            <c:if test="${!empty informacionOrganizacionalGetId}">
                
                <div class="mb-3">
                    <label path="correo" class="form-label"><strong>Nombre del Negocio </strong></label>
                    <input path="correo" type="email" disabled class="form-control" style="width: 30%; text-align: center;" value="${informacionOrganizacionalGetId.nombreNegocio}"/>
                </div>
                
                <div class="mb-3">
                    <label path="correo" class="form-label"><strong>País del Negocio </strong></label>
                    <input path="correo" type="email" disabled class="form-control" style="width: 30%; text-align: center;" value="${informacionOrganizacionalGetId.nombrePais}"/>
                </div>
                
                 <div class="mb-3">
                    <label path="nacio" class="form-label"><strong>Dirección del Negocio </strong></label>
                    <input path="nacio" type="text" disabled class="form-control" style="width: 30%; text-align: center;" value="${informacionOrganizacionalGetId.direccionNegocio}"/>
                </div>
                
                <div class="mb-3">
                    <label path="nom" class="form-label"><strong>Rubro: </strong></label>
                    <input path="nom" type="text" disabled class="form-control" style="width: 30%; text-align: center;" value="${informacionOrganizacionalGetId.nombreRubro}"/>
                </div>
                    
                <div class="mb-3">
                    <label path="nacio" class="form-label"><strong>Cantidad de Empleados </strong></label>
                    <input path="nacio" type="text" disabled class="form-control" style="width: 30%; text-align: center;" value="${informacionOrganizacionalGetId.cantEmpleados}"/>
                </div>
                
                 <div class="mb-3">
                    <label path="nacio" class="form-label"><strong>Cantidad de Sucursales </strong></label>
                    <input path="nacio" type="text" disabled class="form-control" style="width: 30%; text-align: center;" value="${informacionOrganizacionalGetId.cantSucursales}"/>
                </div>
                
            </c:if>
            
            <p align="center"><a href="${getInformacionOrganizacional}" class="btn btn-danger">Regresar</a></p>
            
        </div>

    </body>
</html>