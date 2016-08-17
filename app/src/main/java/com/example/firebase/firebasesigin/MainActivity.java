package com.example.firebase.firebasesigin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


public class MainActivity extends AppCompatActivity {

    private EditText userEmail;
    private EditText userPass;
    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialization
        userEmail = (EditText) findViewById(R.id.editText);
        userPass = (EditText) findViewById(R.id.editText2);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        Firebase.setAndroidContext(this);

        button1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                String lUserEmail = userEmail.getText().toString();
                String lUserPass = userPass.getText().toString();

                lUserEmail = lUserEmail.trim();
                lUserPass = lUserPass.trim();

                if (lUserEmail.isEmpty() || lUserPass.isEmpty()) {

                    Toast.makeText(MainActivity.this, "Please check your Email and Password", Toast.LENGTH_SHORT).show();
                } else {

                    // Authenticating user by email and password
                    Firebase ref = new Firebase("https://simplelogin1.firebaseio.com/");
                    ref.authWithPassword(lUserEmail, lUserPass, new Firebase.AuthResultHandler() {
                        @Override
                        public void onAuthenticated(AuthData authData) {
                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, Home.class));
                        }

                        @Override
                        public void onAuthenticationError(FirebaseError firebaseError) {
                            // there was an error
                            Toast.makeText(MainActivity.this, firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    // On Clicking SignUp button it will open SignUp Activity
    public void Register(View view) {

        startActivity(new Intent(MainActivity.this, Register.class));
    }

    // Making the fields blank when we come back to login Activity
    @Override
    protected void onPause() {
        super.onPause();

        userEmail.setText("");
        userPass.setText("");
    }

    public void Reset(View view){

        startActivity(new Intent(MainActivity.this, ResetPassword.class));
    }
}
