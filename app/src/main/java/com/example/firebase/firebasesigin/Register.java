package com.example.firebase.firebasesigin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;


public class Register extends AppCompatActivity {

    private EditText userName;
    private EditText userEmailR;
    private EditText userPassR;
    private Button button1R;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        // Initialization
        userName = (EditText) findViewById(R.id.editText1R);
        userEmailR = (EditText) findViewById(R.id.editTextR);
        userPassR = (EditText) findViewById(R.id.editText2R);
        button1R = (Button) findViewById(R.id.button1R);

        button1R.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //Retrieving name, email and password
                String mUserName = userName.getText().toString();
                String mUserEmail = userEmailR.getText().toString();
                String mUserPass = userPassR.getText().toString();

                // Removing Space
                mUserName = mUserName.trim();
                mUserEmail = mUserEmail.trim();
                mUserPass = mUserPass.trim();

                if (mUserName.isEmpty() || mUserEmail.isEmpty() || mUserPass.isEmpty()) {
                    // When Field is left blank below message is shown
                    Toast.makeText(Register.this, "Please check your Name, Email and Password", Toast.LENGTH_SHORT).show();
                } else {

                    // Creating an object of Firebase and passing the AppUrl
                    Firebase ref = new Firebase("https://simplelogin1.firebaseio.com/");

                    // User SignUp
                    ref.createUser(mUserEmail, mUserPass, new Firebase.ValueResultHandler<Map<String, Object>>() {
                        @Override
                        public void onSuccess(Map<String, Object> result) {
                            //On successful Registration displaying a message and returning back to Login activity
                            Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this, MainActivity.class));
                        }


                        @Override
                        public void onError(FirebaseError firebaseError) {
                            // there was an error
                            Toast.makeText(Register.this, firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });


                }
            }
        });

    }


}


