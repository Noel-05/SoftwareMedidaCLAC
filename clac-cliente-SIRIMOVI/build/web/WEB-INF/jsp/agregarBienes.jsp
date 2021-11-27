<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Bienes</title>
    </head>
    
    <%@include file="nav.jsp" %>
    
    <body>
        </br>
        
        <h1 align="center">Crear Nuevo Bienes</h1>
        </br>
        
        <hr size="4px" style="width:50%; margin: auto; color: black;" />
        </br>

        <c:url var="saveUrl" value="/bienesAdd" />
        <c:url var="getBienes" value="/getallBienes" />
        
        <div class="container mt-4" align="center">
            <form:form modelAttribute="bienesAttribute" method="POST" action="${saveUrl}" enctype="multipart/form-data">
                
                <div class="mb-3">
                    <form:label path="idInformacionFinanciera" class="form-label">Negocio </form:label>
                    <form:select path="idInformacionFinanciera" class="form-control" style="width: 30%;" required="true">
                        <option value="">Seleccione el negocio...</option>
                        <c:forEach var="nomNeg" items="${informacionFinancieraList}">
                            <form:option value="${nomNeg.idinformacionFinanciera}">${nomNeg.nombreNegocio}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
                    
                <div class="mb-3">
                    <form:label path="nombreBien" class="form-label">Nombre Bien </form:label>
                    <form:input path="nombreBien" required="true" type="text" class="form-control" aria-describedby="emailHelp" style="width: 30%;"/>
                </div>
                    
                <div class="mb-3">
                    <form:label path="cantidadBien" class="form-label">Cantidad Bien </form:label>
                    <form:input path="cantidadBien" required="true" type="number" class="form-control" aria-describedby="emailHelp" style="width: 30%;"/>
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
                <a href="${getBienes}" class="btn btn-danger">Regresar</a>
            </form:form>
        </div>

    </body>
</html>