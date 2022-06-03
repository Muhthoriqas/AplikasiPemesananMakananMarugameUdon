package com.example.marugame;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.marugame.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {

    EditText edtPhone,edtName, edtPassword;
    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtName=findViewById(R.id.edtName);
        edtPassword=findViewById(R.id.edtPassword);
        edtPhone=findViewById(R.id.edtPhone);

        btnSignUp=findViewById(R.id.btnSignUp);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                mDialog.setMessage("Lagi Loading...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if(edtPhone.getText().toString().length()==0){
                            mDialog.dismiss();
                            Toast.makeText(SignUp.this, "No Telfon Belum terisi", Toast.LENGTH_SHORT).show();
                        }else if(edtName.getText().toString().length()==0){
                            mDialog.dismiss();
                            Toast.makeText(SignUp.this, "Nama harus diisi", Toast.LENGTH_SHORT).show();
                        }else if (edtPassword.getText().toString().length()==0){
                            mDialog.dismiss();
                            Toast.makeText(SignUp.this, "Password Harus diisi", Toast.LENGTH_SHORT).show();
                        }
                        else if(dataSnapshot.child(edtPhone.getText().toString()).exists()){ //Cek Nomor
                            mDialog.dismiss();
                            Toast.makeText(SignUp.this, "Nomor Sudah Terdaftar", Toast.LENGTH_SHORT).show();
                        }else{
                            mDialog.dismiss();
                            User user= new User(edtName.getText().toString(), edtPassword.getText().toString());
                            table_user.child(edtPhone.getText().toString()).setValue(user);
                            finish();
                            Toast.makeText(SignUp.this, "Sign Up Sucees", Toast.LENGTH_SHORT).show();
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
