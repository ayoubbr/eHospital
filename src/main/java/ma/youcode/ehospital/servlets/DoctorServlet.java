package ma.youcode.ehospital.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.ehospital.model.Department;
import ma.youcode.ehospital.model.Doctor;
import ma.youcode.ehospital.model.Room;
import ma.youcode.ehospital.repository.*;
import ma.youcode.ehospital.repository.impl.*;
import ma.youcode.ehospital.service.IAdminService;
import ma.youcode.ehospital.service.impl.AdminServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/doctors")
public class DoctorServlet extends HttpServlet {

    private IDoctorRepository doctorRepo = new DoctorRepositoryImpl();
    private IConsultationRepository consultationRepository = new ConsultationRepositoryImpl();
    private IDepartmentRepository departmentRepo = new DepartmentRepositoryImpl();
    private IRoomRepository roomRepo = new RoomRepositoryImpl();

    private IAdminService adminService = new AdminServiceImpl(doctorRepo, consultationRepository, departmentRepo, roomRepo);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "new":
                try {
                    List<Department> departments = departmentRepo.findAll();
                    request.setAttribute("departments", departments);
                    request.setAttribute("doctor", new Doctor());
                    request.getRequestDispatcher("/doctors/form.jsp").forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("errorMessage", e.getMessage());
                    request.getRequestDispatcher("/doctors/list.jsp").forward(request, response);
                }
                break;
            case "edit":
                try {
                    List<Department> departmentList = departmentRepo.findAll();
                    request.setAttribute("departments", departmentList);
                    int id = Integer.parseInt(request.getParameter("id"));
                    Doctor doctor = adminService.getDoctorById(id);
                    request.setAttribute("doctor", doctor);
                    request.getRequestDispatcher("/doctors/form.jsp").forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("errorMessage", e.getMessage());
                    List<Doctor> doctors = adminService.getDoctors();
                    request.setAttribute("doctors", doctors);
                    request.getRequestDispatcher("/doctors/list.jsp").forward(request, response);
                }
                break;
            case "delete":
                try {
                    Doctor doctorById = adminService.getDoctorById(Integer.parseInt(request.getParameter("id")));
                    adminService.deleteDoctor(doctorById);
                    response.sendRedirect(request.getContextPath() + "/doctors");
                } catch (Exception e) {
                    e.printStackTrace();
                    List<Doctor> doctors = adminService.getDoctors();
                    request.setAttribute("doctors", doctors);
                    request.setAttribute("errorMessage", "Something went wrong.");
                    request.getRequestDispatcher("/doctors/list.jsp").forward(request, response);
                }
                break;
            default:
                List<Doctor> doctors = adminService.getDoctors();
                request.setAttribute("doctors", doctors);
                request.getRequestDispatcher("/doctors/list.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = request.getParameter("id") == null || request.getParameter("id").isEmpty() ? 0 : Integer.parseInt(request.getParameter("id"));

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = "Doctor";
        String specialty = request.getParameter("specialty");

        int departmentId = Integer.parseInt(request.getParameter("department_id"));

        Doctor doctor = null;

        if (id == 0) {
            try {
                doctor = new Doctor();
                doctor.setFirstName(firstName);
                doctor.setLastName(lastName);
                doctor.setEmail(email);
                doctor.setPassword(password);
                doctor.setRole(role);
                doctor.setSpecialty(specialty);
                Department department;
                try {
                    department = adminService.getDepartmentById(departmentId);
                    doctor.setDepartment(department);
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("errorMessage", e.getMessage());
                    request.setAttribute("doctor", doctor);
                    List<Department> departments = departmentRepo.findAll();
                    request.setAttribute("departments", departments);
                    request.getRequestDispatcher("/doctors/form.jsp").forward(request, response);
                }
                adminService.createDoctor(doctor);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", e.getMessage());
                List<Department> departments = departmentRepo.findAll();
                request.setAttribute("doctor", doctor);
                request.setAttribute("departments", departments);
                request.getRequestDispatcher("/doctors/form.jsp").forward(request, response);
            }
        } else {
            try {
                doctor = adminService.getDoctorById(id);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", e.getMessage());
                List<Doctor> doctors = adminService.getDoctors();
                request.setAttribute("doctors", doctors);
                request.getRequestDispatcher("/doctors/list.jsp").forward(request, response);
            }
            if (doctor != null) {
                try {
                    doctor.setFirstName(firstName);
                    doctor.setLastName(lastName);
                    doctor.setEmail(email);
                    doctor.setPassword(password);
                    doctor.setRole(role);
                    doctor.setSpecialty(specialty);
                    Department department;
                    try {
                        department = adminService.getDepartmentById(departmentId);
                        doctor.setDepartment(department);
                    } catch (Exception e) {
                        e.printStackTrace();
                        request.setAttribute("errorMessage", e.getMessage());
                        request.setAttribute("doctor", doctor);
                        List<Department> departments = departmentRepo.findAll();
                        request.setAttribute("departments", departments);
                        request.getRequestDispatcher("/doctors/form.jsp").forward(request, response);
                    }
                    adminService.updateDoctor(doctor);
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("errorMessage", e.getMessage());
                    request.setAttribute("doctor", doctor);
                    List<Department> departments = departmentRepo.findAll();
                    request.setAttribute("departments", departments);
                    request.getRequestDispatcher("/doctors/form.jsp").forward(request, response);
                }
            }
        }

        response.sendRedirect("doctors");
    }

}
