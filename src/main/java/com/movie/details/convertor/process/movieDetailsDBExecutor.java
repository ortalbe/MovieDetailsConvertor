package main.java.com.movie.details.convertor.process;

import javafx.util.Pair;
import main.java.com.movie.details.convertor.business.object.MovieDetailBO;
import main.java.com.movie.details.convertor.controlers.MovieDetailsControler;
import main.java.com.movie.details.convertor.data.access.object.MovieDetailsDAO;
import main.java.com.movie.details.convertor.data.structure.MovieDetailsDS;
import main.java.com.movie.details.convertor.exceptions.SaveDBExceptin;
import org.apache.log4j.Logger;

/**
 * Created by Ortal on 5/7/2018.
 */
public class movieDetailsDBExecutor implements Runnable {


    private MovieDetailsDS movieDetailsdataStructure;
    private static Logger LOG = Logger.getLogger(MovieDetailCSVToDB.class);
    private static final String CLASS_NAME = "MovieDetailCSVToDB";
    private Pair<Integer,Integer> indexPairs;

    public movieDetailsDBExecutor(MovieDetailsDS movieDetailsdataStructure, Pair<Integer,Integer> indexPairs) {

        this.movieDetailsdataStructure=movieDetailsdataStructure;
        this.indexPairs = indexPairs;
    }

    public void run() {

        String methodName = "::run ";


        MovieDetailsControler movieDetailsControler=new MovieDetailsControler();
        MovieDetailsDAO movieDetailsDAO = new MovieDetailsDAO() ;

        for(int index=indexPairs.getKey();index<indexPairs.getValue();index++) {
            //int index = movieDetailsdataStructure.updateCurrentIndex();
            LOG.info(CLASS_NAME + methodName + "invoked. Thread ID " + Thread.currentThread().getId() + " index=" + index);

            String[] movieDetails = movieDetailsdataStructure.getValue(index);

            try {
                MovieDetailBO movieDetail = new MovieDetailBO(movieDetails);
                movieDetailsControler.setMovieDetailsBO(movieDetail);
                movieDetailsControler.setMovieDetailsDAO(movieDetailsDAO);
                movieDetailsControler.crateDetailMovieBO();
            } catch (Exception exeption) {
                throw new SaveDBExceptin(CLASS_NAME + methodName + "SaveDBExceptin" + exeption.getMessage());
            }

            LOG.info(CLASS_NAME + methodName + " save '" + movieDetails[0] + "' to the data base under MOVIE_DETAILS table.");
        }




    }
}
