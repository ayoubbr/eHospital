package ma.youcode.ehospital.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static final String PERSISTENCE_UNIT_NAME = "myPersistenceUnit";

    private static EntityManagerFactory emf;

    // Static block guarantees the EntityManagerFactory is initialized when the class is loaded
    static {
        try {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

            // Register a shutdown hook to close the factory when the JVM exits
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                if (emf != null && emf.isOpen()) {
                    System.out.println("Shutting down EntityManagerFactory...");
                    emf.close();
                }
            }));
        } catch (Exception e) {
            System.err.println("Error initializing EntityManagerFactory: " + e.getMessage());
            e.printStackTrace();
            throw new ExceptionInInitializerError("Could not initialize JPA EntityManagerFactory.");
        }
    }

    /**
     * Retrieves the Singleton EntityManagerFactory.
     * @return The EntityManagerFactory instance.
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null || !emf.isOpen()) {
            throw new IllegalStateException("EntityManagerFactory is not initialized or has been closed.");
        }
        return emf;
    }

    /**
     * Creates and returns a new EntityManager instance (lightweight resource).
     * @return A new EntityManager.
     */
    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }
}

