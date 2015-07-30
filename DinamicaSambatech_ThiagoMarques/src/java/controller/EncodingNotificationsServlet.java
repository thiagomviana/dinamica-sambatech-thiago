package controller;

import encoder.ResponseParser;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EncodingNotificationsServlet", urlPatterns = {"/EncodingNotificationsServlet"})
public class EncodingNotificationsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ByteArrayInputStream sbip;
        ResponseParser rp;
        RequestDispatcher rd;
        String notification = request.getParameter("xml");
        String status;

        sbip = new ByteArrayInputStream(notification.getBytes());
        rp = new ResponseParser(sbip);
        rd = request.getRequestDispatcher("conversion.jsp");

        status = rp.getValueByElementName("status");

        if (status.equals("Error")) {
            request.setAttribute("videoVisibility", "hidden");
            request.setAttribute("alertType", "alert-danger");
            request.setAttribute("imgVisibility", "hidden");
            request.setAttribute("status", "Ocorreu um erro na conversão do vídeo. Verifique o arquivo enviado e tente mais tarde.");
            request.setAttribute("videoVisibility", "hidden");

            rd.forward(request, response);
        } else if (status.equals("Finished")) {
            String videoURL = rp.getValueByElementName("destination");

            request.setAttribute("status", "Conversão Finalizada.");
            request.setAttribute("videoVisibility", "");
            request.setAttribute("imgVisibility", "hidden");
            request.setAttribute("alertType", "alert-success");

            request.setAttribute("videoURL", videoURL.substring(0, videoURL.indexOf('?')));

            rd.forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
