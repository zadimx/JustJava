package com.example.android.justjava;

import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class AddNewDeviceActivity extends AppCompatActivity {

    private EditText editTextIdDevice;
    private EditText editTextPassDevice;
    private ImageView imageButtonConnect;
    private Intent intent;

    public static String getNumDevise() {
        return numDevise;
    }

    private static String numDevise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_device);
        editTextIdDevice = findViewById(R.id.editTextIdDevice);
        editTextPassDevice = findViewById(R.id.editTextPassDevice);
        imageButtonConnect = findViewById(R.id.buttonConnect);
        imageButtonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageButtonConnect.setImageResource(R.drawable.button_create_push);
                numDevise = editTextIdDevice.getText().toString();
                intent = new Intent(AddNewDeviceActivity.this, CreateNoteActivity.class);
                intent.putExtra("numDev", numDevise);
                startActivity(intent);
                finish();
            }
        });
    }
}
