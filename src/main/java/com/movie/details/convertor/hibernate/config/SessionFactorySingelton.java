package com.movie.details.convertor.hibernate.config;

import com.movie.details.convertor.data.access.object.MovieDetailsDAO;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Ortal on 5/13/2018.
 */
public class SessionFactorySingelton {

    private static SessionFactorySingelton sessionFactoryInstance = null;
    private SessionFactory sessionFactory = null;
    private final static String CLASS_NAME = "SessionFactorySingelton" ;
    private final static Logger LOG = Logger.getLogger(MovieDetailsDAO.class);

    private  SessionFactorySingelton() {
        String methodName = "::SessionFactorySingelton ";
        LOG.info(CLASS_NAME + methodName + " session factory not initialized yet. initialize session");
        sessionFactory =  new Configuration().configure().buildSessionFactory();
           }

    public synchronized static SessionFactorySingelton getInstance ()
    {
        if (sessionFactoryInstance ==null)
            sessionFactoryInstance = new SessionFactorySingelton();

        return sessionFactoryInstance;

    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }


}
