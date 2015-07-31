<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Dinâmica Samba Tech - Thiago</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

        <script type="text/javascript">
            function showUploadingMessage() {
                document.getElementById("uploadAlert").style.visibility = 'visible';
                document.getElementById("uploadAlert").innerHTML = '<h4>Fazendo upload do vídeo</h4>';
            }
        </script>
        
        <script type="text/javascript">
            function showUploadedMessage() {
                document.getElementById("form").style.visibility = 'hidden';
                document.getElementById("uploadAlert").style.visibility = 'visible';                                
            }
        </script>
    </head>
    <body onload="<%out.print(request.getAttribute("alertFunction"));%>">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="jumbotron well">
                        <h2>
                            Conversor de Arquivos de Vídeo
                        </h2>
                        <p>
                            Esta aplicação converte um vídeo para MP4 em 240p, um formato compatível com a WEB. 
                            Para fazer a conversão, selecione o arquivo que deseja converter no formulário abaixo.
                            Após a conversão, o vídeo convertido será exibido em um player nesta página.
                        </p>
                    </div>
                    <div class="alert alert-success" id="uploadAlert" style="visibility: hidden">
                        <h4><%out.print(request.getAttribute("status"));%></h4>
                        <div align="right">
                            <form role="form" method="POST" action="EncodingNotificationsServlet">
                                <input type="hidden" name="sourceURL" value="<%out.print(request.getAttribute("sourceURL"));%>">
                                <input type="hidden" name="destinationURL" value="<%out.print(request.getAttribute("destinationURL"));%>">
                                <input type="hidden" name="videoFormat" value="<%out.print(request.getAttribute("videoFormat"));%>">
                                <input type="hidden" name="started" value="<%out.print(request.getAttribute("started"));%>">
                                <button type="submit" class="btn btn-default">
                                    Iniciar conversão
                                </button>
                            </form>
                         </div>
                    </div>
                </div>
            </div>
            <div class="row" id="form">
                <div class="col-md-12">
                    <form role="form" method="POST" action="VideoUploaderServlet" enctype="multipart/form-data" onsubmit="showUploadingMessage()">
                        <div class="form-group">
                            <p> Selecione o vídeo:
                                <input name="videoFile" type="file"  accept="video/*" class="form-control" aria-describedby="basic-addon1">
                            </p>

                            <!--<p> Selecione o formato do arquivo
                                <select name="videoFormat" class="btn btn-default">
                                    <option value="MP4">MP4</option>
                                </select>
                            </p>-->
                            <br/>
                            <div align="center">
                                <button type="submit" class="btn btn-default alert-danger">
                                    <h4>Enviar Vídeo</h4>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
