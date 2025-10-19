package ma.youcode.ehospital.servlets.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.ehospital.exception.ValidationException;
import ma.youcode.ehospital.model.Patient;
import ma.youcode.ehospital.repository.IPersonRepository;
import ma.youcode.ehospital.repository.impl.PersonRepositoryImpl;
import ma.youcode.ehospital.service.IAuthService;
import ma.youcode.ehospital.service.impl.AuthServiceImpl;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private IAuthService authService;

    @Override
    public void init() {
        IPersonRepository personRepository = new PersonRepositoryImpl();
        authService = new AuthServiceImpl(personRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/auth/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String password = req.getParameter("password");
        String confirm = req.getParameter("confirm");

        if (!password.equals(confirm)) {
            req.setAttribute("errorMessage", "Passwords do not match");
            req.getRequestDispatcher("/auth/register.jsp").forward(req, resp);
            return;
        }
        Patient patient = null;

        try {
            patient = new Patient();
            patient.setEmail(email);
            patient.setFirstName(firstName);
            patient.setLastName(lastName);
            patient.setPassword(password);
            patient.setRole("Patient");

            authService.register(patient);

            resp.sendRedirect(req.getContextPath() + "/auth/login.jsp");

        } catch (ValidationException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.setAttribute("patient", patient);
            req.getRequestDispatcher("/auth/register.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "An error occurred while processing your request.");
            req.setAttribute("patient", patient);
            req.getRequestDispatcher("/auth/register.jsp").forward(req, resp);
        }
    }
}
