package csce462.vizionapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddUserAccess extends AppCompatActivity {
    private Button btnAddUser;
    private EditText etUsersEmailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_access);

        etUsersEmailAddress = (EditText) findViewById(R.id.etUsersEmailAddress);

        btnAddUser = (Button) findViewById(R.id.btnAddUser);
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //@TODO Must add information gathered to database and return to User Access with UPDATED information
                Intent addIntent = new Intent(AddUserAccess.this, UsersAccess.class);
                startActivity(addIntent);
            }
        });
    }
}
