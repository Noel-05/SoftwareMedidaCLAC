<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Obtener Bien</title>
    </head>
    
    <%@include file="nav.jsp" %>
    
    <body>
        </br>
        
        <h1 align="center">Obtener Bien</h1>
        </br>
        
        <hr size="4px" style="width:50%; margin: auto; color: black;" />
        </br>
        
        <c:url var="getBien" value="/getallBienes" />

        <div class="container mt-4" align="center">
            <c:if test="${empty bienGetId}">
                No se encontraron Registros!
            </c:if>

            <c:if test="${!empty bienGetId}">
                <div class="mb-3">
                    <label path="nombreBien" class="form-label"><strong>Nombre Bien </strong></label>
                    <input path="nombreBien" type="text" disabled class="form-control" style="width: 30%; text-align: center;" value="${bienGetId.nombreBien}"/>
                </div>
                
                <div class="mb-3">
                    <label path="nombreNegocio" class="form-label"><strong>Nombre Negocio </strong></label>
                    <input path="nombreNegocio" type="text" disabled class="form-control" style="width: 30%; text-align: center;" value="${bienGetId.nombreNegocio}"/>
                </div>
                    
                <div class="mb-3">
                    <label path="cantidadBien" class="form-label"><strong>Cantidad </strong></label>
                    <input path="cantidadBien" type="text" disabled class="form-control" style="width: 30%; text-align: center;" value="${bienGetId.cantidadBien}"/>
                </div>
                
            </c:if>
            
            <p align="center"><a href="${getBien}" class="btn btn-danger">Regresar</a></p>
        </div>
        </br>

    </body>
</html>
