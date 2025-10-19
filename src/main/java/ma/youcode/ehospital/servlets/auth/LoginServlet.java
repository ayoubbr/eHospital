package ma.youcode.ehospital.servlets.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ma.youcode.ehospital.exception.ValidationException;
import ma.youcode.ehospital.model.Patient;
import ma.youcode.ehospital.model.Person;
import ma.youcode.ehospital.repository.IPersonRepository;
import ma.youcode.ehospital.repository.impl.PersonRepositoryImpl;
import ma.youcode.ehospital.service.IAuthService;
import ma.youcode.ehospital.service.impl.AuthServiceImpl;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private IAuthService authService;

    @Override
    public void init() {
        IPersonRepository personRepository = new PersonRepositoryImpl();
        authService = new AuthServiceImpl(personRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/auth/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            // Use a concrete subclass of Person to hold credentials
            Patient tempPerson = new Patient();
            tempPerson.setEmail(email);
            tempPerson.setPassword(password);

            Person loggedInUser = authService.login(tempPerson);

            HttpSession session = req.getSession();
            session.setAttribute("user", loggedInUser);
            session.setAttribute("role", loggedInUser.getRole());

            // Redirect by role
            switch (loggedInUser.getRole().toUpperCase()) {
                case "ADMIN":
                    resp.sendRedirect(req.getContextPath() + "/admin/dashboard.jsp");
                    break;
                case "DOCTOR":
                    resp.sendRedirect(req.getContextPath() + "/doctor/dashboard.jsp");
                    break;
                case "PATIENT":
                    resp.sendRedirect(req.getContextPath() + "/patient/dashboard.jsp");
                    break;
                default:
                    req.setAttribute("errorMessage", "Unknown role: " + loggedInUser.getRole());
                    req.getRequestDispatcher("/auth/login.jsp").forward(req, resp);
            }

        } catch (ValidationException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/auth/login.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/auth/login.jsp").forward(req, resp);
        }
    }
}
