package main.java.com.movie.details.convertor.data.access.object;

import main.java.com.movie.details.convertor.utils.ErrorCode;

/**
 * Created by Ortal on 5/5/2018.
 */
public interface DataAccessObjectInterface {

    ErrorCode save(Object obj);
    ErrorCode update(Object obj);
    ErrorCode delete(Object obj);
    ErrorCode openSession();
}
