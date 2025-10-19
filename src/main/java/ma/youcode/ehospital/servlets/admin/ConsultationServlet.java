package ma.youcode.ehospital.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.ehospital.exception.ObjectNotFound;
import ma.youcode.ehospital.model.*;
import ma.youcode.ehospital.repository.*;
import ma.youcode.ehospital.repository.impl.*;
import ma.youcode.ehospital.service.IAdminService;
import ma.youcode.ehospital.service.impl.AdminServiceImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@WebServlet("/consultations")
public class ConsultationServlet extends HttpServlet {

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
                List<Doctor> doctors = adminService.getDoctors();
                List<Patient> patients = adminService.getPatients();
                List<Room> rooms = adminService.getRooms();
                request.setAttribute("statuses", Status.values());
                request.setAttribute("doctors", doctors);
                request.setAttribute("patients", patients);
                request.setAttribute("rooms", rooms);
                request.setAttribute("consultation", new Consultation());
                request.getRequestDispatcher("/consultations/form.jsp").forward(request, response);
                break;
            case "edit":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Consultation consultation = adminService.getConsultationById(id);
                    request.setAttribute("consultation", consultation);
                    getLists(request);
                    request.getRequestDispatcher("/consultations/form.jsp").forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("errorMessage", e.getMessage());
                    backToConsultationsList(request, response, e);
                }
                break;
            case "delete":
                try {
                    Consultation consultation = adminService.getConsultationById(Integer.parseInt(request.getParameter("id")));
                    adminService.deleteConsultation(consultation);
                    response.sendRedirect(request.getContextPath() + "/consultations");
                } catch (Exception e) {
                    e.printStackTrace();
                    backToConsultationsList(request, response, e);
                }
                break;
            default:
                try {
                    List<Consultation> consultations = adminService.getConsultations();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy 'at' HH:mm");

                    List<Map<String, Object>> formattedConsultations = new ArrayList<>();

                    for (Consultation c : consultations) {
                        Map<String, Object> item = new HashMap<>();
                        item.put("id", c.getId());
                        item.put("report", c.getReport());
                        item.put("status", c.getStatus());
                        item.put("patient", c.getPatient());
                        item.put("doctor", c.getDoctor());
                        item.put("room", c.getRoom());
                        item.put("formattedDate", c.getDate().format(formatter));
                        formattedConsultations.add(item);
                    }
                    request.setAttribute("consultations", formattedConsultations);
                    request.getRequestDispatcher("/consultations/list.jsp").forward(request, response);
                } catch (ObjectNotFound e) {
                    request.setAttribute("errorMessage", e.getMessage());
                    request.getRequestDispatcher("/consultations/list.jsp").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("errorMessage", e.getMessage());
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }

                break;
        }
    }

    private void getLists(HttpServletRequest request) {
        List<Doctor> doctorsList = adminService.getDoctors();
        List<Patient> patientsList = adminService.getPatients();
        List<Room>  roomsList = adminService.getRooms();
        request.setAttribute("statuses", Status.values());
        request.setAttribute("doctors", doctorsList);
        request.setAttribute("patients", patientsList);
        request.setAttribute("rooms", roomsList);
    }

    private void backToConsultationsList(HttpServletRequest request, HttpServletResponse response, Exception e) throws ServletException, IOException {
        List<Consultation> consultations = adminService.getConsultations();
        if (!consultations.isEmpty()) {
            request.setAttribute("consultations", consultations);
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/consultations/list.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = request.getParameter("id") == null || request.getParameter("id").isEmpty() ? 0 : Integer.parseInt(request.getParameter("id"));

        String date = request.getParameter("date");
        String report = request.getParameter("report");
        String status = request.getParameter("status");
        Doctor doctor = adminService.getDoctorById(Integer.parseInt(request.getParameter("doctor_id")));
        Patient patient = adminService.getPatientById(Integer.parseInt(request.getParameter("patient_id")));
        Room room = adminService.getRoomById(Integer.parseInt(request.getParameter("room_id")));


        Consultation consultation;
        if (id == 0) {
            consultation = new Consultation();
            consultation.setDate(LocalDateTime.parse(date));
            consultation.setReport(report);
            consultation.setStatus(Status.valueOf(status));
            consultation.setDoctor(doctor);
            consultation.setRoom(room);
            consultation.setPatient(patient);
            try {
                adminService.createConsultation(consultation);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", e.getMessage());
                getLists(request);
                request.setAttribute("consultation", consultation);
                request.getRequestDispatcher("/consultations/form.jsp").forward(request, response);
            }
        } else {
            try {
                consultation = adminService.getConsultationById(id);
                consultation.setDate(LocalDateTime.parse(date));
                consultation.setReport(report);
                consultation.setStatus(Status.valueOf(status));
                consultation.setDoctor(doctor);
                consultation.setPatient(patient);
                consultation.setRoom(room);
                try {
                    adminService.updateConsultation(consultation);
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("errorMessage", e.getMessage());
                    getLists(request);
                    request.setAttribute("consultation", consultation);
                    request.getRequestDispatcher("/consultations/form.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", e.getMessage());
                request.getRequestDispatcher("/consultations/list.jsp").forward(request, response);
            }
        }
        response.sendRedirect("consultations");
    }

}
