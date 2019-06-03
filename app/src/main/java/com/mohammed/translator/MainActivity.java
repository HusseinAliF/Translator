package com.mohammed.translator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button transS, addS;
    Intent start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        transS = findViewById(R.id.translateStart);
        addS = findViewById(R.id.addWordStart);

        transS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start = new Intent(MainActivity.this, TranslatePage.class);
                startActivity(start);
            }
        });

        addS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start = new Intent(MainActivity.this, AddWord.class);
                startActivity(start);
            }
        });
    }
}
