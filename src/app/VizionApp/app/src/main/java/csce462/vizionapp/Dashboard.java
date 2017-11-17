package csce462.vizionapp;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {
    private Button btnAddDoor;
    ListView mlistView;

//    int[] images = {R.drawable.door_icon};
    String[] names = {"Shaun", "Tyler", "Russell", "Yerania", "Random", "Testing"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mlistView = (ListView) findViewById(R.id.dashboard_listView);
        MyCustomAdapter customAdaptor = new MyCustomAdapter();
        mlistView.setAdapter(customAdaptor);

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
            TextView mTextView = (TextView) view.findViewById(R.id.door_name);

//            mImageView.setImageResource(images[0]);
            mTextView.setText(names[position]);
            return view;
        }
    }

}
