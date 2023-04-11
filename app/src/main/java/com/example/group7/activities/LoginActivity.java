package com.example.group7.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.group7.R;
import com.example.group7.UI.Fragment.UserFragment;
import com.example.group7.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText ed_user, ed_password;
    Button btn_login;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView txt_logtores;
    String email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Hooks
        ed_user = findViewById(R.id.ed_user);
        ed_password = findViewById(R.id.ed_pass);
        btn_login = findViewById(R.id.btn_login);
        progressBar = findViewById(R.id.progressBar);
        txt_logtores = findViewById(R.id.txt_logtores);

        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        SharedPreferences mPreferences = getSharedPreferences("isLoggin", MODE_PRIVATE);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                email = ed_user.getText().toString();
                password = ed_password.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(LoginActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                userRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            User user = dataSnapshot.getValue(User.class);
                            if (email.equals(user.getEmail())) {
                                if (password.equals(user.getPassword())) {
                                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                    intent.putExtra("id", user.getId());
                                    intent.putExtra("email", user.getEmail());

                                    //Save login
                                    SharedPreferences.Editor editor = mPreferences.edit();
                                    editor.putBoolean("isLogged", true);
                                    editor.putString("userId", user.getId());
                                    editor.apply();
                                    startActivity(intent);
                                    return;
                                } else {
                                    Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_LONG).show();
                                    break;
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        txt_logtores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}