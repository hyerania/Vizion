package csce462.vizionapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {
    private Button btnRegister;
    private EditText etUFirstName;
    private EditText etULastName;
    private EditText etUserEmail;
    private EditText etUserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUFirstName = (EditText) findViewById(R.id.etUFirstName);
        etULastName = (EditText) findViewById(R.id.etULastName);
        etUserEmail = (EditText) findViewById(R.id.etUserEmail);
        etUserPassword = (EditText) findViewById(R.id.etUserPassword);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //@TODO Must add information gathered to database and return to Dashboard with UPDATED information
                Intent addIntent = new Intent(Register.this, Dashboard.class);
                startActivity(addIntent);
            }
        });
    }
}
