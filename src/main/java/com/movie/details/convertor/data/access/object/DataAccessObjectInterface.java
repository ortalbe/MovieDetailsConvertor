package com.movie.details.convertor.data.access.object;


import com.movie.details.convertor.business.object.BusinessObject;
import com.movie.details.convertor.utils.ErrorCode;

/**
 * DataAccessObjectInterface - data access interface. all DAO must implemt it.
 */
public interface DataAccessObjectInterface <E extends BusinessObject>  {

    ErrorCode save(E busniessObject );
    ErrorCode update(E busniessObject);
    ErrorCode delete(E busniessObject);
    ErrorCode openSession();
}
