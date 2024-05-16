package com.example.lostfound;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class NewAdvert extends AppCompatActivity {
    TextInputEditText name, phone, description, date, location;
    RadioGroup radioGroup;
    Button submit;
    DatabaseHelper dbHelper;
    String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_advert);

        dbHelper = new DatabaseHelper(this);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        description = findViewById(R.id.description);
        date = findViewById(R.id.date);
        location = findViewById(R.id.location);
        radioGroup = findViewById(R.id.radioGrp);
        submit = findViewById(R.id.submit);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = radioGroup.findViewById(i);
                type = radioButton.getText().toString().trim();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameText = name.getText().toString().trim();
                String phoneText = phone.getText().toString().trim();
                String descText = description.getText().toString().trim();
                String dateText = date.getText().toString().trim();
                String locationText = location.getText().toString().trim();

                if (nameText.isEmpty() || phoneText.isEmpty() || descText.isEmpty() || dateText.isEmpty() || locationText.isEmpty()) {
                    Toast.makeText(NewAdvert.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                long result = dbHelper.insertLostFoundItem(nameText, descText, dateText, locationText, phoneText, type);
                if (result != -1) {
                    Toast.makeText(NewAdvert.this, "Data Stored Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(NewAdvert.this, "Failed to store data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
