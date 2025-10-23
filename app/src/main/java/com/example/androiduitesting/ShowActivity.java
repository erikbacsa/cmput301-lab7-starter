package com.example.androiduitesting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_activity);

        String name = getIntent().getStringExtra("name");
        TextView cityText = findViewById(R.id.cityText);
        cityText.setText(name != null ? name : "(no name)");

        Button goBackBtn = findViewById(R.id.goBackBtn);
        goBackBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
//                Intent intent = new Intent(ShowActivity.this, MainActivity.class);
//                startActivity(intent);
            }
        });

    }
}
