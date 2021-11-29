<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </head>
    
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            
            <c:url var="home" value="/index" />
            <c:url var="login" value="/" />
            <c:url var="getPerson" value="/getall" />
            <c:url var="prueba" value="/getallPrueba" />
            <c:url var="informacionOrganizacional" value="/getallInformacionOrganizacional" />
            <c:url var="informacionComercial" value="/getallInformacionComercial" />
            <c:url var="usuario" value="/getallUsuarios" />
            <c:url var="monitoreo" value="/getallMonitoreos" />
            <c:url var="producto" value="/getallProducto" />
            <c:url var="bien" value="/getallBienes" />
                
            <div class="container-fluid">
                
                <a class="navbar-brand" href="${home}" >
                    <img src="<c:url value='/img/CLAC-SFC21.png'/>" width="18%" height="18%"/>
                    <strong>&nbsp; &nbsp; &nbsp;SIRIMOVI</strong>
                </a>
                    
                <a class="nav-link" href="${login}" style="color: white;">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-power" viewBox="0 0 16 16">
                        <path d="M7.5 1v7h1V1h-1z"/>
                        <path d="M3 8.812a4.999 4.999 0 0 1 2.578-4.375l-.485-.874A6 6 0 1 0 11 3.616l-.501.865A5 5 0 1 1 3 8.812z"/>
                    </svg>
                </a>
                
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                
                <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                    <div class="navbar-nav">
                        
                        <a class="nav-link active" aria-current="page" href="${home}">Inicio</a>
                        
                        <a class="nav-link" href="${usuario}">Usuario</a>
                        
                        <a class="nav-link" href="${informacionOrganizacional}">Visitas</a>
                        
                        <a class="nav-link" href="${monitoreo}">Monitoreos</a>
                        
<!--                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="${getPerson}" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Personas
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="${getPerson}">Personas</a></li>
                                <li><a class="dropdown-item" href="${prueba}">Prueba Personas</a></li>
                                <li><hr class="dropdown-divider"></li>
                            </ul>
                        </li>-->
                    
                    </div>
                </div>
            </div>
        </nav>
    </body>
</html>
