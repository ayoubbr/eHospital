package ma.youcode.ehospital.servlets;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ma.youcode.ehospital.model.Admin;
import ma.youcode.ehospital.repository.*;
import ma.youcode.ehospital.repository.impl.*;
import ma.youcode.ehospital.service.IAdminService;
import ma.youcode.ehospital.service.impl.AdminServiceImpl;

@WebListener
public class AppStartupListener implements ServletContextListener {

    private IDoctorRepository doctorRepository;
    private IConsultationRepository consultationRepository;
    private IDepartmentRepository departmentRepository;
    private IRoomRepository roomRepository;
    private IPatientRepository patientRepository;
    private IAdminRepository adminRepository;

    public AppStartupListener() {
        this.doctorRepository = new DoctorRepositoryImpl();
        this.consultationRepository = new ConsultationRepositoryImpl();
        this.departmentRepository = new DepartmentRepositoryImpl();
        this.roomRepository = new RoomRepositoryImpl();
        this.patientRepository = new PatientRepositoryImpl();
        this.adminRepository = new AdminRepositoryImpl();
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        IAdminService adminService = new AdminServiceImpl(
                doctorRepository,
                consultationRepository,
                departmentRepository,
                roomRepository,
                patientRepository,
                adminRepository
        );

        if (!adminService.hasAnyAdmin()) {
            Admin admin = new Admin();
            admin.setFirstName("Admin");
            admin.setLastName("Admin");
            admin.setEmail("admin@ehospital.com");
            admin.setPassword("admin");
            admin.setRole("ADMIN");

            adminService.save(admin);
            System.out.println("✅ Default admin created: admin@ehospital.com / admin");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // nothing needed here
    }
}
