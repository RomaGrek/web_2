package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Получаем данные координат */
//        String x = request.getParameter("cordinateX");
//        String y = request.getParameter("cordinateY");
//        String r = request.getParameter("radiusR");

        getServletContext().getRequestDispatcher("/AreaCheckServlet").forward(request, response);
    }


    /*Ну тупо перенаврпавляем на jsp, пока что непонятно зачем */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }


}
