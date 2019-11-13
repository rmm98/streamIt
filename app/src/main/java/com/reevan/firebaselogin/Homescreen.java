package com.reevan.firebaselogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Homescreen extends AppCompatActivity {

    Button a1,a2,t1,t2,m1,m2;
    FirebaseAuth mAuth;
    NestedScrollView s;
    FirebaseAuth.AuthStateListener mAuthListener;
    String t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);


        mAuth = FirebaseAuth.getInstance();
        String emoji = new String(Character.toChars(0x1F60E));
        String temp = mAuth.getCurrentUser().getEmail().toString();
        String title = temp.substring(1,temp.lastIndexOf('@'));
        setTitle(temp.substring(0,1).toUpperCase()+title+" "+emoji);

        a1 = findViewById(R.id.a1);
        a2 = findViewById(R.id.a2);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        m1 = findViewById(R.id.m1);
        m2 = findViewById(R.id.m2);
        s = findViewById(R.id.s);
        s.setBackgroundResource(R.drawable.grad);

        a1.setBackgroundResource(R.drawable.a1);
        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Homescreen.this,Watch.class);
                i.putExtra("address","https://firebasestorage.googleapis.com/v0/b/streamit-ff036.appspot.com/o/emojimovie.mp4?alt=media&token=3c9b06c1-a552-40b9-8160-881a36a837f9");
                startActivity(i);
            }
        });
        a2.setBackgroundResource(R.drawable.a2);
        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Homescreen.this,Watch.class);
                i.putExtra("address","https://firebasestorage.googleapis.com/v0/b/streamit-ff036.appspot.com/o/angrybirds.mp4?alt=media&token=c37c5de4-7c32-4770-bb95-7741238476f2");
                startActivity(i);
            }
        });

        t1.setBackgroundResource(R.drawable.t1);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Homescreen.this,Watch.class);
                i.putExtra("address","https://firebasestorage.googleapis.com/v0/b/streamit-ff036.appspot.com/o/mrrorbot.mp4?alt=media&token=5fe13fae-ca7d-4feb-9ee2-c0a5be2d672e");
                startActivity(i);
            }
        });
        t2.setBackgroundResource(R.drawable.t2);
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Homescreen.this,Watch.class);
                i.putExtra("address","https://firebasestorage.googleapis.com/v0/b/streamit-ff036.appspot.com/o/friends.mp4?alt=media&token=0c41a4da-362a-460a-a075-090c7d91b985");
                startActivity(i);
            }
        });

        m1.setBackgroundResource(R.drawable.m1);
        m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Homescreen.this,Watch.class);
                i.putExtra("address","https://firebasestorage.googleapis.com/v0/b/streamit-ff036.appspot.com/o/perfect.mp4?alt=media&token=623efbbd-07e0-45e4-aebc-279fd98dbdbd");
                startActivity(i);
            }
        });

        m2.setBackgroundResource(R.drawable.m2);
        m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Homescreen.this,Watch.class);
                i.putExtra("address","https://firebasestorage.googleapis.com/v0/b/streamit-ff036.appspot.com/o/mahi.mp4?alt=media&token=c75e9ee7-ecde-4ef4-b274-3327d95e2e31");
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        mAuth.signOut();
        startActivity(new Intent(Homescreen.this,MainActivity.class));
        return super.onOptionsItemSelected(item);
    }
}
