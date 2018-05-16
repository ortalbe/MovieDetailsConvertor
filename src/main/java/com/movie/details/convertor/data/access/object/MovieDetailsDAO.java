package com.movie.details.convertor.data.access.object;
import com.movie.details.convertor.business.object.MovieDetailBO;
import com.movie.details.convertor.hibernate.config.SessionFactorySingelton;
import com.movie.details.convertor.utils.ErrorCode;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 * MovieDetailsDAO - control all table operation for MOVIE_DETAILS table.
 */
public class MovieDetailsDAO implements DataAccessObjectInterface<MovieDetailBO> {


    protected SessionFactorySingelton sessionFactory;
    public final static Logger LOG = Logger.getLogger(MovieDetailsDAO.class);
    public final static String CLASS_NAME = "MovieDetailsDAO";
    private Session session;

    public MovieDetailsDAO() {
        openSingeltonSessionFactory();
    }

    public ErrorCode openSingeltonSessionFactory() {

        String method ="::openSingeltonSessionFactory ";
        try
        {
            LOG.info(CLASS_NAME + method + " open session factory.");
            sessionFactory = SessionFactorySingelton.getInstance();

        }
        catch (Exception exception)
        {
           LOG.error(CLASS_NAME + method + " and error occurred while opening session",exception);
           return ErrorCode.ERROR;
        }

        return ErrorCode.SUCCESS;
    }

    public ErrorCode save(MovieDetailBO movieDetailBO) {

        session = sessionFactory.getSessionFactory().openSession();

        session.beginTransaction();
        session.save(movieDetailBO);
        session.getTransaction().commit();
        session.close();

        return ErrorCode.SUCCESS;

    }

    public ErrorCode update(MovieDetailBO movieDetailBO) {

        session = sessionFactory.getSessionFactory().openSession();

        session.beginTransaction();
        session.update(movieDetailBO);
        session.getTransaction().commit();
        session.close();

        return ErrorCode.SUCCESS;
    }

    public ErrorCode delete(MovieDetailBO movieDetailBO) {

        session = sessionFactory.getSessionFactory().openSession();

        session.beginTransaction();
        session.delete(movieDetailBO);
        session.getTransaction().commit();
        session.close();

        return ErrorCode.SUCCESS;
    }

    @Override
    public MovieDetailBO get(String className, String id) {

        session = sessionFactory.getSessionFactory().openSession();
        MovieDetailBO movieDetailBO= (MovieDetailBO) session.get(className,id);
        session.close();
        return movieDetailBO;
    }


    public SessionFactorySingelton getSessionFactorySingelton() {
        return sessionFactory;
    }



}
