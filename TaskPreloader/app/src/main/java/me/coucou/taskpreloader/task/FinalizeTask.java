package me.coucou.taskpreloader.task;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import me.coucou.taskpreloader.MainActivity;
import me.coucou.taskpreloader.R;

/**
 * Created by matiasmunoz on 26/2/16.
 */
public class FinalizeTask extends AbstractTask {

    private static final String TAG= "FinalizeTask";

    public FinalizeTask(Context context){
        this.mContext = context;

        setName(TAG);
        setStatus(IN_PROGRESS);
        setDescription(this.mContext.getString(R.string.preploader_finish));
    }
    /**
     * Abstract method need to be implemented to be able to perform the task
     */
    @Override
    public void perform() {

        setStatus(DONE);

        Activity currentActivity = ((Activity)mContext);
        Intent i = new Intent(currentActivity, MainActivity.class);
        mContext.startActivity(i);

        currentActivity.finish();
    }

    /**
     * Task Name
     *
     * @param value
     */
    @Override
    public void setName(String value) {
        this.name = value;
    }
}
