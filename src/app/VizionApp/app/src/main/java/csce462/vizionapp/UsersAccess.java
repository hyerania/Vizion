package csce462.vizionapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

class UserItems{
    private Integer id;
    private String userName;

    public UserItems(Integer id, String userName){
        this.id = id;
        this.userName = userName;
    }

    public Integer getId() { return id; }
    public String getUserName() { return userName; }

}

public class UsersAccess extends AppCompatActivity {
    private Button btnAddUser;
    private Button btnDoorStatus;
    ListView mListAccessView;
    // @TODO: Need to select from database and pull data from there
    String[] users = {"Shaun", "Tyler", "Russell", "Yerania", "Random", "Testing"};
    private static String serverURL = "http://192.168.1.19:8000/";
    String lockState;

    public String getLockState(){
        return this.lockState;
    }

    public void setLockState(String ls){
        this.lockState = ls;
    }


    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_access);
        Bundle extras = getIntent().getExtras();
        final String userEmail = extras.getString("UserEmail");
        setLockState(extras.getString("LockStatus"));
        final Integer lockID = extras.getInt("LockID");

        //@TODO Must fix when moving to this Intent, transfer over information and use it to display appropriate
        //@TODO information about the specific door and the actual person that will be accessing
        mListAccessView = (ListView) findViewById(R.id.access_ListView);
        UsersAccess.MyCustomAdapter customAdaptor = new UsersAccess.MyCustomAdapter();
        mListAccessView.setAdapter(customAdaptor);


        btnAddUser = (Button) findViewById(R.id.btnAddUser);
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent addIntent = new Intent(UsersAccess.this, AddUserAccess.class);
                startActivity(addIntent);
            }
        });

        btnDoorStatus = (Button) findViewById(R.id.btnDoorStatus);
        btnDoorStatus.setText(getLockState());
        btnDoorStatus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String newUrl = "";
                if(getLockState().equals("locked")){
                    newUrl = serverURL + "unlockdoorapp/?useremail=" + userEmail + "&lockid=" + lockID.toString();
                }
                else if(getLockState().equals("unlocked")){
                    newUrl = serverURL + "lockdoorapp/?useremail=" + userEmail + "&lockid=" + lockID.toString();
                }

                Log.i("UsersAccess: ", "NEW URL: " + newUrl);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, newUrl, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("UsersAccess: ", "Response: " + response.toString());
                        try {
//                            usernameTest = response.getString("username");
                            String state = response.getString("state");
                            setLockState(state);
                            if (state.equals("locked")){
                                btnDoorStatus.setText("Locked");
                            }
                            else if (state.equals("unlocked")){
                                btnDoorStatus.setText("Unlocked");
                            }
                        } catch (JSONException e) {
                            Log.i("UsersAccess: ", "Error: Can't get JSONObject");
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("UsersAccess: ", "Error: " + error.toString());
                    }
                }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json; charset=utf-8");
                        return headers;
                    }
                };
                JsonObjectSingleton.getInstance(UsersAccess.this).addToRequestQueue(jsonObjectRequest);

            }
        });
    }

//    public void changeDoorStatus(View view) {
//        boolean isDoorStatusOn = ((ToggleButton)view).isChecked();
//        if(isDoorStatusOn){
//            //Set text to "LOCKED" (off to on)
//            //@TODO Must send signal to turn off
//            Log.i("UserAccess Toggle", "Door locked!");
//        }
//        else{
//            //Set text to "UNLOCKED" (on to off)
//            //@TODO Must send signal to turn status on
//            Log.i("UserAccess Toggle", "Door unlocked!");
//        }
//    }

    private class MyCustomAdapter extends BaseAdapter {

        //Responsible for how many rows in my list
        @Override
        public int getCount() {
            return users.length;
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            View view = getLayoutInflater().inflate(R.layout.custom_layout_users,null);

            TextView mTextView = (TextView) view.findViewById(R.id.user_name);
            mTextView.setText(users[position]);

            return view;
        }
    }
}
