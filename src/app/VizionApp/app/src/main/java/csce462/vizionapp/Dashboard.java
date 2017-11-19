package csce462.vizionapp;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {
    private Button btnAddDoor;
    ListView mlistView;

//    int[] images = {R.drawable.door_icon};
    // @TODO: Need to select from database and pull data from there
    String[] names = {"Shaun", "Tyler", "Russell", "Yerania", "Random", "Testing"};
//    ArrayList<String> names = new ArrayList<String>();
    String[] address = {"4453 LaF", "42311 Shaun Rd", "123 Tyler Rd", "123 Yerania St", "123 Russell St", "123 Russell St"};

//    public void addNames(String name){
//        names.add(name);
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mlistView = (ListView) findViewById(R.id.dashboard_listView);
        MyCustomAdapter customAdaptor = new MyCustomAdapter();
        mlistView.setAdapter(customAdaptor);

        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Dashboard.this, UsersAccess.class);
                intent.putExtra("DoorName", mlistView.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });

        btnAddDoor = (Button) findViewById(R.id.btnAddDoor);
        btnAddDoor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent addIntent = new Intent(Dashboard.this, AddDoor.class);
                startActivity(addIntent);
            }
        });
    }

    private class MyCustomAdapter extends BaseAdapter{

        //Responsible for how many rows in my list
        @Override
        public int getCount() {
            return names.length;
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
            View view = getLayoutInflater().inflate(R.layout.custom_layout,null);

//            ImageView mImageView = (ImageView) view.findViewById(R.id.imageView);
            TextView mTextView = (TextView) view.findViewById(R.id.user_name);
            TextView mAddressView = (TextView) view.findViewById(R.id.address_name);

//            mImageView.setImageResource(images[0]);
            mTextView.setText(names[position]);
            mAddressView.setText(address[position]);
            return view;
        }
    }

}
