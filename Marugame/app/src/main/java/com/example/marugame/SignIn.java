package com.example.marugame;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.marugame.Common.Common;
import com.example.marugame.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {
    EditText edtPhone, edtPassword;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPhone = findViewById(R.id.edtPhone);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignIn = findViewById(R.id.btnSignIn);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Pesan Loading
                ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                mDialog.setMessage("Lagi Loading..");
                mDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(edtPhone.getText().toString().length()==0 ){
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this, "Nomor Telfon harus di isi", Toast.LENGTH_SHORT).show();
                        }else if(edtPassword.getText().toString().length()==0){
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this, "Password Belum Diisi", Toast.LENGTH_SHORT).show();
                        }else if (dataSnapshot.child(edtPhone.getText().toString()).exists()) { //Cek Nomor Telfon
                            mDialog.dismiss();
                            //Set User
                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            //Set Phone
                            user.setPhone(edtPhone.getText().toString());
                            if (user.getPassword().equals(edtPassword.getText().toString())) { //Cek Password
                                {
                                    Intent homeIntent = new Intent(SignIn.this, Home.class);
                                    Common.currentUser= user;
                                    startActivity(homeIntent);
                                    Toast.makeText(SignIn.this, "Welcome", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            } else { //Jika Password Salah
                                Toast.makeText(SignIn.this, "Password Salah", Toast.LENGTH_SHORT).show();
                            }
                        } else { // Jika Belum Register
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this, "Nomor Telfon Belum Terdaftar! Silahkan Register Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });
            }
        });

    }
}