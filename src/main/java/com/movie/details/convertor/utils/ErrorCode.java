package main.java.com.movie.details.convertor.utils;

/**
 * Created by Ortal on 5/5/2018.
 */

public enum ErrorCode{

    SUCCESS("Success"),
    ERROR("Error"),
    WARNING("Warnning");

    private final String value;

    ErrorCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}



