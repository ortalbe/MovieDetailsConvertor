package main.java.com.movie.details.convertor.data.access.object;
import main.java.com.movie.details.convertor.business.object.MovieDetailBO;
import main.java.com.movie.details.convertor.utils.ErrorCode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.apache.log4j.Logger;
import org.hibernate.cfg.Configuration;

/**
 * Created by Ortal on 5/5/2018.
 */
public class MovieDetailsDAO  {


    protected SessionFactory sessionFactory;
    protected Session session;
    public final static Logger LOG = Logger.getLogger(MovieDetailsDAO.class);
    public final static String CLASS_NAME = "MovieDetailsDAO";

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public ErrorCode openSession() {

        String method ="::openSession ";
        try {
            if(session==null && sessionFactory==null)
            {
                LOG.info(CLASS_NAME + method + " session factory not initialized yet. intialize session");
                sessionFactory =  new Configuration().configure().buildSessionFactory();
            }
            if(session==null && sessionFactory!=null)
            {
                LOG.info(CLASS_NAME + method + " session not initialized yet. intialize session");
                session=sessionFactory.openSession();
            }
        }
        catch (Exception exception)
        {
           LOG.error(CLASS_NAME + method + " and error occured while opening session",exception);
           return ErrorCode.ERROR;
        }

        return ErrorCode.SUCCESS;
    }

    public ErrorCode save(MovieDetailBO movieDetailBO) {

        String method = "save";
        try {
            openSession();
            session.beginTransaction();
            session.save(movieDetailBO);
            session.getTransaction().commit();
        }
        catch (Exception exception)
        {
            LOG.error(CLASS_NAME + method + " and error occured while opening session",exception);
            return ErrorCode.ERROR;
        }

        return ErrorCode.SUCCESS;

    }

    public ErrorCode update(MovieDetailBO movieDetailBO) {
        String method = "update";

        try {
            openSession();
            session.beginTransaction();
            session.update(movieDetailBO);
            session.getTransaction().commit();
        }
        catch (Exception exception)
        {
            LOG.error(CLASS_NAME + method + " and error occured while opening session",exception);
            return ErrorCode.ERROR;
        }

        return ErrorCode.SUCCESS;
    }

    public ErrorCode delete(MovieDetailBO movieDetailBO) {
        String method = "delete";
        try {
            openSession();
            session.beginTransaction();
            session.delete(movieDetailBO);
            session.getTransaction().commit();
        }
        catch (Exception exception)
        {
            LOG.error(CLASS_NAME + method + " and error occured while opening session",exception);
            return ErrorCode.ERROR;
        }

        return ErrorCode.SUCCESS;
    }



    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        session.close();
        sessionFactory.close();
    }
}
