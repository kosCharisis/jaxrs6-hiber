package gr.aueb.cf.schoolapp.service.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAHelper {
    private static EntityManagerFactory emf;
    private static ThreadLocal<EntityManager> threadLocal = new ThreadLocal<>();

    private JPAHelper() {

    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null || !emf.isOpen()) {
            emf = Persistence.createEntityManagerFactory("school6PU");
        }
        return  emf;
    }

    public static EntityManager getEntitymanager() {
        EntityManager em = threadLocal.get();
        if (em == null || !em.isOpen()) {
            em = getEntityManagerFactory().createEntityManager();
            threadLocal.set(em);
        }

        return em;
    }

    public static void closeEntityManager() {
        getEntitymanager().close();
    }

    public static void beginTransaction()  {
        getEntitymanager().getTransaction().begin();
    }

    public static void commitTransaction() {
        getEntitymanager().getTransaction().commit();
    }

    public static void rollbackTransaction() {
        getEntitymanager().getTransaction().rollback();
    }

    public static void closeEMF() {
        emf.close();
    }
}
