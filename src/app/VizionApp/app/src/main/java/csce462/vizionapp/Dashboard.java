package csce462.vizionapp;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//class TestingFaceID{
//    private int lockid = 1;
//    private int faceid = 4;
//    TestingFaceID(){}
//}

class ListItem{
    private Integer id;
    private String location;
    private String address;
    private String state;

    public ListItem(Integer id, String location, String address, String state){
        this.id = id;
        this.location = location;
        this.address = address;
        this.state = state;
    }

    public Integer getId() { return id; }
    public String getLocation() { return location; }
    public String getAddress() {return address; }
    public String getStatus() {return state; }

}

public class Dashboard extends AppCompatActivity {
    private static final String TAG = Dashboard.class.getName();
    private Button btnAddDoor;
    ListView mlistView;
//    private static String serverURL = "http://192.168.253.2/dashboard";

//    private static String serverURL = "http://192.168.1.13:8000/getLocks/"; //Returns json (lots of fields)
    //Pass in email as parameter, get back JSON of door, location, lock status


    // @TODO: Need to select from database and pull data from there
//    String[] names = {"Shaun", "Tyler", "Russell", "Yerania", "Random", "Testing"};
//    String[] address = {"4453 LaF", "42311 Shaun Rd", "123 Tyler Rd", "123 Yerania St", "123 Russell St", "123 Russell St"};
//    String usernameTest;


    private List<ListItem> listItems;
    private static String serverURL = "http://192.168.1.13:8000/getLocks/"; //Returns json (lots of fields)
    //Pass in email as parameter, get back JSON of door, location, lock status
//    MyCustomAdapter customAdaptor = new MyCustomAdapter();
    private MyCustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
//        final String userEmail = getIntent().getStringExtra("currentUserEmail");
        String userEmail = "tyler.nardecchia@tamu.edu";
        Log.i(TAG, "Checking current logged in: " + userEmail);

        mlistView = (ListView) findViewById(R.id.dashboard_listView);
        listItems = new ArrayList<>();
        loadListVIewData(userEmail);


//        TestingFaceID obj = new TestingFaceID();
//        Gson gson = new Gson();
//        String json = gson.toJson(obj);
//        Log.d(TAG, "Gson string: " + json);

//        JSONObject jsObj = null;
//        try {
//            jsObj = new JSONObject(json);
//            Log.d(TAG, "This is json object: " + jsObj.toString());
//        } catch (Throwable t) {
//            Log.e("JSON Issues", "Could not parse JSON: \"" + json + "\"");
//        }

//        Integer faceid = 4;
//        Integer lockid = 1;
//        String newUrl = serverURL + "?faceid=" + faceid + "&lockid=" + lockid;
//        Log.d(TAG, "NEW URL: " + newUrl);

//        getLocks
//                -JSON Object: "id, location, address, status"
//        String usernameTest;
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, newUrl, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Log.i(TAG, "Response: " + response.toString());
//                try {
//                    usernameTest = response.getString("username");
//                } catch (JSONException e) {
//                    Log.i(TAG, "Error: Can't get JSONObject");
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.i(TAG, "Error: " + error.toString());
//            }
//        }){
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json; charset=utf-8");
//                return headers;
//            }
//        };
//        JsonObjectSingleton.getInstance(Dashboard.this).addToRequestQueue(jsonObjectRequest);

//        Log.i("GET REQUEST: ", usernameTest);


        btnAddDoor = (Button) findViewById(R.id.btnAddDoor);
        btnAddDoor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent addIntent = new Intent(Dashboard.this, AddDoor.class);
                startActivity(addIntent);
            }
        });
    }

    private void loadListVIewData(final String userEmail){
        String newUrl = serverURL + "?userEmail=" + userEmail;
        Log.d(TAG, "NEW URL: " + newUrl);
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, newUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "Response: " + response.toString());
                try {
//                    usernameTest = response.getString("locks");
//                    JSONObject aResponse = new JSONObject(response);
                    JSONArray array = response.getJSONArray("locks");
                    for(int i = 0; i < array.length(); i++){
                        JSONObject o = array.getJSONObject(i);
                        ListItem item = new ListItem(
                                o.getInt("id"),
                                o.getString("location"),
                                o.getString("address"),
                                o.getString("state")
                        );
                        listItems.add(item);

                    }
                    customAdapter = new MyCustomAdapter(listItems, getApplicationContext());
                    mlistView.setAdapter(customAdapter);



                } catch (JSONException e) {
                    Log.i(TAG, "Error: Can't get JSONObject");
                    e.printStackTrace();
                }
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
        JsonObjectSingleton.getInstance(Dashboard.this).addToRequestQueue(jsonObjectRequest);



        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Dashboard.this, UsersAccess.class);
//                intent.putExtra("DoorName", mlistView.getItemAtPosition(i).toString());
                Bundle extra = new Bundle();
                extra.putString("UserEmail", userEmail);
                extra.putString("LockStatus", listItems.get(i).getStatus());
                extra.putInt("LockID", listItems.get(i).getId());
                intent.putExtras(extra);
                startActivity(intent);
            }
        });

    }

    private class MyCustomAdapter extends BaseAdapter{
        private List<ListItem> listItems;
        private Context context;

        public MyCustomAdapter(List<ListItem> listItems, Context context){
            this.listItems = listItems;
            this.context = context;
        }
        //Responsible for how many rows in my list
        @Override
        public int getCount() {
            return listItems.size();
        }

        @Override
        public Object getItem(int i) {
            ListItem listItem = listItems.get(i);
            return listItem;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            View view = getLayoutInflater().inflate(R.layout.custom_layout,null);

//            ImageView mImageView = (ImageView) view.findViewById(R.id.imageView);
            TextView mTextView = (TextView) view.findViewById(R.id.user_name);
            TextView mAddressView = (TextView) view.findViewById(R.id.address_name);


//            mImageView.setImageResource(images[0]);
            mTextView.setText(listItems.get(position).getLocation());
            mAddressView.setText(listItems.get(position).getAddress());
            return view;
        }
    }

}
