package com.movie.details.convertor.controlers;

import com.movie.details.convertor.business.object.MovieDetailBO;
import com.movie.details.convertor.data.access.object.MovieDetailsDAO;
import com.movie.details.convertor.utils.ErrorCode;

/**
 * MovieDetailsControler - will create a new entry to MOVIE_DETAILS.
 * we will use to object :
 * 1. Business Object - a POJO for the entry.
 * 2. Data access object - contain all operation recognized for the table.
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

