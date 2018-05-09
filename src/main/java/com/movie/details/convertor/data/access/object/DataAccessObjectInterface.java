package main.java.com.movie.details.convertor.data.access.object;

import main.java.com.movie.details.convertor.business.object.BusinessObject;
import main.java.com.movie.details.convertor.utils.ErrorCode;

/**
 * Created by Ortal on 5/5/2018.
 */
public interface DataAccessObjectInterface <E extends BusinessObject>  {

    ErrorCode save(E busniessObject );
    ErrorCode update(E busniessObject);
    ErrorCode delete(E busniessObject);
    ErrorCode openSession();
}
