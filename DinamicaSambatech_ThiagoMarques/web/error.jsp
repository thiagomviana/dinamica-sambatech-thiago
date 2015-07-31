<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Dinâmica Samba Tech - Thiago</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="jumbotron well">
                        <h2>
                            Conversor de Arquivos de Vídeo
                        </h2>
                        <p>
                            Esta aplicação converte um vídeo para um formato compatível com a WEB. 
                        </p>
                    </div>
                </div>
            </div>
            <div class="row">		
                <div class="col-md-12">
                    <div class="alert alert-danger">                       
                        <strong><%out.print(request.getAttribute("status"));%></strong>
                        <div align="center"> 

                            <a href="/" class="btn btn-default">Tentar novamente</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>                    
</html>