package com.movie.details.convertor.data.access.object;

import com.movie.details.convertor.business.object.MovieDetailBO;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

/**
 * Created by Ortal on 5/15/2018.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MovieDetailsDAOTest {

    private MovieDetailsDAO movieDetailsDAO;
    private MovieDetailBO movieDetailBO;
    private String id;

    @Before
    public void initializeTest()
    {

        id = "tt7026230";

        movieDetailBO = new MovieDetailBO();
        movieDetailBO.setCtegories("Action");
        movieDetailBO.setFulltitle("Patton Oswalt: Annihilation (2017)");
        movieDetailBO.setImdbID("tt7026230");
        movieDetailBO.setImdbRating("7.4");
        movieDetailBO.setLanguage("English");
        movieDetailBO.setImageURL("https://hydramovies.com/wp-content/uploads/2018/04/Patton-Oswalt-Annihilation-Movie-Poster.jpg");
        movieDetailBO.setYtID("4hZi5QaMBFc");
        movieDetailBO.setRuntime("66");
        movieDetailBO.setSummary("Patton Oswald, despite a personal tragedy, produces his best standup yet. Focusing on the tribulations of the Trump era and life after the loss of a loved one, Patton Oswald continues his journey to contribute joy to the world.");
        movieDetailBO.setTitle("atton Oswalt: Annihilation");
        movieDetailsDAO = new MovieDetailsDAO();

    }

    @Test
    public void tast1_save() throws Exception {

        movieDetailsDAO.save(movieDetailBO);
        MovieDetailBO resultFriomDB = movieDetailsDAO.get(movieDetailBO.getClass().getName(),id);
        assertEquals(resultFriomDB,movieDetailBO);
    }

    @Test
    public void test2_delete() throws Exception {

        movieDetailsDAO.delete(movieDetailBO);
        MovieDetailBO resultFriomDB = movieDetailsDAO.get(movieDetailBO.getClass().getName(),id);
        assertTrue(resultFriomDB==null);

    }



    @Test
    public void test3_update() throws Exception {

        System.out.println("test3_update");

        movieDetailsDAO.save(movieDetailBO);

        movieDetailBO.setCtegories("Drama");

        movieDetailsDAO.update(movieDetailBO);

        MovieDetailBO  movieDetailBOUpdatedFromDB = new MovieDetailBO();
        movieDetailBOUpdatedFromDB = movieDetailsDAO.get(movieDetailBO.getClass().getName(),movieDetailBO.getImdbID());

        assertEquals(movieDetailBOUpdatedFromDB.getCtegories(),"Drama");
    }


}
