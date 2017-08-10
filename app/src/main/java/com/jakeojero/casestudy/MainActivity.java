package com.jakeojero.casestudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jakeojero.casestudy.session.Session;

public class MainActivity extends AppCompatActivity {

    private Session session;

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_products:
                    mTextMessage.setText(R.string.title_products);
                    Intent intent = new Intent(getApplicationContext(), ProductsActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Session Object
        session = new Session(this);

        if(session.getUser() != null) {
            session.clearUser();

        }

        mTextMessage = (TextView) findViewById(R.id.message);

        /*
            Bottom Navigation
         */
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Button loginBtn = (Button) findViewById(R.id.btnLogin);
        if(!session.getUser().isEmpty()) {
            loginBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
            loginBtn.setText("Log Out");
            loginBtn.setOnClickListener(this::logOut);
        }
        else {
            loginBtn.setText("Login");
            loginBtn.setOnClickListener(this::login);
        }
    }

    public void login(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Login Clicked", Toast.LENGTH_SHORT).show();
    }

    public void logOut(View v) {
        session.clearUser();
        onResume();
        Toast.makeText(this, "Logout Clicked", Toast.LENGTH_SHORT).show();
    }

}
