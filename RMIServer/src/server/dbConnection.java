package server;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class dbConnection {
	private SessionFactory factory;

	dbConnection(){try {
        factory = new Configuration().configure().buildSessionFactory();
     } catch (Throwable ex) { 
        System.err.println("Failed to create sessionFactory object." + ex);
        throw new ExceptionInInitializerError(ex); 
     }
	}

	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
}
