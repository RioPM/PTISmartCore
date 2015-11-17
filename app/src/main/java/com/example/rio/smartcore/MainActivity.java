package com.example.rio.smartcore;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener {

    private EditText username;
    private EditText password;
    private Button login;
    private Button register;

    private int loginOk = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("SmartCore");
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        username = (EditText)findViewById(R.id.editText);
        password = (EditText)findViewById(R.id.editText2);
        login = (Button)findViewById(R.id.button);
        login.setOnClickListener(this);
        register = (Button)findViewById(R.id.button2);
        register.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            String user = username.getText().toString();
            String pass = password.getText().toString();
            if (user != null && pass != null && !user.isEmpty() && !pass.isEmpty()) {
                /*String strikeIronUserName = "stikeironusername@yourdomain.com";
                String strikeIronPassword = "strikeironpassword";
                String apiURL = "http://ws.strikeiron.com/StrikeIron/EMV6Hygiene/VerifyEmail?";
                String urlString = apiURL + "LicenseInfo.RegisteredUser.UserID=" + strikeIronUserName + "&LicenseInfo.RegisteredUser.Password=" + strikeIronPassword + "&VerifyEmail.Email=" + strikeIronUserName + "&VerifyEmail.Timeout=30";
                */
                String urlString = "http://localhost:8080/RestServer/webresources/login?username=rio&password=rio";
                new CallRest().execute(urlString);
                if (loginOk == 1) {
                    Intent home = new Intent(this, Home.class);
                    home.putExtra("username", user);
                    home.putExtra("password", pass);
                    startActivity(home);
                }
                else Toast.makeText(getApplicationContext(), "Les credencials són invàlides", Toast.LENGTH_LONG).show();
                /*Intent home = new Intent(this, Home.class);
                home.putExtra("username", user);
                home.putExtra("password", pass);
                startActivity(home);
                */

            }
            else {
                Toast.makeText(getApplicationContext(), "Les credencials són invàlides", Toast.LENGTH_LONG).show();
            }
        }
        else if (v.getId() == R.id.button2) {
            Intent register = new Intent(this, Register.class);
            startActivity(register);
        }
    }

    private class CallRest extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            String URLRest = (String) params[0];
            String result = "";
            InputStream RestResponse = null;

            try {
                URL url = new URL(URLRest);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                RestResponse = new BufferedInputStream(urlConnection.getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

    }
}
