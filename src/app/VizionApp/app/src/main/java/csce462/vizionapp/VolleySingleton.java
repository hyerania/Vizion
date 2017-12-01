package csce462.vizionapp;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Hernandez-TAMU on 10/27/2017.
 */

public class VolleySingleton {
    private static VolleySingleton mInstance;
    private RequestQueue mRequestQueue;
//    private static Context ctx;


    private VolleySingleton(Context context){
        mRequestQueue = getRequestQueue(context);
    }

    public static VolleySingleton getInstance(Context context){
        if(mInstance == null){
            mInstance = new VolleySingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(Context context){
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(context);
        }
        return mRequestQueue;
    }
}
