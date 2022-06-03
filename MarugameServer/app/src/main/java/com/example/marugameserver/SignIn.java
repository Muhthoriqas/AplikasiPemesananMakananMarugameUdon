package com.example.marugameserver;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marugameserver.Common.Common;
import com.example.marugameserver.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    EditText edtPhone, edtPassword;
    Button btnSignIn;

    FirebaseDatabase db;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnSignIn = (Button)findViewById(R.id.btnSignIn);


        db = FirebaseDatabase.getInstance();
        users = db.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sigInUser(edtPhone.getText().toString(),edtPassword.getText().toString());
            }
        });
    }

    private void sigInUser(final String phone, String password) {
        ProgressDialog mDialog = new ProgressDialog(SignIn.this);
        mDialog.setMessage("Plese waiting..");
        mDialog.show();

        final String localPhone = phone;
        final String localPassword = password;
        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(localPhone).exists())
                {
                    mDialog.dismiss();
                    //Get User Information
                    User user = dataSnapshot.child(localPhone).getValue(User.class);
                    user.setPhone(localPhone);
                    if(Boolean.parseBoolean(user.getIsStaff()))
                    {
                        if (user.getPassword().equals(localPassword))
                        {
                            Intent homeIntent = new Intent(SignIn.this,Home.class);
                            Common.currentUser=user;
                            startActivity(homeIntent);
                            Toast.makeText(SignIn.this, "Welcome Admin", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else
                        {
                            Toast.makeText(SignIn.this, "Password Salah!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    mDialog.dismiss();
                    Toast.makeText(SignIn.this, "Anda Belum Menjadi Staff Kami Silahkan Hubungi Pihak Rumah Makan ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}