package me.coucou.taskpreloader.task;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import me.coucou.taskpreloader.R;
import me.coucou.taskpreloader.networking.GsonRequest;
import me.coucou.taskpreloader.networking.LoadInitDataDTO;
import me.coucou.taskpreloader.networking.NetworkingManager;

/**
 * Created by matiasmunoz on 25/2/16.
 * Observable task
 */
public class LoadInitTask extends AbstractTask {

    private static final String TAG= "LoadInitTask";

    private Response.Listener listener;
    private Response.ErrorListener errorListener;


    public LoadInitTask(Context context){
        this.mContext = context;

        this.setName(TAG);
        this.setStatus(IN_PROGRESS);
        this.setDescription(this.mContext.getString(R.string.preloader_init));
    }

    @Override
    public void perform() {

        listener = new Response.Listener() {
            @Override
            public void onResponse(Object response) {

                LoadInitDataDTO dto = (LoadInitDataDTO) response;

                setStatus(DONE);
                setChanged();
                notifyObservers();
            }
        };

        errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, error.toString());
                setStatus(ERROR);
                setChanged();
                notifyObservers();
            }
        };

        //Just for testing purpose
        String endpoint = "http://jsonplaceholder.typicode.com/posts/1/comments";

        GsonRequest request = new GsonRequest(endpoint, LoadInitDataDTO.class,null, listener, errorListener);
        NetworkingManager nm = new NetworkingManager(this.mContext);
        nm.performRequest(request);
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
