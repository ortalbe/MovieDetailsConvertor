package main.java.com.movie.details.convertor.data.structure;

import java.util.Vector;

/**
 * Created by Ortal on 5/7/2018.
 */
public class MovieDetailsDS {

    private  volatile Vector<String[]> movieDetailsList;
    private  int currentIndex;

    public MovieDetailsDS() {
        this.movieDetailsList = new Vector<String[]>();
        this.currentIndex=-1;
    }

    public void add(String [] input)
    {
        movieDetailsList.add(input);
    }

    public int getSize()
    {
        return movieDetailsList.size();
    }


    public synchronized int updateCurrentIndex ()
    {
        currentIndex++;
        return currentIndex;
    }

    public int getCurrentIndex()
    {
        return currentIndex;
    }

    public String [] getValue(int index)
    {
        return movieDetailsList.get(index);
    }
}
