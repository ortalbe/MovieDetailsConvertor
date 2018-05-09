package main.java.com.movie.details.convertor.business.object;

/**
 * Created by Ortal on 5/9/2018.
 */
public class BusinessObject {

    private String name;

     BusinessObject() {
        this.name = null;
    }

    public BusinessObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
