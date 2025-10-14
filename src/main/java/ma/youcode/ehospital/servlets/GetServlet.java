package ma.youcode.ehospital.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/getServlet")
public class GetServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
//        super.doGet(request, response);

        String value = request.getParameter("name");
        String htmlResponse = "<html><h3>Welcome to the servlets!</h3></html>";
        String test = "<h1>" + value + "</h1>";
        PrintWriter writer = response.getWriter();
        writer.write(htmlResponse);
        writer.write(test);
    }
}
