package com.reevan.firebaselogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {

    EditText name,address,email,password;
    CheckBox scifi,horror;
    Button confirm;
    FirebaseAuth mAuth;
    ConstraintLayout l;
    TextView fill ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        scifi = findViewById(R.id.scifi);
        horror = findViewById(R.id.horror);
        confirm = findViewById(R.id.confirm);
        l = findViewById(R.id.l);
        l.setBackgroundResource(R.drawable.grad3);
        fill=findViewById(R.id.textView);
        String emoji = new String(Character.toChars(0x1F607));
        fill.setText(fill.getText()+" "+emoji);

        confirm.setText(confirm.getText()+" "+new String(Character.toChars(0x1F929)));

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e = email.getText().toString();
                String p = password.getText().toString();
                if(e.isEmpty()){
                    email.setError("Please enter the email ID");
                    email.requestFocus();
                }
                else if(p.isEmpty())
                {
                    password.setError("Please Enter the password");
                    password.requestFocus();
                }
                else{
                    mAuth.createUserWithEmailAndPassword(e,p).addOnCompleteListener(Signup.this,new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                                startActivity(new Intent(Signup.this,Homescreen.class));
                            else
                                Toast.makeText(Signup.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
