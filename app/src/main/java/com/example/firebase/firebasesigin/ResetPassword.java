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

/**
 * Created by Harish on 17-Aug-16.
 */
public class ResetPassword extends AppCompatActivity {

    private EditText userEmail;
    private EditText oldPass;
    private EditText newPass;
    private Button resetbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);

        userEmail = (EditText) findViewById(R.id.email);
        oldPass = (EditText) findViewById(R.id.oldpass);
        newPass = (EditText) findViewById(R.id.newpass);
        resetbtn = (Button) findViewById(R.id.resetbtn);

        resetbtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                String rUserEmail = userEmail.getText().toString();
                String rOldPass = oldPass.getText().toString();
                String rNewPass = newPass.getText().toString();

                rUserEmail = rUserEmail.trim();
                rOldPass = rOldPass.trim();
                rNewPass = rNewPass.trim();

                if (rUserEmail.isEmpty() || rOldPass.isEmpty() || rNewPass.isEmpty()) {

                    Toast.makeText(ResetPassword.this, "Please don't leave any field blank", Toast.LENGTH_SHORT).show();
                } else {

                    Firebase ref = new Firebase("https://simplelogin1.firebaseio.com/");
                    ref.changePassword(rUserEmail, rOldPass, rNewPass, new Firebase.ResultHandler() {
                        @Override
                        public void onSuccess() {
                            // password changed
                            Toast.makeText(ResetPassword.this, "Password change successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ResetPassword.this, MainActivity.class));
                        }

                        @Override
                        public void onError(FirebaseError firebaseError) {
                            // error encountered
                            Toast.makeText(ResetPassword.this, firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


    }
}
