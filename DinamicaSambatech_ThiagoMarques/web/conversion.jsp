<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Dinâmica Samba Tech - Thiago</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    </head>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="jumbotron well">
                    <h2>
                        Conversor de Arquivos de Vídeo
                    </h2>
                    <p>
                        Esta aplicação converte um vídeo para um formato compatível com a WEB. 
                        Para fazer a conversão, selecione o arquivo que deseja converter no formulário abaixo.
                        Após a conversão, o vídeo convertido será exibido no player ao lado.
                    </p>
                </div>
            </div>
        </div>
        <div class="row">		
            <div class="col-md-12">
                <div class="alert <%out.print(request.getAttribute("alertType")); %>">                       
                    <h4>
                        Status da Conversão
                    </h4> <strong><%out.print(request.getAttribute("status")); %></strong>
                    <img src="img/conversion-loader.gif" alt="Smiley face" height="16" width="16" <%out.print(request.getAttribute("imgVisibility")); %>>
                    <div align="right">
                        <form role="form" method="POST" action="EncodingNotificationsServlet">
                            <input type="hidden" name="xml" value="<?xml version='1.0'?>
                                   <result>
                                   <mediaid>[MediaID]</mediaid>
                                   <destination>http://dinamica-sambatech-thiago.s3.amazonaws.com/teste.mp4?acl=public-read</destination>
                                   <status>Finished</status>
                                   <description>[ ErrorDescription]</description>
                                   </result>">
                            <button type="submit" class="btn btn-default">
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
</html>