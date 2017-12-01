package csce462.vizionapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private static final String REQUESTTAG = "string request first";
    private Button sendButton;
    private RequestQueue mRequestQueue;
    private StringRequest stringRequest;
    EditText etURL;
//    private String url = "http://10.230.201.34:8000/turnonled/";
    private DiskBasedCache mCache;
    private com.android.volley.Network mNetwork;

    private Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String userEmail = getIntent().getStringExtra("currentUserEmail");
//        Log.i(TAG, "Checking current logged in: " + userEmail);


        //GET REQUEST
        etURL = (EditText) findViewById(R.id.etURL);
        sendButton = (Button) findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Send request and print the response using volley library
                String url = etURL.getText().toString();
                sendRequestAndPrintResponse(url);
            }
        });

        btnTest = (Button) findViewById(R.id.btnTest);
        btnTest.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, Dashboard.class);
                intent.putExtra("currentUserEmail", userEmail);
                startActivity(intent);
            }
        });
    }

    //Used for GET REQUEST
    private void sendRequestAndPrintResponse(String url) {
//        mRequestQueue = Volley.newRequestQueue(this);
//        mCache = new DiskBasedCache(getCacheDir(), 4*1024*1024);
//        mNetwork = new BasicNetwork(new HurlStack());
//        mRequestQueue = new RequestQueue(mCache, mNetwork);
//        mRequestQueue.start();
        mRequestQueue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue(this.getApplicationContext());

        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "Response: " + response.toString());
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Log.i(TAG, "Error: " + error.toString());
            }
        });

        stringRequest.setTag(REQUESTTAG);
        mRequestQueue.add(stringRequest);
    }

    @Override
    protected void onStop(){
        super.onStop();
        if(mRequestQueue != null){
            mRequestQueue.cancelAll(REQUESTTAG);
        }
    }
}
