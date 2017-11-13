package csce462.vizionapp;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Dashboard extends AppCompatActivity {
    private Button btnAddDoor;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        listView = (ListView) findViewById(R.id.dashboard_listView);
//        listView.adapter = MyCustomAdapater();

        btnAddDoor = (Button) findViewById(R.id.btnAddDoor);
        btnAddDoor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent addIntent = new Intent(Dashboard.this, AddDoor.class);
                startActivity(addIntent);
            }
        });
    }

    private class MyCustomAdapater extends BaseAdapter{
        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return null;
        }
    }

}
