package database;

/**
 * Created by pavel on 24.11.15.
 */

/*
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.io.File;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            //return new Configuration().configure(new File("../../resources/hibernate.cfg.xml")).buildSessionFactory();
            return new Configuration().configure(new File("hibernate.cfg.xml")).buildSessionFactory();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
*/



import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();

        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}

/*

        import org.hibernate.HibernateException;
        import org.hibernate.Query;
        import org.hibernate.Session;
        import org.hibernate.SessionFactory;
        import org.hibernate.boot.MetadataSources;
        import org.hibernate.boot.registry.StandardServiceRegistry;
        import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

        import java.util.Objects;

/**
 * Created by ivan on 20.11.15.
 */
/*
public class ProjectDB {
    private static final Logger LOGGER = LogManager.getLogger(ProjectDB.class);
    private static SessionFactory s_sesssionFactory;
    private static String s_currentBD;
    public static void initBD(){
        initBD("");
    }
    public static void initBD(String source){
        if(s_sesssionFactory == null){
            StandardServiceRegistry serviceRegistry;
            if(source.isEmpty()) {
                s_currentBD="production";
                serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
            }else{
                s_currentBD="test";
                serviceRegistry = new StandardServiceRegistryBuilder().configure(source).build();
            }
            s_sesssionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
        }
    }
    public static void truncateTables(){
        if(s_sesssionFactory != null) {
            if (Objects.equals(s_currentBD, "test")) {
                try (Session session = s_sesssionFactory.openSession()) {
                    session.beginTransaction();
                    Query query = session.createSQLQuery(" SET FOREIGN_KEY_CHECKS=0");
                    query.executeUpdate();
                    query = session.createSQLQuery("TRUNCATE TABLE room");
                    query.executeUpdate();
                    query = session.createSQLQuery("TRUNCATE TABLE player");
                    query.executeUpdate();
                    query = session.createSQLQuery("TRUNCATE TABLE user");
                    query.executeUpdate();
                    query = session.createSQLQuery(" SET FOREIGN_KEY_CHECKS=1");
                    query.executeUpdate();
                    session.getTransaction().commit();
                }catch (HibernateException exc){
                    LOGGER.error("Truncate failed",exc);
                }
            }
        }else{
            Marker marker = new MarkerManager.Log4jMarker("FAIL INIT");
            LOGGER.error(marker,"db failed");
        }
    }

    public static SessionFactory getSessionFactory(){
        if(s_sesssionFactory == null){
            initBD();
        }
        return s_sesssionFactory;
    }

}*/