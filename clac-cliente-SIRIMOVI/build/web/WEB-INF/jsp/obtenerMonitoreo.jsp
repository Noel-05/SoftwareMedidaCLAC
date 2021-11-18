<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Obtener Monitoreo</title>
    </head>
    
    <%@include file="nav.jsp" %>
    
    <body>
        </br>
        
        <h1 align="center">Obtener Monitoreo</h1>
        </br>
        
        <hr size="4px" style="width:50%; margin: auto; color: black;" />
        </br>
        
        <c:url var="getMonitoreos" value="/getallMonitoreos" />

        <div class="container mt-4" align="center">
            <c:if test="${empty monitoreoGetId}">
                No se encontraron Registros!
            </c:if>

            <c:if test="${!empty monitoreoGetId}">
                
                <div class="mb-3">
                    <label path="nombreNegocio" class="form-label"><strong>Nombre del Negocio: </strong></label>
                    <input path="nombreNegocio" type="text" disabled class="form-control" style="width: 30%; text-align: center;" value="${monitoreoGetId.nombreNegocio}"/>
                </div>
                
                <div class="mb-3">
                    <label path="fechaRegistroMonitoreo" class="form-label"><strong>Fecha de Monitoreo: </strong></label>
                    <input path="fechaRegistroMonitoreo" type="text" disabled class="form-control" style="width: 30%; text-align: center;" pattern="dd/MM/yyyy" value="${monitoreoGetId.fechaRegistroMonitoreo}"/>
                </div>
                
                <div class="mb-3">
                    <label path="cantidadProduccion" class="form-label"><strong>Cantidad de Producción: </strong></label>
                    <input path="cantidadProduccion" type="text" disabled class="form-control" style="width: 30%; text-align: center;" value="${monitoreoGetId.cantidadProduccion}"/>
                </div>
                    
                <div class="mb-3">
                    <label path="cantidadPerdidas" class="form-label"><strong>Cantidad de Perdidas: </strong></label>
                    <input path="cantidadPerdidas" type="email" disabled class="form-control" style="width: 30%; text-align: center;" value="${monitoreoGetId.cantidadPerdidas}"/>
                </div>
                    
                <div class="mb-3">
                    <label path="totalVentas" class="form-label"><strong>Total de Ventas Actuales: </strong></label>
                    <input path="totalVentas" type="text" disabled class="form-control" style="width: 30%; text-align: center;" value="${monitoreoGetId.totalVentas}"/>
                </div>
                
                <div class="mb-3">
                    <label path="ganancia" class="form-label"><strong>Total de Ganancias Actuales: </strong></label>
                    <input path="ganancia" type="text" disabled class="form-control" style="width: 30%; text-align: center;" value="${monitoreoGetId.ganancia}"/>
                </div>
                
                <p align="center"><a href="${getMonitoreos}" class="btn btn-danger">Regresar</a></p>
            </c:if>
        </div>
        </br></br>

    </body>
</html>