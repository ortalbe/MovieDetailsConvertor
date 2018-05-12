package com.movie.details.convertor.process;

import javafx.util.Pair;
import org.apache.log4j.Logger;
import com.movie.details.convertor.exceptions.SaveDBExceptin;
import com.movie.details.convertor.business.object.MovieDetailBO;
import com.movie.details.convertor.controlers.MovieDetailsControler;
import com.movie.details.convertor.data.access.object.MovieDetailsDAO;
import com.movie.details.convertor.data.structure.MovieDetailsDS;

/**
 * movieDetailsDBExecutor - multi thread class.
 * will save entries one by one to the table MOVIE_DETAILS.
 */
public class movieDetailsDBExecutor implements Runnable {


    private MovieDetailsDS movieDetailsdataStructure;
    private static Logger LOG = Logger.getLogger(movieDetailsDBExecutor.class);
    private static final String CLASS_NAME = "MovieDetailCSVToDB";
    private Pair<Integer,Integer> indexPairs;
    private MovieDetailsDBMonitor tableThreadMonitor;
    private MovieDetailsDAO movieDetailsDAO ;
    
    public movieDetailsDBExecutor(MovieDetailsDS movieDetailsdataStructure,MovieDetailsDBMonitor tableThreadMonitor, Pair<Integer,Integer> indexPairs) {

        this.movieDetailsdataStructure=movieDetailsdataStructure;
        this.indexPairs = indexPairs;
        this.tableThreadMonitor=tableThreadMonitor;
        movieDetailsDAO = new MovieDetailsDAO();
    }

    public void run() {

        String methodName = "::run ";

        tableThreadMonitor.waitForTableToBeCreated();
        if(!tableThreadMonitor.getThreadFinishedToCreateTable())
        {
            LOG.info(CLASS_NAME + methodName + "Thread "  + Thread.currentThread().getName() + "will create the table" + tableThreadMonitor.toString());
            String[] movieDetails = movieDetailsdataStructure.getValue(indexPairs.getKey());
            indexPairs= new Pair<Integer, Integer>(indexPairs.getKey()+1,indexPairs.getValue());
            cretaeEntryToDB(movieDetails);
            tableThreadMonitor.tableCreated();
            LOG.info(CLASS_NAME + methodName + "Thread "  + Thread.currentThread().getName() + "created table and update status"
            + "ThreadCalledToCreateTable - " + tableThreadMonitor.getThreadCalledToCreateTable()
                    + "FinishedToCreateTable - " + tableThreadMonitor.getThreadFinishedToCreateTable());

        }
        for(int index=indexPairs.getKey();index<indexPairs.getValue();index++) {

            LOG.info(CLASS_NAME + methodName + "invoked. Thread ID " + Thread.currentThread().getId() + " index=" + index);

            String[] movieDetails = movieDetailsdataStructure.getValue(index);

            cretaeEntryToDB(movieDetails);

            LOG.info(CLASS_NAME + methodName + " save '" + movieDetails[0] + "' to the data base under MOVIE_DETAILS table.");
        }

        LOG.info(CLASS_NAME + methodName + " thread " + Thread.currentThread().getName() + " finished");

    }

    public void cretaeEntryToDB(String[] movieDetails)
    {
        String methodName = "cretaeEntryToDB";
        MovieDetailsControler movieDetailsControler=new MovieDetailsControler();
        
        try {
            MovieDetailBO movieDetail = new MovieDetailBO(movieDetails);
            movieDetailsControler.setMovieDetailsBO(movieDetail);
            movieDetailsControler.setMovieDetailsDAO(movieDetailsDAO);
            movieDetailsControler.crateDetailMovieBO();
        } 
        catch (Exception exeption) {
            throw new SaveDBExceptin(CLASS_NAME + methodName + "SaveDBExceptin" + exeption.getMessage());
        }

    }


}