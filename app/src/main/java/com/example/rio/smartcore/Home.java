package com.example.rio.smartcore;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Home extends AppCompatActivity implements View.OnClickListener {

    private String username;
    private String password;

    private TextView welcome;
    private ListView list_devices;
    private Button add;

    private Device devices[];
    private DeviceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent home = getIntent();
        username = home.getStringExtra("username");
        welcome = (TextView)findViewById(R.id.textView7);
        welcome.setText("Hola, " + username + "!");

        devices = new Device[] {
                new Device(R.mipmap.llum, "Llum0"),
                new Device(R.mipmap.llum, "Llum1")
        };
        adapter = new DeviceAdapter(this,R.layout.device_list,devices);
        list_devices = (ListView)findViewById(R.id.listView);
        list_devices.setAdapter(adapter);

        add = (Button)findViewById(R.id.button4);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button4) {
            Intent addDevice = new Intent(Home.this,AddDevice.class);
            Home.this.startActivity(addDevice);
            devices = new Device[] {
                    new Device(R.mipmap.llum, "Llum0"),
                    new Device(R.mipmap.llum, "Llum1"),
                    new Device(R.mipmap.llum, "Llum2"),
                    new Device(R.mipmap.alarma, "Alarma")
            };
            adapter = new DeviceAdapter(this,R.layout.device_list,devices);
            list_devices.setAdapter(adapter);
        }
    }
}
