package csce462.vizionapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddDoor extends AppCompatActivity {
    private Button btnAddDoor;
    private EditText etDoorName;
    private EditText etLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_door);
        etDoorName = (EditText) findViewById(R.id.etDoorName);
        etLocation = (EditText) findViewById(R.id.etLocation);

        btnAddDoor = (Button) findViewById(R.id.btnAddDoor);
        btnAddDoor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //@TODO Must add information gathered to database and return to Dashboard with UPDATED information
                Intent addIntent = new Intent(AddDoor.this, Dashboard.class);
                startActivity(addIntent);
            }
        });

    }
}
