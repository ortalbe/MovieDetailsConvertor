package main.java.com.movie.details.convertor.business.object;

import org.apache.log4j.Logger;

import javax.persistence.*;

/**
 * MovieDetailBO - Bean class for table REF_MOVIE_DETAILS
 */

@Entity
@Table(name = "MOVIE_DETAILS" )
public class MovieDetailBO extends BusinessObject{

    public final static Logger LOG = Logger.getLogger(MovieDetailBO.class);
    public final static String CLASS_NAME = "MovieDetailBO";
    public final static Integer COLUMN_SIZE= 11;

    @Column(name="MOVIE_TITLE",nullable = false)
    private String title;

    @Column(name="MOVIE_FULL_TITLE")
    private String fulltitle;

    @Column(name="MOVIE_YAER")
    private int movie_year;

    @Column(name="MOVIE_CATEGORY")
    private String ctegories;

    @Column(name="MOVIE_SUMMARY", length=1024)
    @Lob
    private String summary;

    @Column(name="MOVIE_IMAGE_URL",nullable = false)
    private String imageURL;

    @Id
    @Column(name="MOVIE_MDB_ID",nullable = false)
    private String imdbID;

    @Column(name="MOVIE_IMDB_RATE")
    private String imdbRating;

    @Column(name="MOVIE_RUNTIME")
    private String runtime;

    @Column(name="MOVIE_LANGUAGE")
    private String language;

    @Column(name="MOVIE_YT_ID")
    private String ytID;


    public MovieDetailBO() {

        this.title = null;
        this.fulltitle = null;
        this.movie_year = -1;
        this.ctegories = null;
        this.summary=null;
        this.imageURL = null;
        this.imdbID = null;
        this.imdbRating = null;
        this.runtime = null;
        this.language = null;
        this.ytID=null;
        this.setName(this.getClass().getName());

    }

     public MovieDetailBO(String [] array) {

        String methodName = "::MovieDetailBO ";

        if(array.length!=COLUMN_SIZE)
        {
            LOG.error(CLASS_NAME + methodName + "number of column in csv file not equal to MOVIE_DETAILS table.");
        }

        this.title = array[0];
        this.fulltitle = array[1];
        this.movie_year = Integer.parseInt(array[2]);
        this.ctegories = array[3];
        this.summary=array[4];
        this.imageURL = array[5];
        this.imdbID = array[6];
        this.imdbRating = array[7];
        this.runtime = array[8];
        this.language = array[9];
        this.ytID=array[10];
        this.setName(this.getClass().getName());
    }



     public MovieDetailBO(String title, String fulltitle, int movie_year, String ctegories, String imageURL, String imdbID, String imdbRating, String runtime, String language) {

        this.title = title;
        this.fulltitle = fulltitle;
        this.movie_year = movie_year;
        this.ctegories = ctegories;
        this.imageURL = imageURL;
        this.imdbID = imdbID;
        this.imdbRating = imdbRating;
        this.runtime = runtime;
        this.language = language;
        this.setName(this.getClass().getName());

    }

     public MovieDetailBO(String title, String fulltitle, int movie_year, String ctegories, String summary, String imageURL, String imdbID, String imdbRating, String runtime, String language, String ytID) {

        this.title = title;
        this.fulltitle = fulltitle;
        this.movie_year = movie_year;
        this.ctegories = ctegories;
        this.summary = summary;
        this.imageURL = imageURL;
        this.imdbID = imdbID;
        this.imdbRating = imdbRating;
        this.runtime = runtime;
        this.language = language;
        this.ytID = ytID;
        this.setName(this.getClass().getName());

    }

    public void setDetail(String [] array) {

        String methodName = "::setDetail ";

        if(array.length!=COLUMN_SIZE)
        {
            LOG.error(CLASS_NAME + methodName + "number of column in csv file not equal to MOVIE_DETAILS table.");
        }

        this.title = array[0];
        this.fulltitle = array[1];
        this.movie_year = Integer.parseInt(array[2]);
        this.ctegories = array[3];
        this.summary=array[4];
        this.imageURL = array[5];
        this.imdbID = array[6];
        this.imdbRating = array[7];
        this.runtime = array[8];
        this.language = array[9];
        this.ytID=array[10];

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFulltitle() {
        return fulltitle;
    }

    public void setFulltitle(String fulltitle) {
        this.fulltitle = fulltitle;
    }

    public int getMovie_year() {
        return movie_year;
    }

    public void setMovie_year(int movie_year) {
        this.movie_year = movie_year;
    }

    public String getCtegories() {
        return ctegories;
    }

    public void setCtegories(String ctegories) {
        this.ctegories = ctegories;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getYtID() {
        return ytID;
    }

    public void setYtID(String ytID) {
        this.ytID = ytID;
    }




}
