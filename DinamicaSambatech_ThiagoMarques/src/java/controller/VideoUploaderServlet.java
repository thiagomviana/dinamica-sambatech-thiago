package controller;

import io.S3Uploader;
import io.Uploader;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "VideoUploaderServlet", urlPatterns = {"/VideoUploaderServlet"})
@MultipartConfig
public class VideoUploaderServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {   
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Uploader uploader = new S3Uploader();
        String sourceURL, destinationURL;
        InputStream is;
        RequestDispatcher rd;
        Part filePart = request.getPart("videoFile");
        
        
        sourceURL = uploader.upload(getFileName(filePart), filePart.getInputStream());
        destinationURL = "http://dinamica-sambatech-thiago.s3.amazonaws.com/" + getFileNameWithoutExt(filePart) + ".mp4?acl=public-read";

        if (sourceURL != null) { //se o upload for feito normalmente
            request.setAttribute("status", "Upload do vídeo concluído. Para iniciar a conversão clique em Iniciar conversão.");
            request.setAttribute("sourceURL", sourceURL);
            request.setAttribute("destinationURL", destinationURL);
            request.setAttribute("videoFormat", "MP4 H264 3G 240p 350k");
            request.setAttribute("started", "false");
            request.setAttribute("alertFunction", "showUploadedMessage()");

            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);           
        } else { //se ocorrer um erro no upload
            rd = request.getRequestDispatcher("error.jsp");
            request.setAttribute("status", "Erro ao fazer upload do vídeo. Verifique o arquivo enviado e tente novamente.");

            rd.forward(request, response);
        }
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }

        return null;
    }

    private String getFileNameWithoutExt(Part part) { //obtem o nome do arquivo sem extensao
        String ret = getFileName(part);
        if (ret != null) {
            return ret.substring(0, ret.indexOf('.'));
        }

        return null;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
