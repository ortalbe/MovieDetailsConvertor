package com.movie.details.convertor;

import com.movie.details.convertor.process.MovieDetailCSVToDB;
import com.movie.details.convertor.process.movieDetailsDBExecutor;
import org.apache.log4j.Logger;

/**
 * MovieDetailsConvertorApp will fetch movie info from IMDB or csv file and insert it to the data base.
 */
public class MovieDetailsConvertorApp {

    private static Logger LOG = Logger.getLogger(MovieDetailsConvertorApp.class);

    public static void main(String[] args)
    {
        if(args.length!=2)
        {
            throw new IllegalArgumentException("The number of argument send is not as expected");
        }

        MovieDetailCSVToDB process = new MovieDetailCSVToDB(args[0],args[1]);

        process.process();
        
    }
}
