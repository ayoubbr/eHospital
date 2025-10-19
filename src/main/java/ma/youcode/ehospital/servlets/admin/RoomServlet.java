package ma.youcode.ehospital.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.ehospital.model.Room;
import ma.youcode.ehospital.repository.*;
import ma.youcode.ehospital.repository.impl.*;
import ma.youcode.ehospital.service.IAdminService;
import ma.youcode.ehospital.service.impl.AdminServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/rooms")
public class RoomServlet extends HttpServlet {

    private IDoctorRepository doctorRepo = new DoctorRepositoryImpl();
    private IConsultationRepository consultationRepository = new ConsultationRepositoryImpl();
    private IDepartmentRepository departmentRepo = new DepartmentRepositoryImpl();
    private IRoomRepository roomRepo = new RoomRepositoryImpl();

    private IPatientRepository patientRepo = new PatientRepositoryImpl();

    private IAdminService adminService = new AdminServiceImpl(doctorRepo, consultationRepository,
            departmentRepo, roomRepo, patientRepo);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "new":
                request.setAttribute("room", new Room());
                request.getRequestDispatcher("/rooms/form.jsp").forward(request, response);
                break;
            case "edit":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Room room = adminService.getRoomById(id);
                    request.setAttribute("room", room);
                    request.getRequestDispatcher("/rooms/form.jsp").forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("errorMessage", e.getMessage());
                    List<Room> rooms = adminService.getRooms();
                    request.setAttribute("rooms", rooms);
                    request.getRequestDispatcher("/rooms/list.jsp").forward(request, response);
                }
                break;
            case "delete":
                try {
                    Room room1 = adminService.getRoomById(Integer.parseInt(request.getParameter("id")));
                    adminService.deleteRoom(room1);
                    response.sendRedirect(request.getContextPath() + "/rooms");
                } catch (Exception e) {
                    e.printStackTrace();
                    List<Room> rooms = adminService.getRooms();
                    request.setAttribute("rooms", rooms);
                    request.setAttribute("errorMessage",
                            "You are trying to delete a room that has consultations.");
                    request.getRequestDispatcher("/rooms/list.jsp").forward(request, response);
                }
                break;
            default:
                List<Room> rooms = adminService.getRooms();
                request.setAttribute("rooms", rooms);
                request.getRequestDispatcher("/rooms/list.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = request.getParameter("id") == null || request.getParameter("id").isEmpty()
                ? 0 : Integer.parseInt(request.getParameter("id"));

        String name = request.getParameter("name");
        int capacity = Integer.parseInt(request.getParameter("capacity"));

        Room room;

        if (id == 0) {
            try {
                room = new Room();
                room.setName(name);
                room.setCapacity(capacity);
                adminService.createRoom(room);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", e.getMessage());
                request.getRequestDispatcher("/rooms/form.jsp").forward(request, response);
            }
        } else {
            room = adminService.getRoomById(id);
            if (room != null) {
                room.setName(name);
                room.setCapacity(capacity);
                try {
                    adminService.updateRoom(room);
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("errorMessage", e.getMessage());
                    request.setAttribute("room", room);
                    request.getRequestDispatcher("/rooms/form.jsp").forward(request, response);
                }
            }
        }

        response.sendRedirect("rooms");
    }

}
