package main.java.com.movie.details.convertor.process;

import main.java.com.movie.details.convertor.business.object.MovieDetailBO;
import main.java.com.movie.details.convertor.controlers.MovieDetailsControler;
import main.java.com.movie.details.convertor.data.access.object.MovieDetailsDAO;
import main.java.com.movie.details.convertor.data.structure.MovieDetailsDS;
import org.apache.log4j.Logger;

/**
 * Created by Ortal on 5/7/2018.
 */
public class movieDetailsDBExecutor implements Runnable {


    private MovieDetailsDS movieDetailsdataStructure;
    private static Logger LOG = Logger.getLogger(MovieDetailCSVToDB.class);
    private static final String CLASS_NAME = "MovieDetailCSVToDB";

    public movieDetailsDBExecutor(MovieDetailsDS movieDetailsdataStructure) {

        this.movieDetailsdataStructure=movieDetailsdataStructure;
    }

    public void run() {

        String methodName = "::run ";


        MovieDetailsControler movieDetailsControler=new MovieDetailsControler();
        MovieDetailsDAO movieDetailsDAO = new MovieDetailsDAO() ;

        int index = movieDetailsdataStructure.updateCurrentIndex();
        LOG.info(CLASS_NAME + methodName + "invoked. Thread ID " + Thread.currentThread().getId() + " index=" + index);

        String [] movieDetails = movieDetailsdataStructure.getValue(index);

        MovieDetailBO movieDetail= new MovieDetailBO(movieDetails);
        movieDetailsControler.setMovieDetailsBO(movieDetail);
        movieDetailsControler.setMovieDetailsDAO(movieDetailsDAO);
        movieDetailsControler.crateDetailMovieBO();


    }
}
