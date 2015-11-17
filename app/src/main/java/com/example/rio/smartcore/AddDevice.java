package com.example.rio.smartcore;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddDevice extends AppCompatActivity implements View.OnClickListener {

    private Button add;
    private EditText device;
    private Spinner type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("SmartCore");
        setContentView(R.layout.activity_add_device);
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

        getIntent();
        device = (EditText)findViewById(R.id.editText5);
        type = (Spinner) findViewById(R.id.spinner);
        add = (Button)findViewById(R.id.button5);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button5) {
            String name = device.getText().toString();
            type.getSelectedItem();
            Toast.makeText(AddDevice.this, "Spinner " + type.getSelectedItem(), Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
