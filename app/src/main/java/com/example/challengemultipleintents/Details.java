package com.example.challengemultipleintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Details extends AppCompatActivity implements View.OnClickListener {
    EditText etName, etPhone, etWeb, etLocation;
    ImageView iVHappy, iVNeutral, iVSad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etWeb = findViewById(R.id.etWeb);
        etLocation = findViewById(R.id.etLocation);

        iVHappy = findViewById(R.id.iVHappy);
        iVNeutral = findViewById(R.id.iVNeutral);
        iVSad = findViewById(R.id.iVSad);

        //Here we can choose any one button and we will be sent to the main activity
        iVHappy.setOnClickListener(this);
        iVNeutral.setOnClickListener(this);
        iVSad.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (etName.getText().toString().isEmpty() || etPhone.getText().toString().isEmpty()
                || etWeb.getText().toString().isEmpty() || etLocation.getText().toString().isEmpty()) {
            Toast.makeText(Details.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
        } else {
            String yourName = etName.getText().toString().trim();
            String yourPhone = etPhone.getText().toString().trim();
            String yourWeb = etWeb.getText().toString().trim();
            String yourLocation = etLocation.getText().toString().trim();

            Intent intent = new Intent();
            intent.putExtra("name", yourName);
            intent.putExtra("call", yourPhone);
            intent.putExtra("web", yourWeb);
            intent.putExtra("location", yourLocation);

            if (view.getId() == R.id.iVHappy) {
                intent.putExtra("mood", "happy");
            } else if (view.getId() == R.id.iVNeutral) {
                intent.putExtra("mood", "neutral");
            } else {
                intent.putExtra("mood", "sad");
            }
            setResult(RESULT_OK, intent);
            Details.this.finish();
        }
    }
}