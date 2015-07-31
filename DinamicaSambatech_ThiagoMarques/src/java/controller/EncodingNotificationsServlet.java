package controller;

import encoder.Encoder;
import encoder.Encoding;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EncodingNotificationsServlet", urlPatterns = {"/EncodingNotificationsServlet"})
public class EncodingNotificationsServlet extends HttpServlet {
    private Encoder encoder;
    

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
        RequestDispatcher rd;
        boolean erro;
        String status = null, started;
        
        started = (String)request.getParameter("started");
                
        if(started.equals("false")){ //se a conversao ainda nao foi iniciada
            encoder = new Encoding(); 
            
            int res = encoder.encode((String)request.getParameter("sourceURL"), (String)request.getParameter("destinationURL"),
                    (String)request.getParameter("videoFormat"));
            
            if (res != 0){ //se houve erro ao iniciar a conversao
                erro = true;
                
                if(res == 1) status = "Erro ao iniciar a conversão. Verifique o arquivo enviado e tente novamente";
                else status = "O serviço de conversão de vídeos está indisponível. Tente novamente mais tarde";
            }
        }
        
        erro = !encoder.refreshStatus();
        if(status == null) status = encoder.getEncodingStatus();

        if (erro) {
            rd = request.getRequestDispatcher("error.jsp");
            
            request.setAttribute("videoVisibility", "hidden");            
            request.setAttribute("imgVisibility", "hidden");
            request.setAttribute("status", status);
            request.setAttribute("videoVisibility", "hidden");            

            rd.forward(request, response);
            
        } else if(status.equals("Conversão finalizada")){
            rd = request.getRequestDispatcher("conversion.jsp");
            String videoURL = encoder.getDestinationURL();

            request.setAttribute("status", status);
            request.setAttribute("started", "true");            
            request.setAttribute("videoVisibility", "");
            request.setAttribute("imgVisibility", "hidden");
            request.setAttribute("alertType", "alert-success");
            request.setAttribute("refreshStatusVisibility", "hidden");

            request.setAttribute("videoURL", videoURL.substring(0, videoURL.indexOf('?')));

            rd.forward(request, response);
            
        } else { //status entre o inicio e o fim da conversao
            rd = request.getRequestDispatcher("conversion.jsp");

            request.setAttribute("status", status + "<br/>Progresso: " + encoder.getProgress() + "%<br/>Tempo Restante Estimado: " + encoder.getRemainingTime() + " segundos");
            request.setAttribute("started", "true");
            request.setAttribute("videoVisibility", "hidden");
            request.setAttribute("imgVisibility", "hidden");
            request.setAttribute("alertType", "alert-success");
            request.setAttribute("refreshStatusVisibility", "");

            rd.forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
