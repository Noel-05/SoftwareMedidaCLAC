<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SIRIMOVI | Información Organizacional</title>
        <!--        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>-->
    </head>

    <%@include file="nav.jsp" %>

    <body>
        </br>

        <c:url var="addUrl" value="/addInformacionOrganizacional" />
        <c:url var="filtrarUrl" value="/filtrarInformacionOrganizacionalGet" />
        <c:url var="getInfCom" value="/getallInformacionComercial" />
        <c:url var="getInfFin" value="/getAllInformacionFinanciera" />

        <h1 align="center">Registro de Visita</h1>
        </br>
        <hr size="4px" style="width:50%; margin: auto; color: black;" />
        </br>
        <h4 align="center">Consulta de Informaci&oacute;n Organizacional</h4>

        <div class="container mt-4" align="center">
            <form method="POST" action="${filtrarUrl}">
                <div class="mb-3">
                    <div style="display: inline-block">
                        <select class="form-control" style="width: 100%;" id="paisSel" required name="paisSel">
                            <option value="">Seleccione el país a filtrar...</option>
                            <option value="0" style="color: red;">TODOS</option>
                            <c:forEach var="nomPa" items="${paisList}">
                                <option value="${nomPa.idPais}">${nomPa.nombrePais}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary" style="background-color: white; border-color: blue; color: blue;" >Filtrar</button>
                </div>
            </form>
        </div>

        <div class="container mt-4" align="center">
            <div style="display: inline-block">
                <a href="${getInfCom}" class="btn btn-success" title="Consultar Información Comercial">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bar-chart-fill" viewBox="0 0 16 16">
                        <path d="M1 11a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v3a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1v-3zm5-4a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v7a1 1 0 0 1-1 1H7a1 1 0 0 1-1-1V7zm5-5a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1h-2a1 1 0 0 1-1-1V2z"/>
                    </svg>
                    |
                </a>
            </div>
            &nbsp;
            <div style="display: inline-block">
                <a href="${addUrl}" class="btn btn-success" title="Agregar Información Organizacional"> Agregar </a>
            </div>
            &nbsp;
            <div style="display: inline-block">
                <a href="${getInfFin}" class="btn btn-success" title="Consultar Información Financiera">
                    | <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-currency-dollar" viewBox="0 0 16 16">
                        <path d="M4 10.781c.148 1.667 1.513 2.85 3.591 3.003V15h1.043v-1.216c2.27-.179 3.678-1.438 3.678-3.3 0-1.59-.947-2.51-2.956-3.028l-.722-.187V3.467c1.122.11 1.879.714 2.07 1.616h1.47c-.166-1.6-1.54-2.748-3.54-2.875V1H7.591v1.233c-1.939.23-3.27 1.472-3.27 3.156 0 1.454.966 2.483 2.661 2.917l.61.162v4.031c-1.149-.17-1.94-.8-2.131-1.718H4zm3.391-3.836c-1.043-.263-1.6-.825-1.6-1.616 0-.944.704-1.641 1.8-1.828v3.495l-.2-.05zm1.591 1.872c1.287.323 1.852.859 1.852 1.769 0 1.097-.826 1.828-2.2 1.939V8.73l.348.086z"/>
                    </svg>
                </a>
            </div>
        </div>

        <i class="bi bi-trash-fill"></i>

        <div class="container mt-4">
            <div class="card border-dark">
                <div class="card-header bg-dark text-white" align="center">
                    Informaci&oacute;n Organizacional
                </div>

                <div class="card-body">
                    <table class="table table-hover text-center">

                        <thead>
                            <tr>
                                <th class="text-center" style="color:red"><strong> Nombre del Negocio </strong></th>
                                <th class="text-center" style="color:red"><strong> Pais del Negocio </strong></th>
                                <th class="text-center" style="color:red"><strong> Rubro </strong></th>
                                <th class="text-center" style="color:red; width: 5%"><strong> Cant. de Empleados </strong></th>
                                <th class="text-center" style="color:red; width: 5%"><strong> Cant. de Sucursales </strong></th>
                                <th class="text-center" style="color:red"><strong> Fecha Visita </strong></th>
                                <th class="text-center" style="color:red; display:none;"><strong> Archivo </strong></th>
                                <th class="text-center" style="color:red"><strong> Acciones </strong></th>
                                <th class="text-center" style="color:red"><strong> Opciones </strong></th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="informacionOrganizacional" items="${informacionOrganizacionalGetAll}">
                                <c:url var="editUrl" value="/updateInformacionOrganizacional?id=${informacionOrganizacional.idInfOrganizacional}" />
                                <c:url var="deleteUrl" value="/deleteInformacionOrganizacional?id=${informacionOrganizacional.idInfOrganizacional}" />
                                <c:url var="getUrl" value="/getIdInformacionOrganizacional?id=${informacionOrganizacional.idInfOrganizacional}" />

                                <c:url var="getInfComF" value="/getallInformacionComercialF?idInfOrg=${informacionOrganizacional.idInfOrganizacional}" />
                                <c:url var="getInfFinF" value="/getAllInformacionFinancieraF?idInfOrg=${informacionOrganizacional.idInfOrganizacional}" />
                                <c:url var="descarga" value="/descarga?archivo=${informacionOrganizacional.archivo}" />

                                <tr>
                                    <td><c:out value="${informacionOrganizacional.nombreNegocio}" /></td>
                                    <td><c:out value="${informacionOrganizacional.nombrePais}" /></td>
                                    <td><c:out value="${informacionOrganizacional.nombreRubro}" /></td>
                                    <td><c:out value="${informacionOrganizacional.cantEmpleados}" /></td>
                                    <td><c:out value="${informacionOrganizacional.cantSucursales}" /></td>
                                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${informacionOrganizacional.fechaRegistroVisita}"/></td>
                                    <td style="display: none;"><c:out value="${informacionOrganizacional.archivo}" /></td>

                                    <td>
                                        <a href="${editUrl}" class="btn btn-warning btn-sm" title="Editar Información Organizacional"> 
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-fill" viewBox="0 0 16 16">
                                                <path d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"/>
                                            </svg>
                                        </a>

                                        <a href="${deleteUrl}" class="btn btn-danger btn-sm" title="Eliminar Información Organizacional" onclick="return confirm('¿Seguro que desea eliminar esté registro?')"> 
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash-fill" viewBox="0 0 16 16">
                                                <path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z"/>
                                            </svg>
                                        </a>

                                        <a href="${getUrl}" class="btn btn-primary btn-sm" title="Consultar Información Organizacional">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-card-list" viewBox="0 0 16 16">
                                                <path d="M14.5 3a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h13zm-13-1A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-13z"/>
                                                <path d="M5 8a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7A.5.5 0 0 1 5 8zm0-2.5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5zm0 5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5zm-1-5a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0zM4 8a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0zm0 2.5a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0z"/>
                                            </svg>
                                        </a>

                                        <c:if test="${informacionOrganizacional.archivo.equals('no archivo') == false}">
                                            <a href="${descarga}" class="btn btn-primary btn-sm" title="Descargar archivos">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="currentColor" stroke="#000000" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"> <circle cx="12" cy="12" r="10"/>
                                                    <path d="M16 12l-4 4-4-4M12 8v7"/>
                                                </svg>
                                            </a>
                                        </c:if>
                                    </td>

                                    <td>
                                        <a href="${getInfComF}" class="btn btn-success btn-sm" title="Agregar Información Comercial">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bar-chart-fill" viewBox="0 0 16 16">
                                                <path d="M1 11a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v3a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1v-3zm5-4a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v7a1 1 0 0 1-1 1H7a1 1 0 0 1-1-1V7zm5-5a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1h-2a1 1 0 0 1-1-1V2z"/>
                                            </svg>
                                        </a>

                                        <a href="${getInfFinF}" class="btn btn-success btn-sm" title="Agregar Información Financiera">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-currency-dollar" viewBox="0 0 16 16">
                                                <path d="M4 10.781c.148 1.667 1.513 2.85 3.591 3.003V15h1.043v-1.216c2.27-.179 3.678-1.438 3.678-3.3 0-1.59-.947-2.51-2.956-3.028l-.722-.187V3.467c1.122.11 1.879.714 2.07 1.616h1.47c-.166-1.6-1.54-2.748-3.54-2.875V1H7.591v1.233c-1.939.23-3.27 1.472-3.27 3.156 0 1.454.966 2.483 2.661 2.917l.61.162v4.031c-1.149-.17-1.94-.8-2.131-1.718H4zm3.391-3.836c-1.043-.263-1.6-.825-1.6-1.616 0-.944.704-1.641 1.8-1.828v3.495l-.2-.05zm1.591 1.872c1.287.323 1.852.859 1.852 1.769 0 1.097-.826 1.828-2.2 1.939V8.73l.348.086z"/>
                                            </svg>
                                        </a>
                                    </td>

                                </tr>

                            </c:forEach>
                        </tbody>

                    </table>
                </div>
            </div>
        </div>
        </br></br>

        <div align="center">
            <c:if test="${empty informacionOrganizacionalGetAll}" >
                No hay Información Organizacional en la Base de Datos para este registro.
                </br>
                Puedes <strong><a href="${addUrl}">Agregar</a></strong> una Información Organizacional aquí.
            </c:if>
        </div>

    </body>
</html>
