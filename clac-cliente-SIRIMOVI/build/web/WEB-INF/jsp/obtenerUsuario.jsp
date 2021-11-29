<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Obtener Usuario</title>
    </head>
    
    <%@include file="nav.jsp" %>
    
    <body>
        </br>
        
        <h1 align="center">Obtener Usuario</h1>
        </br>
        
        <hr size="4px" style="width:50%; margin: auto; color: black;" />
        </br>
        
        <c:url var="getUser" value="/getallUsuarios" />

        <div class="container mt-4" align="center">
            <c:if test="${empty usuarioGetId}">
                No se encontraron Registros!
            </c:if>

            <c:if test="${!empty usuarioGetId}">
                
                <div class="mb-3">
                    <label path="nombre" class="form-label"><strong>Nombres: </strong></label>
                    <input path="nombre" type="text" disabled class="form-control" style="width: 30%; text-align: center;" value="${usuarioGetId.nombre}"/>
                </div>
                
                <div class="mb-3">
                    <label path="apellido" class="form-label"><strong>Apellidos: </strong></label>
                    <input path="apellido" type="text" disabled class="form-control" style="width: 30%; text-align: center;" value="${usuarioGetId.apellido}"/>
                </div>
                
                <div class="mb-3">
                    <label path="nombreRol" class="form-label"><strong>Rol: </strong></label>
                    <input path="nombreRol" type="text" disabled class="form-control" style="width: 30%; text-align: center;" value="${usuarioGetId.nombreRol}"/>
                </div>
                
                <div class="mb-3">
                    <label path="correo" class="form-label"><strong>Correo: </strong></label>
                    <input path="correo" type="email" disabled class="form-control" style="width: 30%; text-align: center;" value="${usuarioGetId.correo}"/>
                </div>
                
                <p align="center"><a href="${getUser}" class="btn btn-danger">Regresar</a></p>
            </c:if>
        </div>
        </br>

    </body>
</html>
