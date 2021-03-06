<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Informacion Comercial</title>
    </head>

    <%@include file="nav.jsp" %>

    <body>
        </br>

        <h1 align="center">Crear Nueva Informacion Comercial</h1>
        </br>

        <hr size="4px" style="width:50%; margin: auto; color: black;" />
        </br>

        <c:url var="saveUrl" value="/addInformacionComercial" />
        <c:url var="geInfComer" value="/getallInformacionComercial" />

        <div class="container mt-4" align="center">
            <form:form modelAttribute="informacionComercialAttribute" method="POST" action="${saveUrl}" enctype="multipart/form-data">

                <div class="mb-3">
                    <form:label path="idInformacionOrganizacional" class="form-label">Negocio </form:label>
                    <form:select path="idInformacionOrganizacional" class="form-control" style="width: 30%;" required="true">
                        <option>Seleccione el negocio...</option>
                        <c:forEach var="infOrg" items="${InformacionOrganizacionalList}">
                            <form:option value="${infOrg.idInfOrganizacional}">${infOrg.nombreNegocio}</form:option>
                        </c:forEach>
                    </form:select>
                </div>

                <div class="mb-3">
                    <form:label path="departamentosDeComercioInterior" class="form-label">Departamentos de Comercio Interior: </form:label>
                    <form:input path="departamentosDeComercioInterior" required="true" type="text" class="form-control" aria-describedby="emailHelp" style="width: 30%;"/>
                    <div id="emailHelp" class="form-text">Porfavor Escribir cada Departamento separado por comas (,).</div>
                </div>

                <div class="mb-3">
                    <form:label path="paisesDeComercioExterior" class="form-label">Paises de Comercio Exterior: </form:label>
                    <form:input path="paisesDeComercioExterior" type="text" class="form-control" aria-describedby="emailHelp" style="width: 30%;"/>
                    <div id="emailHelp" class="form-text">Porfavor Escribir cada Pais separado por comas (,).</div>
                </div>

                <div class="mb-3">
                    <form:label path="empresasSocias" class="form-label">Empresas Socias: </form:label>
                    <form:input path="empresasSocias" type="text" class="form-control" aria-describedby="emailHelp" style="width: 30%;"/>
                    <div id="emailHelp" class="form-text">Porfavor Escribir cada Empresa separada por comas (,).</div>
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
                <a href="${geInfComer}" class="btn btn-danger">Regresar</a>
            </form:form>
        </div>

    </body>
</html>