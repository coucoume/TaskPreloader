package me.coucou.taskpreloader.task;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import me.coucou.taskpreloader.R;

/**
 * Created by matiasmunoz on 25/2/16.
 */
public class LoadingTasksManager  {

    private static final String TAG="LoadingTasksManager";

    private TextView mUpdateStatusText;
    private View mEventContainer;
    private Context mContext;

    private TaskObserver mTaskObserver;

    private Observable mInitialTask;
    private Observable mFinalizeTask;

    ArrayList<Observable> list;

    public LoadingTasksManager(Context context, TextView statusText, View eventContainer){

        list = new ArrayList<Observable>();

        mUpdateStatusText = statusText;
        mContext = context;
        mEventContainer = eventContainer;

        mTaskObserver = new TaskObserver();

        mInitialTask = new LoadInitTask(context);
        mFinalizeTask = new FinalizeTask(context);

        mInitialTask.addObserver(mTaskObserver);
        mFinalizeTask.addObserver(mTaskObserver);

        list.add(mInitialTask);
        list.add(mFinalizeTask);

        performTask();

    }

    public void performTask(){
        AbstractTask task;
        for (Observable observable:list){
            task = (AbstractTask) observable;
            if (task.getStatus() == AbstractTask.ERROR || task.getStatus() == AbstractTask.IN_PROGRESS){
                mUpdateStatusText.setText(task.getDescription());
                task.perform();
                break;
            }
        }

    }

    public void performError(){
        Snackbar bar = Snackbar.make(mEventContainer,R.string.preloader_error,Snackbar.LENGTH_INDEFINITE);
        bar.setAction(R.string.snack_reintent, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performTask();
            }
        });
        bar.show();
    }

    public class TaskObserver implements Observer
    {
        /**
         * This method is called if the specified {@code Observable} object's
         * {@code notifyObservers} method is called (because the {@code Observable}
         * object has been updated.
         *
         * @param observable the {@link Observable} object.
         * @param data       the data passed to {@link Observable#notifyObservers(Object)}.
         */
        @Override
        public void update(Observable observable, Object data) {
            AbstractTask task = (AbstractTask) observable;

            if (task.getStatus() == AbstractTask.ERROR){
                performError();
            }else{
                performTask();
            }

        }
    }
}
