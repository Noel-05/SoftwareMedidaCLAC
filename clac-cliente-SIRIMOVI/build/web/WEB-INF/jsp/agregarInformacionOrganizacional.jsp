<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Información Organizacional</title>
    </head>
    
    <%@include file="nav.jsp" %>
    
    <body>
        </br>
        
        <h1 align="center">Crear Nueva Informaci&oacute;n Organizacional</h1>
        </br>
        
        <hr size="4px" style="width:50%; margin: auto; color: black;" />
        </br>

        <c:url var="saveUrl" value="/addInformacionOrganizacional" />
        <c:url var="getPerson" value="/getallInformacionOrganizacional" />
        
        <div class="container mt-4" align="center">
            <form:form modelAttribute="informacionOrganizacionalAttribute" method="POST" action="${saveUrl}" enctype="multipart/form-data">
                    
                <div class="mb-3">
                    <form:label path="nombreNegocio" class="form-label">Nombre del negocio: </form:label>
                    <form:input path="nombreNegocio" required="true" type="text" class="form-control" aria-describedby="emailHelp" style="width: 30%;" />
                    <div id="emailHelp" class="form-text">NO utilizar tildes ni el caracter Ñ.</div>
                </div>
                    
                <div class="mb-3">
                    <form:label path="idPais" class="form-label">Pais </form:label>
                    <form:select path="idPais" class="form-control" style="width: 30%;" required="true">
                        <option value="">Seleccione el país del negocio...</option>
                        <c:forEach var="nomPa" items="${paisList}">
                            <form:option value="${nomPa.idPais}">${nomPa.nombrePais}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
                
                <div class="mb-3">
                    <form:label path="direccionNegocio" class="form-label">Direcci&oacute;n del negocio: </form:label>
                    <form:input path="direccionNegocio" required="true" type="text" class="form-control" aria-describedby="emailHelp" style="width: 30%;"/>
                    <div id="emailHelp" class="form-text">NO utilizar tildes ni el caracter Ñ.</div>
                </div>
                    
                <div class="mb-3">
                    <form:label path="idRubro" class="form-label">Rubro: </form:label>
                    <form:select path="idRubro" class="form-control" style="width: 30%;" required="true">
                        <option value="">Seleccione el rubro del negocio...</option>
                        <c:forEach var="nomRu" items="${rubrosList}">
                            <form:option value="${nomRu.idRubro}">${nomRu.nombreRubro}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
                
                <div class="mb-3">
                    <form:label path="cantEmpleados" class="form-label">Cantidad de empleados: </form:label>
                    <form:input path="cantEmpleados" required="true" type="number" class="form-control" style="width: 30%;" />
                </div>
                   
                <div class="mb-3">
                    <form:label path="cantSucursales" class="form-label">Cantidad de sucursales: </form:label>
                    <form:input path="cantSucursales" required="true" type="number" class="form-control" style="width: 30%;"/>
                </div>
                
                <div class="mb-3">
                    <form:label path="fechaRegistroVisita" class="form-label">Fecha: </form:label>
                    <form:input path="fechaRegistroVisita" type="date" required="true" class="form-control" aria-describedby="emailHelp" style="width: 30%;"/>
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
                <a href="${getPerson}" class="btn btn-danger">Regresar</a>
            </form:form>
        </div>
        </br>

    </body>
</html>