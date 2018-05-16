package com.movie.details.convertor.process;


import com.movie.details.convertor.data.structure.MovieDetailsDS;
import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.Mockito;
import java.util.HashMap;

/**
 * Created by Ortal on 5/15/2018.
 */

public class MovieDetailCSVToDBTest {

   private MovieDetailCSVToDB movieDetailCSVToDBMock;
   private MovieDetailsDS MovieDetailsDSMock;

   @Before
   public void initializeData()
   {
      movieDetailCSVToDBMock = new MovieDetailCSVToDB();
      movieDetailCSVToDBMock.setNumberOfThreads(6);

      MovieDetailsDSMock = Mockito.mock(MovieDetailsDS.class);
      movieDetailCSVToDBMock.setMovieDetailsDataStructure(MovieDetailsDSMock);

      Mockito.when(MovieDetailsDSMock.getSize()).thenReturn(513);
   }

   @Test
   public void mapDetailsMovieDSTest()
   {
      HashMap<Integer,Pair<Integer,Integer>> indexMapping = movieDetailCSVToDBMock.mapDetailsMovieDS();

      assertEquals((int) indexMapping.get(0).getKey(),0 );
      assertEquals((int) indexMapping.get(0).getValue(),84 );
      assertEquals((int) indexMapping.get(1).getKey(),85 );
      assertEquals((int) indexMapping.get(1).getValue(),169 );
      assertEquals((int) indexMapping.get(2).getKey(),170 );
      assertEquals((int) indexMapping.get(2).getValue(),254 );
      assertEquals((int) indexMapping.get(3).getKey(),255 );
      assertEquals((int) indexMapping.get(3).getValue(),339 );
      assertEquals((int) indexMapping.get(4).getKey(),340 );
      assertEquals((int) indexMapping.get(4).getValue(),424 );
      assertEquals((int) indexMapping.get(5).getKey(),425 );
      assertEquals((int) indexMapping.get(5).getValue(),512 );

      assertEquals((int) MovieDetailsDSMock.getSize(),513 );
   }



}
