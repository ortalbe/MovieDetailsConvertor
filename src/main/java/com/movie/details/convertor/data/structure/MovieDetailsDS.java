package com.movie.details.convertor.data.structure;

import java.util.Vector;

/**
 * MovieDetailsDS - contain csv table represnt movies data
 */
public class MovieDetailsDS {

    private  volatile Vector<String[]> movieDetailsList;


    public MovieDetailsDS() {
        this.movieDetailsList = new Vector<String[]>();
    }

    public void add(String [] input)
    {
        movieDetailsList.add(input);
    }

    public int getSize()
    {
        return movieDetailsList.size();
    }


    public String [] getValue(int index)
    {
        return movieDetailsList.get(index);
    }


    
}
