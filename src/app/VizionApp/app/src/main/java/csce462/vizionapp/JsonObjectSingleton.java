package csce462.vizionapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Hernandez-TAMU on 11/30/2017.
 */

public class JsonObjectSingleton {
    private static JsonObjectSingleton mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private JsonObjectSingleton(Context context){
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public static synchronized JsonObjectSingleton getInstance(Context context){
        if(mInstance == null){
            mInstance = new JsonObjectSingleton(context);
        }
        return mInstance;
    }

    public<T> void addToRequestQueue(Request<T> request){
        mRequestQueue.add(request);
    }
}
