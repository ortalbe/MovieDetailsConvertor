package com.movie.details.convertor.process;


import com.movie.details.convertor.process.movieDetailsDBExecutor;
import javafx.util.Pair;
import com.movie.details.convertor.business.object.MovieDetailBO;
import com.movie.details.convertor.controlers.MovieDetailsControler;
import com.movie.details.convertor.data.access.object.MovieDetailsDAO;
import com.movie.details.convertor.data.structure.MovieDetailsDS;
import com.movie.details.convertor.utils.ErrorCode;
import com.opencsv.CSVReader;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * MovieDetailCSVToDB - convert cvs file to a table in the DB MOVIE_DETAILS.
 */

public class MovieDetailCSVToDB {

    private String csvFile ;
    private static Logger LOG = Logger.getLogger(MovieDetailCSVToDB.class);
    private static final String CLASS_NAME = "MovieDetailCSVToDB";
    private MovieDetailsDS movieDetailsDataStructure;
    private int numberOfThreads;
    private MovieDetailsDBMonitor tableThreadMonitor;
    private static final int SUCCESS=0;

    public MovieDetailCSVToDB(String csvFile, String numberOfThreads) {
        this.csvFile = csvFile;
        this.numberOfThreads = Integer.parseInt(numberOfThreads);
    }

    public ErrorCode process( )
    {
        String methodName = "::process ";
        String line = "";
        String cvsSplitBy = ",";
        CSVReader reader = null;
        movieDetailsDataStructure = new MovieDetailsDS();

        try {
            long startTime = System.nanoTime();

            reader = new CSVReader(new FileReader(csvFile));
            String[] movieDetails;
            movieDetails = reader.readNext();

            while ((movieDetails = reader.readNext()) != null) {
                movieDetailsDataStructure.add(movieDetails);
            }


            ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
            HashMap<Integer,Pair<Integer,Integer>> startAndEndIndex = mapDetailsMovieDS();
            tableThreadMonitor = new MovieDetailsDBMonitor();

            LOG.info(CLASS_NAME + methodName + "populate MOVIE_DETAILS using " + numberOfThreads + " threads");

            for (int i = 0; i < numberOfThreads; i++) {
                Runnable movieDetail = new movieDetailsDBExecutor(movieDetailsDataStructure,tableThreadMonitor,startAndEndIndex.get((int)i));
                executor.execute(movieDetail);
            }

            executor.shutdown();

            try {
                if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException ex) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }


            long endTime   = System.nanoTime();
            long totalTime = (endTime - startTime)/1000000000;
            LOG.info(CLASS_NAME + methodName + " total process time: " + totalTime);
            System.exit(SUCCESS);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ErrorCode.SUCCESS;
    }

    private HashMap<Integer,Pair<Integer,Integer>> mapDetailsMovieDS() {

        int size = movieDetailsDataStructure.getSize();
        int interval = size/numberOfThreads;
        int leftover = size - interval*numberOfThreads;
        HashMap<Integer,Pair<Integer,Integer>> indexMapping = new HashMap<Integer,Pair<Integer,Integer>>();

        for(int i=0;i<numberOfThreads-1;i++)
            indexMapping.put(i,new Pair<Integer, Integer>(interval*i,interval*(i+1)-1));

        indexMapping.put(numberOfThreads-1,new Pair<Integer, Integer>(interval*(numberOfThreads-1),size-1));

        return indexMapping;
    }



}
