package com.movie.details.convertor.process;

import com.movie.details.convertor.process.movieDetailsDBExecutor;
import org.apache.log4j.Logger;

/**
 * Created by Ortal on 5/9/2018.
 */
public class MovieDetailsDBMonitor {

    private boolean threadFinishedToCreateTable;
    private boolean threadCalledToCreateTable;
    private static Logger LOG = Logger.getLogger(movieDetailsDBExecutor.class);
    private static String CLASS_NAME = "MovieDetailsDBMonitor";

    public MovieDetailsDBMonitor() {
        threadFinishedToCreateTable=false;
        threadCalledToCreateTable = false;
    }

    public synchronized void waitForTableToBeCreated()
    {
        String methodName ="::waitForTableToBeCreated ";
        LOG.info(CLASS_NAME + methodName +  Thread.currentThread().getName());

        if(getThreadCalledToCreateTable() && !getThreadFinishedToCreateTable() )
        {
            try {
                LOG.info(CLASS_NAME + methodName + Thread.currentThread().getName() + "waiting for table to be created" );

                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else
        {
        	setThreadCalledToCreateTable(true);
        }

    }

    public synchronized boolean getThreadCalledToCreateTable()
    {
        return threadCalledToCreateTable;
    }

    public synchronized void setThreadCalledToCreateTable(boolean value)
    {
        threadCalledToCreateTable=value;
    }

    public synchronized boolean getThreadFinishedToCreateTable()
    {
        return threadFinishedToCreateTable;
    }

    public synchronized void setThreadFinishedToCreateTable(boolean value)
    {
    	threadFinishedToCreateTable=value;
    }

    public synchronized void tableCreated()
    {
        setThreadFinishedToCreateTable(true);
        notifyAll();
    }

    @Override
    public String toString() {
        return "MovieDetailsDBMonitor{" +
                "threadFinishedToCreateTable=" + threadFinishedToCreateTable +
                ", threadCalledToCreateTable=" + threadCalledToCreateTable +
                '}';
    }

	@Override
	public void finalize() throws Throwable {
		super.finalize();
	}
    
    
}

