package main.java.com.movie.details.convertor.controlers;

import main.java.com.movie.details.convertor.business.object.MovieDetailBO;
import main.java.com.movie.details.convertor.data.access.object.MovieDetailsDAO;
import main.java.com.movie.details.convertor.utils.ErrorCode;

/**
 * Created by Ortal on 5/6/2018.
 */
public class MovieDetailsControler {

    private MovieDetailBO movieDetailsBO;
    private MovieDetailsDAO movieDetailsDAO;

    public MovieDetailBO getMovieDetailsBO() {
        return movieDetailsBO;
    }

    public void setMovieDetailsBO(MovieDetailBO movieDetailsBO) {
        this.movieDetailsBO = movieDetailsBO;
    }

    public MovieDetailsDAO getMovieDetailsDAO() {
        return movieDetailsDAO;
    }

    public void setMovieDetailsDAO(MovieDetailsDAO movieDetailsDAO) {
        this.movieDetailsDAO = movieDetailsDAO;
    }

    public synchronized ErrorCode crateDetailMovieBO()
    {
        ErrorCode status = movieDetailsDAO.save(movieDetailsBO);
        return status;
    }
}

