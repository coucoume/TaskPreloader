package me.coucou.taskpreloader.task;

import android.content.Context;

import java.util.Observable;

/**
 * Created by matiasmunoz on 26/2/16.
 */
public abstract class AbstractTask extends Observable{

    public static final String IN_PROGRESS="inprogress";
    public static final String DONE="done";
    public static final String ERROR="error";


    protected Context mContext;

    protected String name;
    protected String status;
    protected String description;


    public String getName(){
        return name;
    }

    public void setStatus(String value){
        status = value;
    }

    public String getStatus(){
        return status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Abstract method need to be implemented to be able to perform the task
     */
    public abstract void perform();

    /**
     * Task Name
     * @param value
     */
    public abstract void setName(String value);


}
