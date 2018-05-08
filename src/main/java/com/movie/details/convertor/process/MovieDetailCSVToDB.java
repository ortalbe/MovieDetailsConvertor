package main.java.com.movie.details.convertor.process;


import main.java.com.movie.details.convertor.data.structure.MovieDetailsDS;
import main.java.com.movie.details.convertor.utils.ErrorCode;
import com.opencsv.CSVReader;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Ortal on 5/5/2018.
 */
public class MovieDetailCSVToDB {

    private static final String csvFile = "current-Movie-Data.csv";
    private static Logger LOG = Logger.getLogger(MovieDetailCSVToDB.class);
    private static final String CLASS_NAME = "MovieDetailCSVToDB";
    private MovieDetailsDS movieDetailsDataStructure;
    private static final int NUMBER_OF_THREADS=5;

    public ErrorCode process()
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
            //MovieDetailsControler movieDetailsControler=new MovieDetailsControler();
            //MovieDetailsDAO movieDetailsDAO = new MovieDetailsDAO() ;

            while ((movieDetails = reader.readNext()) != null) {
                LOG.info(CLASS_NAME + methodName + " save '" + movieDetails[0] + "' to the data base under MOVIE_DETAILS table.");
                movieDetailsDataStructure.add(movieDetails);
                //MovieDetailBO movieDetail= new MovieDetailBO(movieDetails);
                //movieDetailsControler.setMovieDetailsBO(movieDetail);
                //movieDetailsControler.setMovieDetailsDAO(movieDetailsDAO);
               // movieDetailsControler.crateDetailMovieBO();
            }



            ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
            for (int i = 0; i < movieDetailsDataStructure.getSize(); i++) {
                Runnable movieDetail = new movieDetailsDBExecutor(movieDetailsDataStructure);
                executor.execute(movieDetail);
            }

            executor.shutdown();

            while (!executor.isTerminated()) {
            }

            long endTime   = System.nanoTime();
            long totalTime = (endTime - startTime)/1000000000;
            LOG.info(CLASS_NAME + methodName + " total process time: " + totalTime);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ErrorCode.SUCCESS;
    }

}
