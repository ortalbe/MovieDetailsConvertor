package com.movie.details.convertor.business.object;

/**
 * Basic business Object - all BO must implement from it.
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
