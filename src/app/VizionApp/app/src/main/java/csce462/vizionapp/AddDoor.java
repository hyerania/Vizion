package csce462.vizionapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class AddDoor extends AppCompatActivity {
    private static final String TAG = AddDoor.class.getName();
    private Button btnAddDoor;
    private EditText etDoorName;
    private EditText etLocation;
    private static String serverURL = "http://192.168.43.107:8000/addlock";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_door);
        Bundle extras = getIntent().getExtras();
        final String userEmail = extras.getString("UserEmail");

        etDoorName = (EditText) findViewById(R.id.etDoorName);
        etLocation = (EditText) findViewById(R.id.etLocation);



        btnAddDoor = (Button) findViewById(R.id.btnAddDoor);
        btnAddDoor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //@TODO Must add information gathered to database and return to Dashboard with UPDATED information
                final String location = etDoorName.getText().toString();
                final String address = etLocation.getText().toString();
//                final String tempURL = serverURL + "?useremail=" + userEmail + "&location=" + location + "&address=" + address;

                String newUrl = null;
                try {
                    String newLocation = URLEncoder.encode(location,"UTF-8");
                    String newAddress = URLEncoder.encode(address,"UTF-8");
                    newUrl = serverURL + "?useremail=" + userEmail + "&location=" + newLocation + "&address=" + newAddress;

//                    newUrl = URLEncoder.encode(tempURL, "UTF-8");

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Log.i(TAG, "NEW Url: " + newUrl);
                buttonAddDoor(btnAddDoor, userEmail, location, address, newUrl);

            }
        });

    }

    private void buttonAddDoor(Button btnAddDoor, String userEmail, String location, String address, String newUrl){
        Log.i("UsersAccess: ", "NEW URL: " + newUrl);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, newUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "Response: " + response.toString());
//                try {
//
//                } catch (JSONException e) {
//                    Log.i(TAG, "Error: Can't get JSONObject");
//                    e.printStackTrace();
//                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "Error: " + error.toString());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        JsonObjectSingleton.getInstance(AddDoor.this).addToRequestQueue(jsonObjectRequest);
        Intent addIntent = new Intent(AddDoor.this, Dashboard.class);
        addIntent.putExtra("currentUserEmail", userEmail);
        startActivity(addIntent);
    }


}
