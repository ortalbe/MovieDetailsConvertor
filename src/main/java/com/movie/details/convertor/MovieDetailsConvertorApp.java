package main.java.com.movie.details.convertor;

import main.java.com.movie.details.convertor.process.MovieDetailCSVToDB;

/**
 * MovieDetailsConvertorApp will fetch movie info from IMDB or csv file and insert it to the data base.
 */
public class MovieDetailsConvertorApp {

    public static void main(String[] args)
    {
        MovieDetailCSVToDB process = new MovieDetailCSVToDB();

        process.process();

    }
}
