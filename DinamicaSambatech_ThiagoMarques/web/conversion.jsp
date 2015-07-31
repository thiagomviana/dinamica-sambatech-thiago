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
                            Para obter o status mais recente da conversão, clique em Atualizar Status.
                            Após a conversão, o vídeo convertido será exibido em um player nesta página.
                        </p>
                    </div>
                </div>
            </div>
            <div class="row">		
                <div class="col-md-12">
                    <div class="alert alert-success">                       
                        <h4>
                            Status da Conversão
                        </h4> <br/> <strong><%out.print(request.getAttribute("status")); %></strong>
                        <img src="img/conversion-loader.gif" alt="Smiley face" height="16" width="16" <%out.print(request.getAttribute("imgVisibility")); %>>
                        <div align="right">
                            <form role="form" method="POST" action="EncodingNotificationsServlet">
                                <input type="hidden" name="sourceURL" value="<%out.print(request.getAttribute("sourceURL"));%>">
                                <input type="hidden" name="destinationURL" value="<%out.print(request.getAttribute("destinationURL"));%>">
                                <input type="hidden" name="videoFormat" value="<%out.print(request.getAttribute("videoFormat"));%>">
                                <input type="hidden" name="started" value="<%out.print(request.getAttribute("started"));%>">
                                
                                <button type="submit" class="btn btn-default" <%out.print(request.getAttribute("refreshStatusVisibility"));%>>
                                    Atualizar Status
                                </button>
                            </form>
                        </div>
                    </div>
                    <div class="media" align="center"<%out.print(request.getAttribute("videoVisibility"));%>>
                        <video width="400" controls>
                            <source src="<%out.print(request.getAttribute("videoURL"));%>" type="video/mp4">                        
                            Seu navegador não suporta vídeos HTML5.
                        </video>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>