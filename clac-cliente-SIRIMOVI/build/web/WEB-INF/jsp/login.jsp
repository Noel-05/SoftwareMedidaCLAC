<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Tangerine">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Anton">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Italianno">
        <link rel="stylesheet" href="<c:url value='/img/css/estilo.css'/>" >
        <title>SIRIMOVI | Inicio</title>
        <style>
            .texto {
                font-family: Times New Roman;
            }

            .texto2 {
                font-family: 'Tangerine', serif;
            }

            .texto3 {
                font-family: 'Anton', serif;
            }

            .texto4 {
                font-family: 'Italianno', serif;
            }
        </style>
    </head>
    
<!--    Times New Roman, Georgia, Arial, Verdana, Courier New, Lucida, Console-->
    
    <%@include file="nav2.jsp" %>

    <body>
        
        <c:url var="login" value="/iniciarSesion" />
        
        <c:if test="${alerta == 'alerta'}">
            </br>
            <div align="center">
                <div class="alert alert-danger alert-dismissable" align="center" style="width: 60%;">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>¡Acceso Inválido!</strong> Usuario o contraseña no válida.
                </div>
            </div>
        </c:if>
        
        </br>
        <h1 align="center" class="texto"><strong>SIRIMOVI | CLAC</strong></h1>
        
        </br>
        <div align="center">
            <img src="<c:url value='/img/CLAC.png'/>" width="20%" height="20%"/>
        </div>
        
        </br></br></br>
        <!-- Button trigger modal -->
        <div align="center">
            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#exampleModalCenter" style="background-color: #cc0000; border-color: #cc0000;">
              Iniciar Sesión.
            </button>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle">Iniciar Sesión</h5>
                    </div>
                    
                    <div class="modal-body">
                        
                        <div class="container mt-4" style="width: 100%">
                            <div class="card border-dark">
                                <div class="card-header bg-dark text-white" align="center">
                                    <strong>Iniciar Sesión</strong>
                                </div>
                                <div class="card-body">
                                    <div align="center">
                                        <img src="<c:url value='/img/CLAC.png'/>" width="30%" height="30%"/>
                                    </div>
                                    </br>
                                    <div align="center">
                                        <form action="${login}" method="POST" style="width: 85%; display: inline-block; text-align: center; ">
                                            <div class="mb-3">
                                                <label for="" class="form-label"><strong>Username:</strong></label>
                                                <input name="correo" type="text" class="form-control" placeholder="Correo Electrónico" required>
                                            </div> 
                                            <div class="mb-3">
                                                <label for="" class="form-label"><strong>Password:</strong></label>
                                                <input name="password" type="password" class="form-control" placeholder="Contraseña" required>
                                            </div>
                                            </br>
                                            <div class="mb-3">
                                                <button type="submit" class="btn btn-primary" style="background-color: #cc0000; border-color: #cc0000;">Ingresar</button>
                                            </div> 
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                                    
                    </div>
                                    
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal" style="background-color: black; color: white;">Cancelar</button>
                    </div>
                </div>
            </div>
        </div>
        
        </br></br>
           
    </body>
</html>
