package controller;

import encoder.Encoder;
import encoder.Encoding;
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
        Encoder encoder = new Encoding("userID", "userKey");
        String sourceURL, destinationURL;
        InputStream is;
        RequestDispatcher rd;
        Part filePart = request.getPart("videoFile");

        sourceURL = uploader.upload(getFileName(filePart), filePart.getInputStream());
        destinationURL = "http://dinamica-sambatech-thiago.s3.amazonaws.com/" + getFileNameWithoutExt(filePart) + ".mp4?acl=public-read";

        if (sourceURL != null) {
            request.setAttribute("status", "Upload efetuado. Convertendo vídeo.");
            request.setAttribute("alertType", "alert-success");
            request.setAttribute("imgVisibility", "");
            request.setAttribute("videoVisibility", "hidden");

            rd = request.getRequestDispatcher("conversion.jsp");
            rd.forward(request, response);

            encoder.encode(sourceURL, destinationURL, "MP4 H264 3G 240p 350k", "");
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

    private String getFileNameWithoutExt(Part part) {
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
