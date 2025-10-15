package ma.youcode.ehospital.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.ehospital.model.Department;
import ma.youcode.ehospital.model.Room;
import ma.youcode.ehospital.repository.*;
import ma.youcode.ehospital.repository.impl.*;
import ma.youcode.ehospital.service.IAdminService;
import ma.youcode.ehospital.service.impl.AdminServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/departments")
public class DepartmentServlet extends HttpServlet {

    private IDoctorRepository doctorRepo = new DoctorRepositoryImpl();
    private IPatientRepository patientRepo = new PatientRepositoryImpl();
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
                request.setAttribute("department", new Department());
                request.getRequestDispatcher("/departments/form.jsp").forward(request, response);
                break;
            case "edit":
                int id = Integer.parseInt(request.getParameter("id"));
                Department department = adminService.getDepartmentById(id);
                request.setAttribute("department", department);
                request.getRequestDispatcher("/departments/form.jsp").forward(request, response);
                break;
            case "delete":
                try {
                    Department department1 = adminService.getDepartmentById(Integer.parseInt(request.getParameter("id")));
                    adminService.deleteDepartment(department1);
                    response.sendRedirect(request.getContextPath() + "/departments");
                } catch (Exception e) {
                    e.printStackTrace();
                    List<Department> departments = adminService.getDepartments();
                    request.setAttribute("departments", departments);
                    request.setAttribute("errorMessage",
                            "Cannot delete department: it still has assigned doctors.");
                    request.getRequestDispatcher("/departments/list.jsp").forward(request, response);
                }
                break;
            default:
                List<Department> departments = adminService.getDepartments();
                request.setAttribute("departments", departments);
                request.getRequestDispatcher("/departments/list.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = request.getParameter("id") == null || request.getParameter("id").isEmpty()
                ? 0 : Integer.parseInt(request.getParameter("id"));

        String name = request.getParameter("name");

        Department department;

        if (id == 0) {
            try {
                department = new Department();
                department.setName(name);
                adminService.createDepartment(department);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", e.getMessage());
                request.getRequestDispatcher("/departments/form.jsp").forward(request, response);
            }
        } else {
            department = adminService.getDepartmentById(id);
            if (department != null) {
                department.setName(name);
                try {
                    adminService.updateDepartment(department);
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("errorMessage", e.getMessage());
                    request.getRequestDispatcher("/departments/form.jsp").forward(request, response);
                }
            } else {
                System.out.println("Department not found");
            }
        }

        response.sendRedirect("departments");
    }

}
