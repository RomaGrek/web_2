package servlets;
import entity.Information;
import entity.ListInformation;

import java.math.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.annotation.WebServlet;

public class AreaCheckServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String x = request.getParameter("corX").replace(",", ".");
        String y = request.getParameter("corY").replace(",", ".");
        String r = request.getParameter("corR").replace(",", ".");
        long nowTime = System.nanoTime();
        String intermediateResult = validate(x, y, r);
        if (intermediateResult.equals("Выполнено!")) {
            double x_ok = Double.parseDouble(x);
            double y_ok = Double.parseDouble(y);
            double r_ok = Double.parseDouble(r);

            String result = chekPenetration(x_ok, y_ok, r_ok);

            Information info = new Information(x_ok, roundCoordinate(y_ok), r_ok, String.valueOf(nowTime), String.valueOf(LocalDateTime.now()), result);

            ListInformation listInformation = (ListInformation) request.getSession().getAttribute("listInformation");
            if (listInformation == null) {
                listInformation = new ListInformation();
            }
            listInformation.getListInformation().add(info);
            request.getSession().setAttribute("listInformation", listInformation);
            request.getSession().setAttribute("serverInfo", intermediateResult);
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }else {
            request.getSession().setAttribute("serverInfo", intermediateResult);
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }


    }

    public double roundCoordinate(double cor) {
        double quantity = Math.pow(10, 2);
        double result = Math.ceil(cor * quantity) / quantity;
        return result;
    }

    public String validate (String x, String y, String r) {
        if (!validateX(x)) { return "Некорректное значение X"; }
        if (!validateY(y)) { return "Некорректное значение Y"; }
        if (!validateR(r)) { return "Некорректное значение R"; }
        return "Выполнено!";
    }

    public boolean validateX (String x) {
        try {
            Double valuesX[] = {-4.0, -3.0, -2.0, -1.0, 0d, 1.0, 2.0, 3.0, 4.0};
            double xVal = Double.parseDouble(x);
            return Arrays.asList(valuesX).contains(xVal);
        }catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean validateY (String y) {
        try{
            double yVal = Double.parseDouble(y);
            return (-3 < yVal) && (yVal < 3);
        }catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean validateR (String r) {
        try {
            Double valuesR[] = {1.0, 1.5, 2.0, 2.5, 3.0};
            double rVal = Double.parseDouble(r);
            return Arrays.asList(valuesR).contains(rVal);
        }catch (NumberFormatException e) {
            return false;
        }
    }



    public String areaOne (double x, double y, double r){
        if (x <= r && y <= r) return "Есть пробитие";
        return "Нету пробития";
    }

    public String areaThree (double x, double y, double r) {
        if ((Math.abs(y) < (r/2)) && (Math.abs(x) < r) && (y >= (-x/2) - (r/2))) return "Есть пробитие";
        return "Нету пробития";
    }

    public String areaFour (double x, double y, double r) {
        if (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2)) return "Есть пробитие";
        return "Нету пробития";
    }

    public String areaOn (double x, double y, double r) {
        if (x == 0) {
            if (y <= r && y >= (-r/2)) return "Есть пробитие";
            return "Нету пробития";
        }
        if (Math.abs(x) <= r) return "Есть пробитие";
        return "Нету пробития";
    }

    public String chekPenetration (double x, double y, double r){
        if (x > 0 && y > 0 ) return areaOne(x, y, r);
        if (x < 0 && y > 0) return "Нету пробития";
        if (x < 0 && y < 0) return areaThree(x, y, r);
        if (x > 0 && y < 0) return areaFour(x, y, r);
        return areaOn(x, y, r);
    }
}
