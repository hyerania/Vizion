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

public class UsersAccess extends AppCompatActivity {
    private Button btnAddUser;
    ListView mListAccessView;
    // @TODO: Need to select from database and pull data from there
    String[] users = {"Shaun", "Tyler", "Russell", "Yerania", "Random", "Testing"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_access);

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
    }

    public void changeDoorStatus(View view) {
        boolean isDoorStatusOn = ((ToggleButton)view).isChecked();
        if(isDoorStatusOn){
            //Set text to "LOCKED" (off to on)
            //@TODO Must send signal to turn off
            Log.i("UserAccess Toggle", "Door locked!");
        }
        else{
            //Set text to "UNLOCKED" (on to off)
            //@TODO Must send signal to turn status on
            Log.i("UserAccess Toggle", "Door unlocked!");
        }
    }

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
