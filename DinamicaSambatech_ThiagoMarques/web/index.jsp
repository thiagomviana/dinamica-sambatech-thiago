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
                        Esta aplicação converte um vídeo para MP4 em 240p, um formato compatível com a WEB. 
                        Para fazer a conversão, selecione o arquivo que deseja converter no formulário abaixo.
                        Após a conversão, o vídeo convertido será exibido em um player nesta página.
                    </p>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <form role="form" method="POST" action="VideoUploaderServlet" enctype="multipart/form-data">
                    <div class="form-group">
                        <p> Selecione o vídeo:
                            <input name="videoFile" type="file" class="form-control" aria-describedby="basic-addon1">
                        </p>

                        <!--<p> Selecione o formato do arquivo
                            <select name="videoFormat" class="btn btn-default">
                                <option value="MP4">MP4</option>
                            </select>
                        </p>-->
                        <br/>
                        <div align="center">
                            <button type="submit" class="btn btn-default alert-danger">
                                <h4>Converter!</h4>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</html>
