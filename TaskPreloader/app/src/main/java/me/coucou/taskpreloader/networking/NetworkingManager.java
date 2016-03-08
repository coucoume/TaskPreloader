package me.coucou.taskpreloader.networking;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by matiasmunoz on 18/2/16.
 */
public class NetworkingManager {

    Context mContext;
    private RequestQueue mRequestQueue;
    private DefaultRetryPolicy mDefaultRetryPolicy;

    public NetworkingManager(Context appContext){

        mContext = appContext;
        mRequestQueue = Volley.newRequestQueue(mContext);
        mDefaultRetryPolicy = new DefaultRetryPolicy(5000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    }

    public void performRequest(GsonRequest request) {

        request.setRetryPolicy(mDefaultRetryPolicy);
        mRequestQueue.add(request);

    }

}
