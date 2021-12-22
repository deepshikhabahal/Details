package com.example.challengemultipleintents;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnCreate;
    final int DETAILS = 3;
    ImageView imgFace, imgCall, imgWeb, imgLocation;
    TextView tVName;
    String name="", call="", web="", location="", mood="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreate = findViewById(R.id.btnCreate);
        imgFace = findViewById(R.id.imgFace);
        imgCall = findViewById(R.id.imgCall);
        imgWeb = findViewById(R.id.imgWeb);
        imgLocation = findViewById(R.id.imgLocation);
        tVName = findViewById(R.id.tVName);

        imgFace.setVisibility(View.GONE);
        imgCall.setVisibility(View.GONE);
        imgWeb.setVisibility(View.GONE);
        imgLocation.setVisibility(View.GONE);
        tVName.setVisibility(View.GONE);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.challengemultipleintents.Details.class);
                startActivityForResult(intent,DETAILS);
            }
        });

        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + call));
                startActivity(intent);
            }
        });

        imgWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(web));
                startActivity(intent);
            }
        });

        imgLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + location));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == DETAILS){
            if(resultCode == RESULT_OK){
                imgFace.setVisibility(View.VISIBLE);
                imgCall.setVisibility(View.VISIBLE);
                imgWeb.setVisibility(View.VISIBLE);
                imgLocation.setVisibility(View.VISIBLE);
                tVName.setVisibility(View.VISIBLE);

                name = data.getStringExtra("name");
                call = data.getStringExtra("call");
                web = data.getStringExtra("web");
                location = data.getStringExtra("location");
                mood = data.getStringExtra("mood");

                tVName.setText(data.getStringExtra("name"));

                if (mood.equals("happy")){
                    imgFace.setImageResource(R.drawable.happy);
                }
                else if (mood.equals("neutral")){
                    imgFace.setImageResource(R.drawable.neutral);
                }
                else
                    imgFace.setImageResource(R.drawable.sad);
            }
            else
                Toast.makeText(MainActivity.this, "No data passed through", Toast.LENGTH_SHORT).show();
        }
    }
}