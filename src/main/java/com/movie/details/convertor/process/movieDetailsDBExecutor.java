package com.movie.details.convertor.process;

import javafx.util.Pair;
import org.apache.log4j.Logger;
import com.movie.details.convertor.exceptions.SaveDBExceptin;
import com.movie.details.convertor.business.object.MovieDetailBO;
import com.movie.details.convertor.controlers.MovieDetailsControler;
import com.movie.details.convertor.data.access.object.MovieDetailsDAO;
import com.movie.details.convertor.data.structure.MovieDetailsDS;
import org.hibernate.Session;

/**
 * movieDetailsDBExecutor - multi thread class.
 * will save entries one by one to the table MOVIE_DETAILS.
 */
public class movieDetailsDBExecutor implements Runnable {


    private MovieDetailsDS movieDetailsdataStructure;
    private static Logger LOG = Logger.getLogger(movieDetailsDBExecutor.class);
    private static final String CLASS_NAME = "movieDetailsDBExecutor";
    private Pair<Integer,Integer> indexPairs;
    private MovieDetailsDBMonitor tableThreadMonitor;
    private Session session;
    private static final int TITLE_INDEX=0;
    
    public movieDetailsDBExecutor(MovieDetailsDS movieDetailsdataStructure,MovieDetailsDBMonitor tableThreadMonitor, Pair<Integer,Integer> indexPairs) {

        this.movieDetailsdataStructure=movieDetailsdataStructure;
        this.indexPairs = indexPairs;
        this.tableThreadMonitor=tableThreadMonitor;

    }

    public void run() {

        String methodName = "::run ";

        tableThreadMonitor.waitForTableToBeCreated();
        if(!tableThreadMonitor.getThreadFinishedToCreateTable())
        {
            LOG.info(CLASS_NAME + methodName + "Thread "  + Thread.currentThread().getId() + " will create the table." );
            String[] movieDetails = movieDetailsdataStructure.getValue(indexPairs.getKey());
            indexPairs= new Pair<Integer, Integer>(indexPairs.getKey()+1,indexPairs.getValue());
            cretaeEntryToDB(movieDetails);
            tableThreadMonitor.tableCreated();
            LOG.info(CLASS_NAME + methodName + "Thread "  + Thread.currentThread().getId() + " created table and update status.");

        }

        for(int index=indexPairs.getKey();index<=indexPairs.getValue();index++) {

            String[] movieDetails = movieDetailsdataStructure.getValue(index);

            LOG.info(CLASS_NAME + methodName + "Thread ID " + Thread.currentThread().getId() + " start saving movie: " + movieDetails[TITLE_INDEX]);

            cretaeEntryToDB(movieDetails);

            LOG.info(CLASS_NAME + methodName + "Thread ID " + Thread.currentThread().getId() + " finished saving movie: " + movieDetails[TITLE_INDEX]);
        }

        LOG.info(CLASS_NAME + methodName + " thread " + Thread.currentThread().getId() + " finished");

    }

    public void cretaeEntryToDB(String[] movieDetails)
    {
        String methodName = "::cretaeEntryToDB ";
        MovieDetailsControler movieDetailsControler=new MovieDetailsControler();
        
        try {
            MovieDetailBO movieDetail = new MovieDetailBO(movieDetails);
            MovieDetailsDAO movieDetailsDAO = new MovieDetailsDAO();
            movieDetailsControler.setMovieDetailsBO(movieDetail);
            movieDetailsControler.setMovieDetailsDAO(movieDetailsDAO);
            movieDetailsControler.crateDetailMovieBO();
        } 
        catch (Exception exeption) {

            LOG.error(CLASS_NAME + methodName + " thread " + Thread.currentThread().getId() + " having an exception while saving movie: " + movieDetails[TITLE_INDEX] +"\n" + exeption);

            throw new SaveDBExceptin(CLASS_NAME + methodName + "SaveDBExceptin" + exeption.getMessage());
        }

    }


}